package uk.ac.ed.bikerental;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class TestLocation {
    Location location;
    Location location2;

    @BeforeEach
    void setUp() throws Exception {
        // TODO: setup some resources before each test
        location = new Location("EH7 5BH", "29 montgomer street");
        location2 = new Location("EH8 7YT", "29 montgomer street");

    }

    // TODO: put some tests here
    @Test
    public void testLocation() {
        assertEquals(true, location.isNearTo(location2));
    }
}
