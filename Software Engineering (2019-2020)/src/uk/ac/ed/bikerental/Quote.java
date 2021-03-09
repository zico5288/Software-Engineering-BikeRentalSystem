package uk.ac.ed.bikerental;

import java.math.BigDecimal;

public class Quote {
    private Provider provider;
    private BikeType biketype;
    private BigDecimal bikeTotalprice;
    private BigDecimal deposit;
    // private User user;
    private Bike bike;

    public Quote(Bike bike) {
        this.bike = bike;
        this.provider = bike.bikegetProvider();
        this.biketype = bike.getType();
        // BigDecimal days = new BigDecimal(user.getNumberbookingdays());
        // this.bikeTotalprice = days.multiply(bike.getDailyrentprice());
        this.deposit = bike.getdeposit();

    }

    // getter
    public Bike quotegetbike() {
        return bike;
    }

    public int quotegetBikeId() {
        return bike.getBikeId();
    }

    public DateRange getBikeDaterange() {
        return bike.getDateRange();
    }

    public Provider getprovider() {
        return provider;
    }

    public BikeType getbiketype() {
        return biketype;
    }

    public BigDecimal getdeposit() {
        return deposit;
    }

    public BigDecimal getdailyprice() {
        return bike.getDailyrentprice();
    }

    public BigDecimal getreplacementvalue() {
        return getprovider().getReplacementvalue(getbiketype());
    }

    // totalprice
    public void settotalprice(BigDecimal bikeTotalprice) {
        this.bikeTotalprice = bikeTotalprice;
    }

    public BigDecimal gettotalprice() {
        return bikeTotalprice;
    }
}
