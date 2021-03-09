package uk.ac.ed.bikerental;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.util.*;

public class SystemTests {
    // You can add attributes here

    // provider1
    private Location location1, location2;
    private Provider provider1, provider2;
    private BikeType biketype1, biketype2, biketype3;
    private Bike bike1, bike2, bike3;
    private BigDecimal replacementValue1, replacementValue2, replacementValue3;
    private BigDecimal dailyrentprice1, dailyrentprice2, dailyrentprice3;
    private BigDecimal depositrate1, depositerate2;
    private Collection<Quote> quotelist = new ArrayList<Quote>();;
    private Collection<Provider> providers = new ArrayList<Provider>();;

    // customer
    private Location customerloc;
    private Customer customer1;
    private LocalDate start, end;
    private DateRange bookingdate1;
    private Location hireloc;
    private int bookingdays;

    @BeforeEach
    void setUp() throws Exception {
        // Setup mock delivery service before each tests
        DeliveryServiceFactory.setupMockDeliveryService();

        // Put your test setup here
        // set provider1Quote
        this.location1 = new Location("eh3 9fe", "chalmers street");
        this.provider1 = new Provider(location1);
        this.depositrate1 = new BigDecimal(0.90);
        // quote1
        this.replacementValue1 = new BigDecimal(500);
        this.biketype1 = new BikeType("ebike", provider1, replacementValue1);
        this.bike1 = new Bike(biketype1, provider1, 1);
        this.dailyrentprice1 = new BigDecimal(500);

        // quote2
        this.replacementValue2 = new BigDecimal(600);
        this.biketype2 = new BikeType("mountainbike", provider1, replacementValue2);
        this.bike2 = new Bike(biketype2, provider1, 2);
        this.dailyrentprice2 = new BigDecimal(600);

        // set provider2
        this.location2 = new Location("ef3 9fe", "beijing street");
        this.provider2 = new Provider(location2);
        this.depositerate2 = new BigDecimal(0.80);
        // set provider2quote1
        this.replacementValue3 = new BigDecimal(700);
        this.biketype3 = new BikeType("mountainbike", provider2, replacementValue3);
        this.bike3 = new Bike(biketype3, provider2, 3);
        this.dailyrentprice3 = new BigDecimal(700);

        providers.add(provider1);
        providers.add(provider2);
        // set Customer
        this.customerloc = new Location("eh3 9fe", "chalmers street");
        this.customer1 = new Customer(customerloc);
        this.start = LocalDate.of(2019, 11, 1);
        this.end = LocalDate.of(2019, 11, 19);
        this.bookingdate1 = new DateRange(start, end);
        this.hireloc = new Location("eh3 9fe", "chalmers street");
        this.bookingdays = (int) bookingdate1.toDays();
        this.quotelist = customer1.getQuote("ebike", bookingdate1, hireloc, providers);

        provider1.setDailyrentprice(biketype1, dailyrentprice1);
        provider1.setDailyrentprice(biketype2, dailyrentprice2);
        provider1.provideQuote(bike1);
        provider1.provideQuote(bike2);
        provider1.setReplacementvalue(biketype1, replacementValue1);
        provider1.setReplacementvalue(biketype2, replacementValue2);
        provider1.setDepositRate(depositrate1);
        provider1.setdeposit(bike1);
        provider1.setdeposit(bike2);
        bike1.setdeposit();
        bike2.setdeposit();

        provider2.setDailyrentprice(biketype3, dailyrentprice3);
        provider2.provideQuote(bike3);
        provider2.setDailyrentprice(biketype3, replacementValue3);
        provider2.setDepositRate(depositerate2);

    }

    // TODO: Write system tests covering the three main use cases

    // getQuote

    // The test of 'GetQuote' for quoteNumber
    @Test
    public void TestGetquoteNum() {
        // JUnit tests look like this
        assertEquals(1, customer1.getQuote("ebike", bookingdate1, hireloc, providers).size()); // Should fail
    }

    // The test of 'GetQuote' for PostCode
    @Test
    public void TestGetQuotePostCode() {
        this.quotelist = customer1.getQuote("ebike", bookingdate1, hireloc, providers);
        StringBuffer stringBuffer = new StringBuffer();
        for (Quote quote : quotelist) {
            stringBuffer.append(quote.getprovider().getLocation().getPostcode());
        }
        assertEquals("eh3 9fe", stringBuffer.toString());
    }

    // The test of 'GetQuote' for BikeAddress
    @Test
    public void TestGetQuoteBikeAddress() {
        this.quotelist = customer1.getQuote("ebike", bookingdate1, hireloc, providers);
        StringBuffer stringBuffer = new StringBuffer();
        for (Quote quote : quotelist) {
            stringBuffer.append(quote.getprovider().getLocation().getAddress());
        }
        assertEquals("chalmers street", stringBuffer.toString());
    }

