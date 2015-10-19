package com.company;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class Question1 {
    /**
     * Takes a function and applies it each element of a list
     *
     * @param plusOne
     * @param list
     * @return
     */
    static <T, R> List<R> map(Function<T, R> plusOne, List<T> list) {
        return list.stream().map(plusOne).collect(Collectors.toList());
    }

    @Test
    public void test() {
        assertThat(Question1.map(n -> n + 1, Arrays.asList(1, 2, 3)), is(Arrays.asList(2, 3, 4)));
    }
}



