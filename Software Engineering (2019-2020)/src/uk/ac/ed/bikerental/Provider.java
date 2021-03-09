package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.*;

public class Provider {
    private int providerId = 0;
    private Location provideraddress;
    private int bikeNumber = 0;
    private Map<BikeType, BigDecimal> depositPolicy = new HashMap<>();
    private Map<BikeType, BigDecimal> Dailyrentprice = new HashMap<>();
    private Collection<Bike> bikelist = new ArrayList<Bike>();
    private BigDecimal DepositRate;
    // quotelist)
    private Collection<Quote> quotelist = new ArrayList<>();;
    // parnter
    private Collection<Provider> partnerlist = new ArrayList<>();

    public Provider(Location provideraddress) {
        this.providerId = providerId + 1;
        this.provideraddress = provideraddress;
    }

    // provideQuote
    public void provideQuote(Bike bike) {
        Quote newquote = new Quote(bike);
        quotelist.add(newquote);
    }

    public Collection<Quote> getQuotelist() {
        return quotelist;
    }

    // getter
    public Location getLocation() {
        return provideraddress;
    }

    public int getproviderId() {
        return providerId;
    }

    public Collection<Bike> getbikelist() {
        return bikelist;
    }

    // bikeInfo
    public void addBike(Bike newbike) {
        bikelist.add(newbike);
        bikeNumber += 1;
    }

    public void removeBike(Bike removebike) {
        assert (bikelist.contains(removebike));
        bikelist.remove(removebike);
    }

    public int getbikeNumber() {
        return bikeNumber;
    }

    // partner
    public void registerPartner(Provider partner) {
        assert (partnerlist.contains(partner) == false);
        partnerlist.add(partner);
    }

    public Collection<Provider> getPartnerlist() {
        return partnerlist;
    }

    // Bikestatus
    public void setBikeStatusToRenting(Bike bike) {
        assert (bike.getBikeStatus() != BikeStatuse.Renting);
        bike.setBikeStatusToRenting();
    }

    public void setBikeStatusToInStock(Bike bike) {
        assert (bike.getBikeStatus() != BikeStatuse.InStock);
        bike.setBikeStatusToInstock();
    }

    public void setBikeStatusToTracking(Bike bike) {
        assert (bike.getBikeStatus() != BikeStatuse.Tracking);
        bike.setBikeStatusToTracking();
    }

    public BikeStatuse getBikeStatus(Bike bike) {
        return bike.getBikeStatus();
    }

    // replacementvalue
    public void setReplacementvalue(BikeType type, BigDecimal Replacementvalue) {
        depositPolicy.put(type, Replacementvalue);
    }

    public BigDecimal getReplacementvalue(BikeType type) {
        return depositPolicy.get(type);
    }

    public Map<BikeType, BigDecimal> getdepositPolicy() {
        return depositPolicy;
    }

    // depositrate
    public void setDepositRate(BigDecimal DepositRate) {
        this.DepositRate = DepositRate;
    }

    public BigDecimal getDepositRate() {
        return DepositRate;
    }

    // deposit
    public void setdeposit(Bike bike) {
//		BikeType biketype = bike.getType();
//		Depositmap.put(bike, getReplacementvalue(biketype).multiply(DepositRate));
        bike.setdeposit();
    }

    public BigDecimal getdeposit(Bike bike) {
//		return Depositmap.get(bike);
        return bike.getdeposit();
    }

    // dailyrentprice
    public void setDailyrentprice(BikeType type, BigDecimal dailyrentprice) {
        Dailyrentprice.put(type, dailyrentprice);
    }

    public BigDecimal getDailyrentprice(BikeType type) {
        return Dailyrentprice.get(type);
    }

    public Map<BikeType, BigDecimal> getDailyrentprice() {
        return Dailyrentprice;
    }

    // return to original provider
    public void returnBikeToOriginalProvider() {

    }

}