    // The test of 'GetQuote' for BikeDeposite
    @Test
    public void TestGetQuoteBikeDeposite() {
        this.quotelist = customer1.getQuote("ebike", bookingdate1, hireloc, providers);
        for (Quote quote : quotelist) {
            BigDecimal deposit = quote.getreplacementvalue().multiply(depositrate1).round(new MathContext(3));
            assertEquals(new BigDecimal(450), deposit);
        }
    }

    // The test of 'GetQuote' for BikeTotalPrice
    @Test
    public void TestGetQuoteBikeTotalPrice() {
        this.quotelist = customer1.getQuote("ebike", bookingdate1, hireloc, providers);
        for (Quote quote : quotelist) {
            assertEquals(new BigDecimal(9000), quote.gettotalprice());
        }

    }
    // BookQuote

    // The test of 'BookQuote' for OrderNumber
    @Test
    public void TestBookQuoteOrderNumber() {
        customer1.setcollectModeToStoreCollection();
        customer1.setreturnModeToOriginalProvider();
        this.quotelist = customer1.getQuote("ebike", bookingdate1, hireloc, providers);
        Confirmation a = customer1.bookquote(quotelist, 1);
        assertEquals(1, a.getOrderNumber());
    }

    @Test
    // The test of 'BookQuote' for Deposit
    public void TestBookQuoteDeposit() {
        customer1.setcollectModeToStoreCollection();
        customer1.setreturnModeToOriginalProvider();
        this.quotelist = customer1.getQuote("ebike", bookingdate1, hireloc, providers);
        Confirmation a = customer1.bookquote(quotelist, 1);
        BigDecimal deposit = a.getBike().getdeposit().round(new MathContext(3));
        assertEquals(new BigDecimal(450), deposit);
    }

    @Test
    // The test of 'BookQuote' for TotalPrice
    public void TestBookQuoteTotalPrice() {
        customer1.setcollectModeToStoreCollection();
        customer1.setreturnModeToOriginalProvider();
        this.quotelist = customer1.getQuote("ebike", bookingdate1, hireloc, providers);
        Confirmation a = customer1.bookquote(quotelist, 1);
        BigDecimal totoalprice = a.getTotalprice().round(new MathContext(5));
        assertEquals(new BigDecimal(9000), totoalprice);
    }

    @Test
    // The test of 'BookQuote' for collectMode
    public void TestBookQuotegetcollectMode() {
        customer1.setcollectModeToStoreCollection();
        customer1.setreturnModeToOriginalProvider();
        this.quotelist = customer1.getQuote("ebike", bookingdate1, hireloc, providers);
        Confirmation a = customer1.bookquote(quotelist, 1);
        assertEquals(CollectingAndDeliverMode.StoreCollection, a.getcollectMode());
    }

    @Test
    // The test of 'BookQuote' for returnMode
    public void TestBookQuotegetreturnMode() {
        customer1.setcollectModeToStoreCollection();
        customer1.setreturnModeToOriginalProvider();
        this.quotelist = customer1.getQuote("ebike", bookingdate1, hireloc, providers);
        Confirmation a = customer1.bookquote(quotelist, 1);
        assertEquals(ReturnMode.OriginalProvider, a.getreturnMode());
    }

    @Test
    // The test of 'BookQuote' for bike information
    public void TestBookQuoteGetbike() {
        customer1.setcollectModeToStoreCollection();
        customer1.setreturnModeToOriginalProvider();
        this.quotelist = customer1.getQuote("ebike", bookingdate1, hireloc, providers);
        Confirmation a = customer1.bookquote(quotelist, 1);
        assertEquals(bike1, a.getBike());
    }

    @Test
    // The test of 'BookQuote' for provider information
    public void TestBookQuoteGetProvider() {
        customer1.setcollectModeToStoreCollection();
        customer1.setreturnModeToOriginalProvider();
        this.quotelist = customer1.getQuote("ebike", bookingdate1, hireloc, providers);
        Confirmation a = customer1.bookquote(quotelist, 1);
        assertEquals(provider1, a.getProvider());
    }

    // returnbike

    @Test
    // The test of 'returnbike' for BikeStatus
    public void TestReturnBikeGetStatus() {
        customer1.setcollectModeToStoreCollection();
        customer1.setreturnModeToOriginalProvider();
        this.quotelist = customer1.getQuote("ebike", bookingdate1, hireloc, providers);
        Confirmation a = customer1.bookquote(quotelist, 1);
        customer1.returnBike(a.getBikeid(), provider1);
        assertEquals(BikeStatuse.Tracking, a.getBike().getBikeStatus());
    }

}
