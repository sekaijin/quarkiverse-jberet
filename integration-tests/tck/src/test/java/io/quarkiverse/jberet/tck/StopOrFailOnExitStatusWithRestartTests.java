/*
 * Copyright 2012 International Business Machines Corp.
 *
 * See the NOTICE file distributed with this work for additional information
 * regarding copyright ownership. Licensed under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 */
package io.quarkiverse.jberet.tck;

import static io.quarkiverse.jberet.tck.JobOperatorSetter.setJobOperator;

import javax.inject.Inject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import jakarta.batch.operations.JobOperator;

//@QuarkusTest
@Disabled
public class StopOrFailOnExitStatusWithRestartTests
        extends com.ibm.jbatch.tck.tests.jslxml.StopOrFailOnExitStatusWithRestartTests {
    @Inject
    JobOperator jobOperator;

    @BeforeEach
    void beforeEach() throws Exception {
        setUp();
        setJobOperator(this, jobOperator);
    }

    @Test
    public void testInvokeJobWithUserStopAndRestart() throws Exception {
        super.testInvokeJobWithUserStopAndRestart();
    }

    @Test
    public void testInvokeJobWithUncaughtExceptionFailAndRestart() throws Exception {
        super.testInvokeJobWithUncaughtExceptionFailAndRestart();
    }
}
