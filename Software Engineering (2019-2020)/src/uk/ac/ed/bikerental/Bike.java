package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.time.LocalDate;

enum BikeStatuse {
    InStock, Renting, Tracking;
}

public class Bike implements Deliverable{
    private int bikeId;
    private BikeType bikeinfo;
    private BikeStatuse bikeStatus;
    private Provider provider;
    private BigDecimal deposit;
    private DateRange bikedaterange;
    private LocalDate ManuDate;

    public Bike(BikeType bikeinfo, Provider provider, int bikeId) {
        this.bikeId = bikeId;
        this.bikeinfo = bikeinfo;
        this.bikeStatus = BikeStatuse.InStock;
        this.provider = provider;
    }

    // getter&setter
    public Provider bikegetProvider() {
        return provider;
    }

    public BikeType getType() {
        // TODO: Implement Bike.getType
        return bikeinfo;
    }

    public int getBikeId() {
        return bikeId;
    }

    public void setManuDate(LocalDate ManuDate) {
        this.ManuDate = ManuDate;
    }

    public LocalDate getManuDate() {
        return ManuDate;
    }

    // BikeStatus
    public void setBikeStatusToInstock() {
        this.bikeStatus = BikeStatuse.InStock;
    }

    public void setBikeStatusToRenting() {
        this.bikeStatus = BikeStatuse.Renting;
    }

    public void setBikeStatusToTracking() {
        this.bikeStatus = BikeStatuse.Tracking;
    }

    public BikeStatuse getBikeStatus() {
        return bikeStatus;
    }

    // dateRange
    public void setDateRange(DateRange bikedaterange) {
        this.bikedaterange = bikedaterange;
    }

    public DateRange getDateRange() {
        return bikedaterange;
    }

    // replacementvalue
    public BigDecimal getReplacevalue() {
        return provider.getdepositPolicy().get(bikeinfo);
    }

    // dailyrentprice
    public BigDecimal getDailyrentprice() {
        return provider.getDailyrentprice(bikeinfo);
    }

    // deposit
    public void setdeposit() {
        BigDecimal a = provider.getReplacementvalue(bikeinfo);
        deposit = a.multiply(provider.getDepositRate());
    }

    public BigDecimal getdeposit() {
        return deposit;
    }

    @Override
    public void onPickup() {
        this.bikeStatus = BikeStatuse.Tracking;
    }

    @Override
    public void onDropoff() {
        this.bikeStatus = BikeStatuse.InStock;
    }

}