package com.company;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Manol on 11/10/2015.
 */
public final class PriceHolder {

    private Map<String, BigDecimal> priceHolder;

    public PriceHolder() {
        priceHolder = Collections.synchronizedMap(new LinkedHashMap<>());
    }

    /**
     * Called when a price ‘p’ is received for an entity ‘e’
     */
    public void putPrice(String e, BigDecimal p) {
        priceHolder.putIfAbsent(e, p);
    }

    /**
     * Called to get the latest price for entity ‘e’
     */
    public BigDecimal getPrice(String e) {
        return priceHolder.get(e);
    }

    /**
     * Called to determine if the price for entity ‘e’ has
     * changed since the last call to getPrice(e).
     */
    public boolean hasPriceChanged(String e) {
        BigDecimal latestPriceForEntity = getMostRecentPriceForEntity(e, priceHolder);
        assert latestPriceForEntity != null;
        if (latestPriceForEntity != priceHolder.get(e)) {
            return true;
        }
        return false;
    }

    private BigDecimal getMostRecentPriceForEntity(String e, Map priceHolder) {
        List<Map.Entry<String, BigDecimal>> entryList = new ArrayList<>(priceHolder.entrySet());
        Map.Entry<String, BigDecimal> lastEntry = entryList.get(entryList.size() - 1);
        if (lastEntry.getKey().equals(e)) {
            return lastEntry.getValue();
        }
        return new BigDecimal(0);
    }
}