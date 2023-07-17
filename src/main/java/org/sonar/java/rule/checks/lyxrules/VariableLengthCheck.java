package org.sonar.java.rule.checks.lyxrules;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.*;

/**
 * 变量名长度不能能大于20
 * Created by Liu Yixuan on 2023/7/13.
 */
@Rule(key="VariableLengthCheck")
public class VariableLengthCheck extends BaseTreeVisitor implements JavaFileScanner{

    private static final Logger LOGGER = LoggerFactory.getLogger(VariableLengthCheck.class);


    private JavaFileScannerContext context;

    @Override
    public void scanFile(JavaFileScannerContext context) {
        this.context = context;
        scan(context.getTree());
    }

    /**
     * 判断变量命名是否符合规范(方法参数变量和成员变量)
     * @param tree
     */
    @Override
    public void visitVariable(VariableTree tree){
        // 一些处理流程
        String variableName = tree.simpleName().name();
        int variableNameLength = variableName.length();
        LOGGER.info(">>>>variableName>>>>" + variableName);
        LOGGER.info(">>>>variableNameLength>>>>" + variableNameLength);


        if (variableNameLength >= 20) {
            LOGGER.info(">>该变量命名不符合规范>>" + tree.simpleName().name());
            context.reportIssue(this, tree,"The length of Member Variable should < 20");
        }


        // 递归
        super.visitVariable(tree);  // 这里是一个递归的执行逻辑，会去找到一段代码里的所有Variable，本身就是一棵树应该

    }
}