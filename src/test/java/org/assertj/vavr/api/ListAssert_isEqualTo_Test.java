/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 * <p>
 * Copyright 2012-2017 the original author or authors.
 */
package org.assertj.vavr.api;

import io.vavr.collection.List;
import org.assertj.vavr.test.BaseTest;
import org.junit.ComparisonFailure;
import org.junit.Test;

import static org.assertj.vavr.api.VavrAssertions.assertThat;

public class ListAssert_isEqualTo_Test extends BaseTest {

    @Test
    public void should_pass_if_List_is_equal_to_expected() {
        final String value = "something";
        assertThat(List.of(value)).isEqualTo(List.of(value));
    }

    @Test
    public void should_fail_when_List_is_null() {
        thrown.expect(ComparisonFailure.class, "expected:<List(something)> but was:<null>");

        assertThat((List<String>) null).isEqualTo(List.of("something"));
    }

    @Test
    public void should_fail_when_expected_values_are_null() {
        final List<String> actual = List.of("something");

        thrown.expect(ComparisonFailure.class, "expected:<null> but was:<List(something)>");

        assertThat(actual).isEqualTo(null);
    }

    @Test
    public void should_fail_if_List_is_not_equal_to_expected() {
        List<String> actual = List.of("a", "b", "c");

        thrown.expect(ComparisonFailure.class, "expected:<List(a[])> but was:<List(a[, b, c])>");

        assertThat(actual).isEqualTo(List.of("a"));
    }
}