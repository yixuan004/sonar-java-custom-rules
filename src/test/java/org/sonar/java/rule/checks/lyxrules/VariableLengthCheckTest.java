package org.sonar.java.rule.checks.lyxrules;

import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

/**
 * Created by Liu Yixuan on 2023/7/13.
 */
public class VariableLengthCheckTest {
    @Test
    public void test() {
        JavaCheckVerifier.verify("src/test/files/VariableLengthTestCase.java", new VariableLengthCheck());
    }
}
