package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Collection;

enum CollectingAndDeliverMode {
    StoreCollection, Delivery;
}

enum ReturnMode {
    OriginalProvider, PartnerProvider;
}

public class Confirmation {
    private int orderNumber = 0;
    private String orderSummary;
    private BigDecimal deposit;
    private BigDecimal totalprice;
    private CollectingAndDeliverMode collectmode;
    private ReturnMode returnmode;
    private Quote quote;
    private Bike bike;
    private Provider provider;
    private Collection<Provider> parnterlist;
    private Location originalProviderlocation;
    // private delivery

    public Confirmation(Quote quote, CollectingAndDeliverMode collectmode, ReturnMode returnmode) {
        this.quote = quote;
        this.orderNumber += 1;
        this.deposit = quote.getdeposit();
        this.totalprice = quote.gettotalprice();
        this.collectmode = collectmode;
        this.returnmode = returnmode;
        this.orderSummary = "OrderNumber: " + orderNumber + "\n" + "Deposit: " + deposit + "\n" + "Totalprice: "
                + totalprice + "\n" + "Collectmode: " + collectmode + "\n" + "BikeLocation: " + originalProviderlocation
                + "\n" + "Returnmode: " + returnmode;
        this.provider = quote.getprovider();
        this.bike = quote.quotegetbike();
    }

    // getter
    public int getOrderNumber() {
        return orderNumber;
    }

    public void getOrderSummary() {
        System.out.println(orderSummary);
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public BigDecimal getTotalprice() {
        return totalprice;
    }

    public CollectingAndDeliverMode getcollectMode() {
        return collectmode;
    }

    public ReturnMode getreturnMode() {
        return returnmode;
    }

    public Bike getBike() {
        this.bike = quote.quotegetbike();
        return bike;
    }

    public int getBikeid() {
        return bike.getBikeId();
    }

    public Provider getProvider() {
        this.provider = bike.bikegetProvider();
        return provider;
    }

    public Location getProviderLocation() {
        this.originalProviderlocation = provider.getLocation();
        return originalProviderlocation;
    }

    public Collection<Provider> getPartnerlist() {
        this.parnterlist = provider.getPartnerlist();
        return parnterlist;
    }

}
