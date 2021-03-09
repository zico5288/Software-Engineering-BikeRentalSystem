package uk.ac.ed.bikerental;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.*;

public class PricingPolicyTests {
    // You can add attributes here
    private Location location1;
    private Provider provider1;
    private BigDecimal replacementvalue1;
    private BikeType biketype1;
    private Bike bike1;
    private BigDecimal dailyprice1;

    private Location location2;
    private Provider provider2;
    private BigDecimal replacementvalue2;
    private BikeType biketype2;
    private Bike bike2;
    private BigDecimal dailyprice2;

    private Collection<Bike> bikes = new ArrayList<Bike>();
    private LocalDate start1;
    private LocalDate end1;
    private DateRange duration1;
    private LocalDate start2;
    private LocalDate end2;
    private DateRange duration2;
    private LocalDate start3;
    private LocalDate end3;
    private DateRange duration3;
    private LocalDate start4;
    private LocalDate end4;
    private DateRange duration4;
    private Multidayratediscountpolicy Multidayratediscountpolicy;

    @BeforeEach
    void setUp() throws Exception {
        // Put setup here
        // set bikeType
        this.location1 = new Location("EH3 9FE", "chalmers street");
        this.provider1 = new Provider(location1);
        this.replacementvalue1 = new BigDecimal(20);
        this.biketype1 = new BikeType("ebike", provider1, replacementvalue1);

        this.location2 = new Location("EH3 9FE", "chalmers street");
        this.provider2 = new Provider(location2);
        this.replacementvalue2 = new BigDecimal(20);
        this.biketype2 = new BikeType("mountainbike", provider2, replacementvalue2);
        // set bike
        this.bike1 = new Bike(biketype1, provider1, 1);
        this.dailyprice1 = new BigDecimal(50);
        this.bike2 = new Bike(biketype2, provider2, 2);
        this.dailyprice2 = new BigDecimal(60);

        // set collection<bike> bikes
        this.bikes.add(bike1);
        this.bikes.add(bike2);

        // set duration
        this.start1 = LocalDate.of(2019, 11, 1);
        this.end1 = LocalDate.of(2019, 11, 19);
        this.duration1 = new DateRange(start1, end1);

        this.start2 = LocalDate.of(2019, 11, 1);
        this.end2 = LocalDate.of(2019, 11, 13);
        this.duration2 = new DateRange(start2, end2);

        this.start3 = LocalDate.of(2019, 11, 1);
        this.end3 = LocalDate.of(2019, 11, 5);
        this.duration3 = new DateRange(start3, end3);

        this.start4 = LocalDate.of(2019, 11, 1);
        this.end4 = LocalDate.of(2019, 11, 2);
        this.duration4 = new DateRange(start4, end4);

        // setDailyRentalPrice
        this.Multidayratediscountpolicy = new Multidayratediscountpolicy();
        Multidayratediscountpolicy.setDailyRentalPrice(biketype1, dailyprice1);
        Multidayratediscountpolicy.setDailyRentalPrice(biketype2, dailyprice2);
    }

    @Test
    public void multidayratediscountpolicy1() {
        assertEquals(new BigDecimal(1683.0).setScale(1, BigDecimal.ROUND_HALF_DOWN),
                Multidayratediscountpolicy.calculatePrice(bikes, duration1));

    }

    @Test
    public void multidayratediscountpolicy2() {
        assertEquals(new BigDecimal(1188.0).setScale(1, BigDecimal.ROUND_HALF_DOWN),
                Multidayratediscountpolicy.calculatePrice(bikes, duration2));
    }

    @Test
    public void multidayratediscountpolicy3() {
        assertEquals(new BigDecimal(416), Multidayratediscountpolicy.calculatePrice(bikes, duration3));

    }

    @Test
    public void multidayratediscountpolicy4() {

        assertEquals(new BigDecimal(110), Multidayratediscountpolicy.calculatePrice(bikes, duration4));

    }

    // TODO: Write tests for pricing policies

}