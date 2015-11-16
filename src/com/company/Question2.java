package com.company;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Manol on 11/10/2015.
 */
public final class Question2 {

    private Map<String, LinkedList<BigDecimal>> priceHolder;

    private Lock addPriceLock = new ReentrantLock();

    public Question2() {
        priceHolder = new ConcurrentHashMap<>();
    }

    /**
     * Called when a price�p� is received for an entity �e�
     */
    public void putPrice(String e, BigDecimal p) {
        if (priceHolder.get(e) != null) {
            priceHolder.get(e).add(p);
        } else {
            LinkedList initialPricingList = new LinkedList();
            try {
                addPriceLock.lock();
                initialPricingList.add(p);
            } finally {
                addPriceLock.unlock();
            }
            priceHolder.put(e, initialPricingList);
        }
    }

    /**
     * Called to get the latest price for entity �e�
     */
    public BigDecimal getPrice(String e) {
        return priceHolder.get(e).getFirst();
    }

    /**
     * Called to determine if the price for entity �e� has
     * changed since the last call to getPrice(e).
     */
    public boolean hasPriceChanged(String e) {
        BigDecimal latestPriceForEntity = priceHolder.get(e).getLast();
        BigDecimal previousPriceForEntity = priceHolder.get(e).get(priceHolder.get(e).size() - 2);

        assert latestPriceForEntity != null;
        assert previousPriceForEntity != null;

        if (latestPriceForEntity != previousPriceForEntity) {
            return true;
        }
        return false;
    }


    private BigDecimal getMostRecentPriceForEntity(String e, Map priceHolder) {
        List<Map.Entry<String, BigDecimal>> entryList = new ArrayList<>(priceHolder.entrySet());

        if (entryList.size() >= 1) {
            Map.Entry<String, BigDecimal> lastEntry = entryList.get(entryList.size() - 1);
            if (lastEntry.getKey().equals(e)) {
                return lastEntry.getValue();
            }
        }

        return new BigDecimal(0);
    }
}