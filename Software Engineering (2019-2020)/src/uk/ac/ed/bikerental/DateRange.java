package uk.ac.ed.bikerental;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * 
 * @author s1826390
 *
 */
public class DateRange {
    private LocalDate start, end;

    /**
     * 
     * @param LocalDate start
     * @param LocalDate end
     */
    public DateRange(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }

    /**
     * 
     * @return LocalDate start
     */
    public LocalDate getStart() {
        return this.start;
    }

    /**
     * 
     * @return LocalDate end
     */
    public LocalDate getEnd() {
        return this.end;
    }

    /**
     * 
     * @return long ChronoUnit.YEARS.between(this.getStart(), this.getEnd())
     */
    public long toYears() {
        return ChronoUnit.YEARS.between(this.getStart(), this.getEnd());
    }

    /**
     * 
     * @return long return ChronoUnit.DAYS.between(this.getStart(), this.getEnd());
     */
    public long toDays() {
        return ChronoUnit.DAYS.between(this.getStart(), this.getEnd());
    }

    /**
     * 
     * @param DateRange other
     * @return Boolean
     */
    public Boolean overlaps(DateRange other) {
        // TODO: implement date range intersection checking
        LocalDate others = other.getStart();
        LocalDate othere = other.getEnd();
        boolean a1 = othere.isBefore(start);
        boolean a2 = end.isBefore(others);
        if (a1 || a2) {
            assert other != null;
            return false;
        }
        return true;
    }

    /**
     * @return int Objects.hash(end, start)
     */
    @Override
    public int hashCode() {
        // hashCode method allowing use in collections
        return Objects.hash(end, start);
    }

    /**
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        // equals method for testing equality in tests
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DateRange other = (DateRange) obj;
        return Objects.equals(end, other.end) && Objects.equals(start, other.start);
    }

    // You can add your own methods here
}
