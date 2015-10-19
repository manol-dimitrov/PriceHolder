package com.company;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Manol on 14/10/2015.
 */
public class PriceHolderTest {

    private Map<String, BigDecimal> testPriceHolder;
    private

    @Before
    public void setUp() throws Exception {
        testPriceHolder = Collections.synchronizedMap(new LinkedHashMap<>());
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void shouldGetCorrectPrice() throws Exception {
        //arrange
        testPriceHolder.put("a", new BigDecimal("1000.00"));
        testPriceHolder.put("a", new BigDecimal("20.00"));
        testPriceHolder.put("a", new BigDecimal("30.00"));
        testPriceHolder.put("a", new BigDecimal("40.00"));
        testPriceHolder.put("a", new BigDecimal("50.00"));
        //act
        //assert
        Assert.assertEquals(testPriceHolder.get("a"), "50.00");
    }

    @Test
    public void testHasPriceChanged() throws Exception {

    }
}