package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class LinearDepreciation implements ValuationPolicy {
    BigDecimal value;
    BigDecimal depreciationRate;

    public LinearDepreciation(BigDecimal d) {
        this.depreciationRate = d;

    }

    public BigDecimal getDepreciationRate() {
        return depreciationRate;
    }

    public void setDepreciationRate(BigDecimal depreciationRate) {
        this.depreciationRate = depreciationRate;
    }

    @Override
    public BigDecimal calculateValue(Bike bike, LocalDate date) {
        BikeType b = bike.getType();
        BigDecimal replaceValue = b.getReplacementValue();
        LocalDate l1 = bike.getManuDate();

        assert (l1.isBefore(date) || l1.isEqual(date)) : "The input date is invalid";
        int year1 = date.getYear() - l1.getYear();
        this.value = replaceValue;

        BigDecimal r = replaceValue.multiply(depreciationRate);
        String year2 = Integer.toString(year1);
        BigDecimal year = new BigDecimal(year2);
        BigDecimal r2 = r.multiply(year);
        BigDecimal r3 = value.subtract(r2);

        BigDecimal result = r3.setScale(2, RoundingMode.HALF_UP);

        return result;

    }

}
