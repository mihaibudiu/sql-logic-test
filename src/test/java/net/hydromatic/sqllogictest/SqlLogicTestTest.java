/*
 * Licensed to Julian Hyde under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to you under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.hydromatic.sqllogictest;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Tests {@link Main}.
 */
public class SqlLogicTestTest {
  private static final String UTF_8 = StandardCharsets.UTF_8.name();

  /** Test that prints usage information to stdout.
   *
   * <p>There is no way to redirect the usage output; JCommander writes directly
   * to {@link System#console()}.
   */
  @Test void testMain() throws IOException {
    final ByteArrayOutputStream baosOut = new ByteArrayOutputStream();
    final PrintStream out = new PrintStream(baosOut);
    final ByteArrayOutputStream baosErr = new ByteArrayOutputStream();
    final PrintStream err = new PrintStream(baosErr);
    Main.main2(false, out, err, new String[] {"-h"});
    out.flush();
    err.flush();
    assertThat(baosErr.toString(UTF_8), is(""));
    assertThat(baosOut.toString(UTF_8),
        is("ExecutionOptions{root=null, files=[], execute=true, "
            + "executor=calcite, stopAtFirstError=false}\n"));
  }
}

// End SqlLogicTestTest.java
