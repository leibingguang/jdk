package time;

import org.junit.Test;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class ZoneDemo {
    @Test
    public void zone() {
        // 获取所有可用的时区
        Set<String> allZones = ZoneId.getAvailableZoneIds();
// 按自然顺序排序
// Create a List using the set of zones and sort it.
        List<String> zoneList = new ArrayList<String>(allZones);
        Collections.sort(zoneList);

        LocalDateTime dt = LocalDateTime.now();
        for (String s : zoneList) {
            // 获取到的字符串可以通过ZoneId.of获取实例
            ZoneId zone = ZoneId.of(s);
            // 把本地时间加时区信息 转换成一个ZonedDateTime
            // 但是这个LocalDateTime不包含时区信息，是怎么计算出来的呢？本地时间与这个时区相差n小时？
            // 这里的偏移量是针对 格林威治标准时间来说的 +3 ，就是比标准时间快3个小时
            // 如果说一个时区是 +3;而北京是+8，那么该时区比北京慢5个小时
            // 北京时间是12点，那么该时区12-5 = 7
            ZonedDateTime zdt = dt.atZone(zone);
            ZoneOffset offset = zdt.getOffset();
            int secondsOfHour = offset.getTotalSeconds() % (60 * 60);
            String out = String.format("%35s %10s%n", zone, offset);

            // Write only time zones that do not have a whole hour offset
            // to standard out.
            if (secondsOfHour != 0) {
                System.out.printf(out);
            }
        }
    }

    @Test
    public void zoneDateTime()
    {
        //        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy  hh:mm a");
        DateTimeFormatter format = DateTimeFormatter.ofPattern("YYYY-MM-dd  HH:mm:ss");

        // Leaving from San Francisco on July 20, 2013, at 7:30 p.m.
        //  2013-07-20  19:30:00
        LocalDateTime leaving = LocalDateTime.of(2013, Month.JULY, 20, 19, 30);
        ZoneId leavingZone = ZoneId.of("America/Los_Angeles");
        ZonedDateTime departure = ZonedDateTime.of(leaving, leavingZone);

        try {
            String out1 = departure.format(format);
            System.out.printf("LEAVING:  %s (%s)%n", out1, leavingZone);
        } catch (DateTimeException exc) {
            System.out.printf("%s can't be formatted!%n", departure);
            throw exc;
        }

        // Flight is 10 hours and 50 minutes, or 650 minutes
        ZoneId arrivingZone = ZoneId.of("Asia/Tokyo");
        // 使用美国洛杉矶出发的时间，然后换算成东京的时区，返回该时区对应的时间
        ZonedDateTime arrival = departure.withZoneSameInstant(arrivingZone)
                .plusMinutes(650); // 在该时区的基础上加650分钟

        try {
            String out2 = arrival.format(format);
            System.out.printf("ARRIVING: %s (%s)%n", out2, arrivingZone);
        } catch (DateTimeException exc) {
            System.out.printf("%s can't be formatted!%n", arrival);
            throw exc;
        }

        // 夏令时
        if (arrivingZone.getRules().isDaylightSavings(arrival.toInstant()))
            System.out.printf("  (%s daylight saving time will be in effect.)%n",
                    arrivingZone);
        else
            // 标准时间
            System.out.printf("  (%s standard time will be in effect.)%n",
                    arrivingZone);
    }
}
