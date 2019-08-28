package time;

import org.junit.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

/**
 * Date-Time API 的核心类之一是 Instant 类，它表示时间轴上的纳秒开始。此类对于生成表示机器时间的时间戳很有用。
 */
public class InstantDemo {
    @Test
    public void instant() {
        LocalDateTime ldt = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
        System.out.println(ldt);
// MAY 8 2018 at 13:37
        System.out.printf("%s %d %d at %d:%d%n", ldt.getMonth(), ldt.getDayOfMonth(),
                ldt.getYear(), ldt.getHour(), ldt.getMinute());
        Instant oneHourLater = Instant.now().plus(1, ChronoUnit.HOURS);//当前时间增加1小时
        System.out.println(oneHourLater);
        long secondsFromEpoch = Instant.ofEpochSecond(0L).until(Instant.now(), ChronoUnit.SECONDS);//1970年1月1日到现在的秒数
        System.out.println(secondsFromEpoch);

        LocalDateTime start = LocalDateTime.of(2018, 05, 01, 0, 0, 0);
        LocalDateTime end = LocalDateTime.of(2018, 05, 8, 0, 0, 0);
// 两个时间之间相差了7天
        long until = start.until(end, ChronoUnit.DAYS); // 还有其他时间类都提供了 unitl方法
        System.out.println(until);
    }
}
