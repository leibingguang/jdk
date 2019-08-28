package time;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;
import java.util.stream.Stream;

public class TimeTest {
    /**
     * 写一个例子，给定一个年份，打印出该年中每个月有多少天
     */
    @Test
    public void daysOfMonth() {
        Stream.of(Month.values())
                .map(month -> LocalDate.of(2019, month, 1))
                .forEach(localDate -> {
                    System.out.println("month " + localDate.getMonthValue() + " days " + localDate.lengthOfMonth());
                });
    }

    /**
     * 写一个例子，在当年的某个特定月份，列出当月的所有星期一。
     */
    @Test
    public void allMondayInMonth() {
//        Stream.of(LocalDate.now()).map()
        LocalDate now = LocalDate.now();
        LocalDate monday = now.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
        while (monday.getMonth() == now.getMonth()) {
            System.out.println(monday);
            monday = monday.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        }
    }

    /**
     * 写一个例子，测试一个给定的日期是否发生在 13 日星期五。
     */
    @Test
    public void query() {
        // 假设给定5月13号，判断该日期是否是13号并且还是星期五
        int m = 5;
        int day = 13;

        LocalDate date = Year.now().atMonth(m).atDay(day);
        // 使用查询方式来处理是最方便的
        Boolean query = date.query(temporal -> {
            int dom = temporal.get(ChronoField.DAY_OF_MONTH);
            int dow = temporal.get(ChronoField.DAY_OF_WEEK);
            return dom == 13 && dow == 5;
        });
        System.out.println(query);
    }
    /**
     * 扩展练习：找一年中是13号又是星期5的日期
     */
}
