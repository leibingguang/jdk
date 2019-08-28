package time;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;

/**
 * 在测量机器时间的情况下，Duration 最合适，例如使用 Instant 对象的代码。Duration 对象以秒或纳秒度量， 不使用基于 Date
 * 的结构，如年、月和日，尽管类提供了转换为天数、小时和分钟的方法。一个 Duration 可以有一个负值， 如果它是在开始点之前发生的端点创建的。
 */
public class DurationDemo {
    /**
     * 将 10 秒变成一个 Duration，再让 start 加上这个 Duration，也就是加 10 秒；
     * 这种 api 可能会更灵活，在计算完两个时间点的持续时间后，还可以进行计算
     */
    @Test
    public void duration() {
        Instant start = Instant.now();
        Duration gap = Duration.ofSeconds(10);
        Instant later = start.plus(gap);
        System.out.println(later);
    }

    /**
     * 义了用于测量时间的单位。当你想要在一个单位的时间内测量一段时间，比如几天或几秒时， ChronoUnit.between 可以做到。
     * between 方法与所有基于时间的对象一起工作，但是它只返回单个单元的数量。 下面的代码以毫秒为间隔计算两个时间戳之间的差距：
     */
    @Test
    public void chronoUnit() {
        Instant current = Instant.now();
// 10秒前
        Instant previous = current.minus(10, ChronoUnit.SECONDS);
        if (previous != null) {
            // 计算两个时间之前间隔多少毫秒
            long between = ChronoUnit.MILLIS.between(previous, current);
            System.out.println(between); // 10000
        }
    }

    @Test
    public void period() {
        LocalDate today = LocalDate.now();
// 1960.06.01
        LocalDate birthday = LocalDate.of(1960, Month.JANUARY, 1);

        Period p = Period.between(birthday, today);
        long p2 = ChronoUnit.DAYS.between(birthday, today);
// 生活了58年，4个月，8天，总共21313天
        System.out.println("You are " + p.getYears() + " years, " + p.getMonths() +
                " months, and " + p.getDays() +
                " days old. (" + p2 + " days total)");
    }

}
