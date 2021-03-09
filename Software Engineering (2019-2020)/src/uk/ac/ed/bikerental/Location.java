package uk.ac.ed.bikerental;

/**
 * @author s1826390 Location
 */
public class Location {
    private String postcode;
    private String address;

    /**
     * 
     * @param String postcode
     * @param String address
     */
    public Location(String postcode, String address) {
        assert postcode.length() >= 6;
        this.postcode = postcode;
        this.address = address;
    }

    /**
     * 
     * @param Location other
     * @return boolean
     */
    public boolean isNearTo(Location other) {
        // TODO: Implement Location.isNearTo
        if (postcode.substring(0, 2).equals(other.getPostcode().substring(0, 2))) {
            return true;
        }
        assert other != null;
        return false;
    }

    /**
     * 
     * @return String postcode
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * 
     * @return String address
     */
    public String getAddress() {
        return address;
    }

    // You can add your own methods here
}
