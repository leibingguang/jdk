package time;

import org.junit.Test;
import sun.awt.windows.WToolkit;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.chrono.MinguoDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;

public class LocalDateDemo {
    /**
     * 获取当前日期，默认为系统时区，可通过Clock/ZoneId指定时区
     */
    @Test
    public void now() {
        System.out.println(LocalDate.now());
        //获取特定时区的日期
        System.out.println(LocalDate.now(ZoneId.of(ZoneId.SHORT_IDS.get("HST"))));
        System.out.println(LocalDate.now(Clock.system(ZoneId.of(ZoneId.SHORT_IDS.get("HST")))));
    }

    /**
     * 创建LocalDate的不同方式
     */
    @Test
    public void initLocalDate() {
        LocalDate localDate = LocalDate.of(2019, 8, 28);
        System.out.println("of: " + localDate);
        localDate = LocalDate.of(2019, Month.AUGUST, 28);
        System.out.println("of: " + localDate);
        localDate = LocalDate.now();
        System.out.println("now : " + localDate);
        localDate = LocalDate.now(Clock.systemDefaultZone());
        System.out.println("now with Clock: " + localDate);
        localDate = LocalDate.now(ZoneId.of("Asia/Shanghai"));
        System.out.println("now with ZoneId: " + localDate);
        localDate = LocalDate.from(MinguoDate.now());
        System.out.println("from: " + localDate);
        localDate = LocalDate.ofEpochDay(365);//1970 Unix元年开始+天数
        System.out.println("ofEpochDay: " + localDate);
        localDate = LocalDate.ofYearDay(2019, 200);//年份+往后数N天
        System.out.println("ofYearDate: " + localDate);
        //通过时间字符串创建LocalDate实例
        localDate = LocalDate.parse("2019-08-28");
        System.out.println("parse: " + localDate);
        localDate = LocalDate.parse("20190828", DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println("parse with DateTimeFormatter: " + localDate);
    }

    @Test
    public void adjustInto() {
        LocalDate localDate = LocalDate.of(2011, 8, 29);
        LocalDate localDate1 = LocalDate.of(2019, 1, 1);
        Temporal temporal = localDate.adjustInto(localDate1);//调整指定的 Temporal（接口） 和 LocalDate 有相同的日期。
        System.out.println(localDate);
        System.out.println(localDate1);
        System.out.println(temporal);
    }

    /**
     * 获取当天的0点0分
     */
    @Test
    public void atStartOfDay() {
        LocalDate localDate = LocalDate.of(2011, 8, 29);
        LocalDateTime localDateTime = localDate.atStartOfDay();//获取
        System.out.println(localDateTime);
        ZonedDateTime localDateTimeWithZoneId = localDate.atStartOfDay(ZoneId.of("Europe/Paris"));//转换成区域时间
        System.out.println(localDateTimeWithZoneId);
    }

    @Test
    public void atTime() {
        LocalDate localDate = LocalDate.of(2011, 8, 29);
        LocalDateTime localDateTime = localDate.atTime(12, 14);
        System.out.println(localDateTime);
        localDateTime = localDate.atTime(LocalTime.now());
        System.out.println(localDateTime);
        OffsetDateTime offsetDateTime = localDate.atTime(OffsetTime.now());
        System.out.println(offsetDateTime);
    }


    @Test
    public void with() {
        // 2000年11月20日 星期一
        LocalDate date = LocalDate.of(2000, Month.NOVEMBER, 20);
        // 当前指定日期的下一个 星期三
        LocalDate nextWed = date.with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY));
        // 当前指定日期的下一个 星期一
        LocalDate nextMond = date.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        System.out.println(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(date));
        System.out.println(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(nextWed));
        System.out.println(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(nextMond));
    }

    @Test
    public void yearMonth() {
        // 2018-05: 31
        YearMonth date = YearMonth.now();
        System.out.printf("%s: %d%n", date, date.lengthOfMonth());
        //2010-02: 28
        YearMonth date2 = YearMonth.of(2010, Month.FEBRUARY);
        System.out.printf("%s: %d%n", date2, date2.lengthOfMonth());
        // 2012-02: 29
        YearMonth date3 = YearMonth.of(2012, Month.FEBRUARY);
        System.out.printf("%s: %d%n", date3, date3.lengthOfMonth());
        // 2012-02: 366  // 返回该年有多少天
        System.out.printf("%s: %d%n", date3, date3.lengthOfYear());
    }

    /**
     * 判断时间是否有效
     */
    @Test
    public void monthDay() {
        // 2月29号
        MonthDay date = MonthDay.of(Month.FEBRUARY, 29);
        // 对于 2010 年是否是有效的时间
        boolean validLeapYear = date.isValidYear(2010);
    }

    @Test
    public void year() {
        /**
         * 判断是否闰年
         */
        boolean validLeapYear = Year.of(2012).isLeap();
    }

    /**
     * 纪元时间（1970.1.1）+指定天数
     */
    @Test
    public void ofEpochDay() {
        System.out.println(LocalDate.ofEpochDay(365));
    }

    /**
     * 获取指定Field的范围
     */
    @Test
    public void range() {
        System.out.println(LocalDate.of(2019, 1, 1).range(ChronoField.YEAR_OF_ERA));
    }

    /**
     * 获取此日期的时间顺序，即ISO日历系统
     */
    @Test
    public void getChronology() {
        System.out.println(LocalDate.of(2019, 1, 1).getChronology());
    }

    @Test
    public void getEra()
    {
        System.out.println(LocalDate.of(2019, 1, 1).getEra());

    }
}
