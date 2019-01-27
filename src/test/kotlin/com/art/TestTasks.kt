package com.art

import com.art.calculator.Calculator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class TestTasks {
    @Test
    fun `test remove duplicates`() {
        assertThat("aa".removeDuplicates()).isEqualToIgnoringCase("a")
        assertThat("aabb".removeDuplicates()).isEqualToIgnoringCase("ab")
        assertThat("aaccaabbaacv".removeDuplicates()).isEqualToIgnoringCase("acabacv")
        assertThat("aacckkbbaacv5".removeDuplicates()).isEqualToIgnoringCase("ackbacv5")
        assertThat("jjddllskkfhaafnnvjhhdkk".removeDuplicates()).isEqualToIgnoringCase("jdlskfhafnvjhdk")
    }

    @Test
    fun `test merge sort`() {
        assertThat("4".stringToIntList(" ").mergeSort().joinToString(separator = " "))
            .isEqualToIgnoringCase("4")
        assertThat("4 0".stringToIntList(" ").mergeSort().joinToString(separator = " "))
            .isEqualToIgnoringCase("0 4")
        assertThat("0 53 67 1 35 5 6 7 8 33".stringToIntList(" ").mergeSort().joinToString(separator = " "))
            .isEqualToIgnoringCase("0 1 5 6 7 8 33 35 53 67")

        assertThat("53 67 5 35 5 6 7 8 33".stringToIntList(" ").mergeSort().joinToString(separator = " "))
            .isEqualToIgnoringCase("5 5 6 7 8 33 35 53 67")
    }

    @Test
    fun `test calculator`() {
        assertThat(Calculator("5 - 6").calculate()).isEqualTo(-1)
        assertThat(Calculator("5 * 6").calculate()).isEqualTo(30)
        assertThat(Calculator("30 / 6").calculate()).isEqualTo(5)
        assertThat(Calculator("5 + 6 * 4").calculate()).isEqualTo(29)
        assertThat(Calculator("4 +9 *1 *3 - 14").calculate()).isEqualTo(17)
        assertThat(Calculator("9*9*9+12+34/2*2-3").calculate()).isEqualTo(772)
    }
}



