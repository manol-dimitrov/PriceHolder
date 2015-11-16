package com.company;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Manol on 14/10/2015.
 */
public class Question2Test {

    private Question2 testPriceHolder;
    private ScheduledExecutorService executorService;

    @Before
    public void setUp() throws Exception {
        testPriceHolder = new Question2();
        executorService = Executors.newScheduledThreadPool(5);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void shouldGetCorrectPrice() throws Exception {
        Runnable task1 = () -> testPriceHolder.putPrice("a", new BigDecimal(20));
        executorService.schedule(task1, 1, TimeUnit.MILLISECONDS);

        Runnable task2 = () -> testPriceHolder.putPrice("a", new BigDecimal(30));
        executorService.schedule(task2, 1, TimeUnit.MILLISECONDS);

        Runnable task3 = () -> testPriceHolder.putPrice("a", new BigDecimal(40));
        executorService.schedule(task3, 1, TimeUnit.MILLISECONDS);

        assertEquals(testPriceHolder.getPrice("a"), new BigDecimal(40));
    }

    @Test
    public void testHasPriceChanged() throws Exception {

        testPriceHolder.putPrice("a", new BigDecimal("40.00"));
        testPriceHolder.putPrice("a", new BigDecimal("50.00"));

        boolean hasPriceChangedTest = testPriceHolder.hasPriceChanged("a");

        assertTrue(hasPriceChangedTest);
    }
}