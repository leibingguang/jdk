package time;

import org.junit.Test;

import java.text.ParsePosition;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.TemporalAccessor;

/**
 * 时间格式转换
 */
public class DataFormatterDemo {
    @Test
    public void format() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse("2013-12-31 23:59:59", formatter);
        System.out.println(localDateTime);

    }
}
