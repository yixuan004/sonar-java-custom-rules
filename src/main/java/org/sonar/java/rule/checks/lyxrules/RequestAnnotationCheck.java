package org.sonar.java.rule.checks.lyxrules;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Rule(key="RequestAnnotationCheck")
public class RequestAnnotationCheck extends BaseTreeVisitor implements JavaFileScanner {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestAnnotationCheck.class);

    private JavaFileScannerContext context;

    // 用来匹配以"Request"结尾的类名
    private String regex = ".*Request$";
    private Pattern pattern = Pattern.compile(regex);

    // 两个待匹配的注解名称，这里因为确定了是两个就没有写成列表或者其他Map的形式
    private final String TARGET_ANNOTATION_1 = "test1";
    private final String TARGET_ANNOTATION_2 = "test2";

    @Override
    public void scanFile(JavaFileScannerContext context) {
        this.context = context;
        scan(context.getTree());
    }

    @Override
    public void visitClass(ClassTree tree) {
        // 只对结尾为Request的文件进行检查，实际使用的时候修改为对结尾为VO的文件进行检查
        boolean isRequest = hasRequestClassName(tree);
        if (isRequest) {
            for (Tree member : tree.members()) {
                // 只匹配里面的VariableTree，可以通过member.getClass()查看，比如可以看到 VariableTreeImpl 和 MethodTreeImpl
                if (member instanceof VariableTree) {
                    // 初始化重置两个为False
                    boolean isEqualAnno1 = false;
                    boolean isEqualAnno2 = false;

                    // 获取VariableTree的名称
                    VariableTree variableTree = (VariableTree) member;
                    LOGGER.info("variableTree.simpleName().name(): " + variableTree.simpleName().name());

                    // 获取该variable下所有注解的名称，和两个待匹配的注解名称进行匹配
                    for (AnnotationTree annotation : variableTree.modifiers().annotations()) {
                        if (annotation.annotationType().is(Tree.Kind.IDENTIFIER)) {
                            IdentifierTree identifier = (IdentifierTree) annotation.annotationType();
                            if (TARGET_ANNOTATION_1.equals(identifier.name())) {
                                isEqualAnno1 = true;
                            } else if (TARGET_ANNOTATION_2.equals(identifier.name())) {
                                isEqualAnno2 = true;
                            }
                        }
                    }

                    // 如果同时匹配到了两个注解名称，那么会报错
                    if (isEqualAnno1 && isEqualAnno2) {
                        context.reportIssue(this, variableTree,"不能同时使用 " + TARGET_ANNOTATION_1 + ", "+ TARGET_ANNOTATION_2 + "两种注解");
                    }
                }
            }
        }
        super.visitClass(tree);
    }

    private boolean hasRequestClassName(ClassTree tree) {
        Matcher matcher = pattern.matcher(tree.simpleName().name());
        if (matcher.matches()) {
            LOGGER.info("正则表达式匹配类 " + tree.simpleName().name() + " 成功");
            return true;
        } else {
            LOGGER.info("正则表达式匹配类 " + tree.simpleName().name() + " 失败");
            return false;
        }
    }
}