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
import org.junit.Test;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.data.Index.atIndex;
import static org.assertj.core.error.ShouldContainAtIndex.shouldContainAtIndex;
import static org.assertj.core.util.FailureMessages.actualIsNull;
import static org.assertj.vavr.api.VavrAssertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

public class ListAssert_contains_atIndex_Test extends BaseTest {

    @Test
    public void should_pass_if_List_contains_expected_value_at_given_index() {
        final String value = "something";
        assertThat(List.of(value)).contains("something", atIndex(0));
    }

    @Test
    public void should_fail_when_List_is_null() {
        thrown.expectAssertionError(actualIsNull());

        assertThat((List<String>) null).contains("something", atIndex(0));
    }

    @Test
    public void should_fail_if_given_index_is_greater_than_list_size() {
        final List<String> actual = List.of("something");

        Throwable thrown = catchThrowable(() -> assertThat(actual).contains(null, atIndex(2)));

        assertThat(thrown).isInstanceOf(IndexOutOfBoundsException.class)
                .hasMessage("Index should be between <0> and <0> (inclusive) but was:\n <2>");
    }

    @Test
    public void should_fail_when_expected_values_are_null() {
        final List<String> actual = List.of("something");

        thrown.expectAssertionError(shouldContainAtIndex(actual, null, atIndex(0), "something"));

        assertThat(actual).contains(null, atIndex(0));
    }

    @Test
    public void should_fail_if_List_contains_no_expected_element_at_given_index() {
        List<String> actual = List.of("a", "b", "c");

        thrown.expectAssertionError(shouldContainAtIndex(actual, "a", atIndex(2), "c"));

        assertThat(actual).contains("a", atIndex(2));
    }
}