package time;

import org.junit.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoField;

public class LocalDateAndTime {
    @Test
    public void localDate()
    {
        LocalDate localDate = LocalDate.of(2019, 8, 28);
//        LocalDateDemo localDate = LocalDateDemo.now();

        int year = localDate.getYear();
        localDate.get(ChronoField.YEAR);
        int month = localDate.getMonthValue();
        int dayOfMonth = localDate.getDayOfMonth();
        int dayOfYear = localDate.getDayOfYear();
        int dayOfWeek = localDate.getDayOfWeek().getValue();
        boolean isLeapYear = localDate.isLeapYear();


        Instant instant = Instant.ofEpochSecond(3);
        year = instant.get(ChronoField.YEAR);//报错，无法处理便于阅读的时间
    }
}
