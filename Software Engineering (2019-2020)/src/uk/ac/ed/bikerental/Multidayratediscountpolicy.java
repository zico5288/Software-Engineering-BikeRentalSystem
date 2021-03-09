package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Collection;
import java.util.*;

public class Multidayratediscountpolicy implements PricingPolicy {
    private Map<BikeType, BigDecimal> setDailyRentalPrice = new HashMap<>();;

    @Override
    public void setDailyRentalPrice(BikeType bikeType, BigDecimal dailyPrice) {
        // TODO Auto-generated method stub

        this.setDailyRentalPrice.put(bikeType, dailyPrice);
    }

    @Override
    public BigDecimal calculatePrice(Collection<Bike> bikes, DateRange duration) {
        // TODO Auto-generated method stub
        BigDecimal sum = new BigDecimal(0);
        BigDecimal totalprice = new BigDecimal(0);
        int days = (int) duration.toDays();
        for (Bike bike : bikes) {
            BikeType bikeType = bike.getType();
            BigDecimal price = setDailyRentalPrice.get(bikeType);
            BigDecimal a = new BigDecimal(0.95);
            BigDecimal b = new BigDecimal(0.90);
            BigDecimal c = new BigDecimal(0.85);

            if (days >= 14) {
                price = price.multiply(c);
            } else if (days >= 7) {
                price = price.multiply(b);
            } else if (days >= 3) {
                price = price.multiply(a);
            }
            sum = sum.add(price); /// calculate totoal price
        }
        totalprice = sum.round(new MathContext(3)).multiply(new BigDecimal(days));
        return totalprice;
    }
}