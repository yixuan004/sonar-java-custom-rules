/*
 * SonarQube Java Custom Rules Example
 * Copyright (C) 2016-2016 SonarSource SA
 * mailto:contact AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.java.rule;

import com.google.common.collect.ImmutableList;
import org.sonar.java.rule.checks.lyxrules.*;
import org.sonar.java.rule.checks.namerules.*;
import org.sonar.plugins.java.api.JavaCheck;

import java.util.List;

public final class RulesList {

  private RulesList() {
  }

  public static List<Class> getChecks() {
    return ImmutableList.<Class>builder().addAll(getJavaChecks()).addAll(getJavaTestChecks()).build();
  }

  public static List<Class<? extends JavaCheck>> getJavaChecks() {
    return ImmutableList.<Class<? extends JavaCheck>>builder()
            .add(UnderlineDollarNameCheck.class)
            .add(UpperCameCaseCheck.class)
            .add(LowerCameCaseCheck.class)
            .add(ConstantNameCheck.class)
            .add(AbstractClassNameCheck.class)
            .add(ExceptionClassNameCheck.class)
            .add(ArrayNameCheck.class)
            .add(VariableLengthCheck.class)
            .add(RequestAnnotationCheck.class)
      .build();
  }

  public static List<Class<? extends JavaCheck>> getJavaTestChecks() {
    return ImmutableList.<Class<? extends JavaCheck>>builder()
      .build();
  }
}
