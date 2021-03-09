package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class DoubleDecliningBalanceDepreciation implements ValuationPolicy {
    private BigDecimal depreciationRate;

    public DoubleDecliningBalanceDepreciation(BigDecimal d) {
        this.depreciationRate = d;
    }

    public BigDecimal getDoubleDecliningBalanceDepreciation() {
        return depreciationRate;
    }

    public void setDoubleDecliningBalanceDepreciation(BigDecimal dR) {
        this.depreciationRate = dR;
    }

    @Override
    public BigDecimal calculateValue(Bike bike, LocalDate date) {
        BikeType b = bike.getType();
        BigDecimal replaceValue = b.getReplacementValue();
        LocalDate l1 = bike.getManuDate();

        assert (l1.isBefore(date) || l1.isEqual(date)) : "The input date is invalid";
        int year1 = date.getYear() - l1.getYear();

        BigDecimal one = new BigDecimal(1);
        BigDecimal two = new BigDecimal(2);
        BigDecimal result1 = one.subtract(two.multiply(depreciationRate));
        BigDecimal resultPow = result1.pow(year1);
        BigDecimal result = replaceValue.multiply(resultPow);

        BigDecimal r = result.setScale(2, RoundingMode.HALF_UP);

        return r;

    }
}
