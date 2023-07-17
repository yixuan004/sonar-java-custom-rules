package org.sonar.java.rule.checks.lyxrules;

import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

public class RequestAnnotationCheckTest {
    @Test
    public void test() {
        JavaCheckVerifier.verify("src/test/files/RequestAnnotationCheckTestCase.java", new RequestAnnotationCheck());
    }
}
