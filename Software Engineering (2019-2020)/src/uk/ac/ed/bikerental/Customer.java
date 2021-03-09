package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.*;

public class Customer {
    private int CustomerId = 0;
    private Location Customeraddress;
    private DateRange bookingdate;
    private Collection<Bike> bookinglist = new ArrayList<>();
    private Collection<Quote> returnquotelist = new ArrayList<>();
    private CollectingAndDeliverMode collectmode;
    private ReturnMode returnmode;
    private Location hirelocation;

    public Customer(Location Customeraddress) {
        this.CustomerId = CustomerId + 1;
        this.Customeraddress = Customeraddress;
    }

    // getter&setter
    public Location getLocation() {
        return Customeraddress;
    }

    public int getCustomerId() {
        return CustomerId;
    }

    // get providerInfo
    public Provider getProvider(Bike bike) {
        return bike.bikegetProvider();
    }

    public Collection<Provider> getparnterProvider(Bike bike) {
        return getProvider(bike).getPartnerlist();
    }

    // Mode
    public void setcollectModeToStoreCollection() {
        assert (getcollectMode() != CollectingAndDeliverMode.StoreCollection);
        this.collectmode = CollectingAndDeliverMode.StoreCollection;
    }

    public void setcollectModeToDelivery() {
        assert (getcollectMode() != CollectingAndDeliverMode.Delivery);
        this.collectmode = CollectingAndDeliverMode.Delivery;
    }

    public void setreturnModeToOriginalProvider() {
        assert (getreturnMode() != ReturnMode.OriginalProvider);
        this.returnmode = ReturnMode.OriginalProvider;
    }

    public void setreturnModeToPartnerProvider() {
        assert (getreturnMode() != ReturnMode.PartnerProvider);
        this.returnmode = ReturnMode.PartnerProvider;
    }

    public CollectingAndDeliverMode getcollectMode() {
        return collectmode;
    }

    public ReturnMode getreturnMode() {
        return returnmode;
    }

    // get quote
    public Collection<Quote> getQuote(String biketypename, DateRange bookingdate, Location hirelocation,
            Collection<Provider> providers) {
        this.hirelocation = hirelocation;
        this.bookingdate = bookingdate;
        for (Provider provider : providers) {
            for (Quote quote : provider.getQuotelist()) {
                if (biketypename.equals(quote.getbiketype().getname()) &&
                // (provider.getBikeStatus(quote.getbike()) == BikeStatuse.InStock) &&
                        hirelocation.isNearTo(quote.getprovider().getLocation())) {
                    returnquotelist.add(quote);
                    BigDecimal days = new BigDecimal(getNumberbookingdays());
                    BigDecimal totalprice = days.multiply(provider.getDailyrentprice(quote.getbiketype()));
                    quote.settotalprice(totalprice);
                }
            }
        }
        return returnquotelist;
    }

    // book quote
    public Confirmation bookquote(Collection<Quote> quotelist, int bikeId) {
        for (Quote quote : quotelist) {
            if (bikeId == quote.quotegetBikeId()) {
                assert (quote.quotegetbike().getBikeStatus() == BikeStatuse.InStock);
                quote.quotegetbike().setDateRange(bookingdate);
                bookinglist.add(quote.quotegetbike());
                quote.quotegetbike().setBikeStatusToRenting();
                Confirmation confirmation = new Confirmation(quote, getcollectMode(), getreturnMode());
                if (getcollectMode() == CollectingAndDeliverMode.Delivery) {
                    assert hirelocation.isNearTo(confirmation.getProviderLocation());
                    DeliveryServiceFactory.getDeliveryService().scheduleDelivery(quote.quotegetbike(), getLocation(), hirelocation, bookingdate.getStart());// delivery when CollectingAndDeliverMode.Delivery
                }
                return confirmation;
            }
        }
        return null;
    }

    // daterange inyears
    public long getNumberbookingyears() {
        return bookingdate.toYears();
    }

    // daterange indays
    public long getNumberbookingdays() {
        return bookingdate.toDays();
    }

    // return bike
    public void returnBike(int bikeId, Provider provider) {
        for (Bike returnbike : bookinglist) {
            if (returnbike.getBikeId() == bikeId) {
                assert (bookinglist.contains(returnbike));
                assert (returnbike.getBikeStatus() == BikeStatuse.Renting);
                bookinglist.remove(returnbike);
                returnbike.setBikeStatusToTracking();
                DeliveryServiceFactory.getDeliveryService().scheduleDelivery(returnbike, getLocation(), provider.getLocation(), bookingdate.getEnd());
                break;
            }
        }
    }

}
