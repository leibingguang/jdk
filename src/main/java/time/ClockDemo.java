package time;

import org.junit.Test;

import java.time.Clock;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ClockDemo {
    @Test
    public void systemUTC() {
        System.out.println(Clock.systemUTC().instant());
        Date date = new Date();
        System.out.println(System.currentTimeMillis());
        date.setTime(System.currentTimeMillis());
        System.out.println(date);
    }

    @Test
    public void systemDefaultZone()
    {
        Clock clock = Clock.systemDefaultZone();
        System.out.println(clock);
    }
    @Test
    public void chicagoClock() {
        Clock chicagoClock = Clock.system(ZoneId.of(ZoneId.SHORT_IDS.get("CST")));
        System.out.println(chicagoClock);
        System.out.println(chicagoClock.millis());
        System.out.println(System.currentTimeMillis());
        System.out.println(chicagoClock.instant());
        System.out.println(Clock.systemUTC().instant());
    }

    /**
     * 剔除秒
     */
    @Test
    public void tickSeconds() {
        Clock clock = Clock.systemUTC();
        Clock tickClock = Clock.tickSeconds(ZoneId.of(ZoneId.SHORT_IDS.get("CST")));
        System.out.println(clock.instant());
        System.out.println(tickClock.instant());
    }

    /**
     * 剔除分钟
     */
    @Test
    public void tickMinutes() {
        Clock clock = Clock.systemUTC();
        Clock tickClock = Clock.tickMinutes(ZoneId.of(ZoneId.SHORT_IDS.get("CST")));
        System.out.println(clock.instant());
        System.out.println(tickClock.instant());
    }

    /**
     * 剔除总时间/持续时间的余数
     */
    @Test
    public void tick() {
        Clock clock = Clock.systemUTC();
        Duration duration = Duration.from(Duration.ofMinutes(992));
        Clock tickClock = Clock.tick(clock, duration);
        System.out.println(duration.toNanos());
        System.out.println(clock.instant());
        System.out.println(tickClock.instant());
    }

    /**
     * 时钟设置固定时间
     */
    @Test
    public void fixed() {
        Clock clock = Clock.fixed(Clock.systemUTC().instant(), ZoneId.of(ZoneId.SHORT_IDS.get("CST")));
        System.out.println(clock.instant());
    }

    /**
     * 设置偏移量
     */
    @Test
    public void offset() {
        System.out.println(Clock.systemUTC().instant());
        Clock clock = Clock.offset(Clock.systemUTC(), Duration.ofHours(1));
        System.out.println(clock.instant());
    }

}
