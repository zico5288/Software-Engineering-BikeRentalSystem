package uk.ac.ed.bikerental;

import java.math.BigDecimal;

public class BikeType {
    private String str;
    private BigDecimal replacementvalue;
    private Provider provider;

    public BikeType(String str, Provider provider, BigDecimal replacementvalue) {
        this.str = str;
        this.replacementvalue = replacementvalue;
        this.provider = provider;
    }

    // getter
    public Provider getProvider() {
        return provider;
    }

    public String getname() {
        return str;
    }

    // set replacementvalue
    public BigDecimal getReplacementValue() {
        // TODO: Implement Bike.getReplacementValue
        return replacementvalue;
    }

    // can see typename in outprint
    public void getTypeName() {
        System.out.println(str);
    }
}