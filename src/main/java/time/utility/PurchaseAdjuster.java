package time.utility;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

/**
 * 假设一个场景，一个商店每个月进货两次，
 * 上半月一次，下半月一次，上半月的那次进货是在15号，如果是在下半月，
 * 则是该月的最后一天进货。如果进货的那天恰逢周六周日，则提前到该周周五进货（货车司机也要双休嘛），
 * 那么如何自定义一个调节器，计算下一次的进货时间呢？
 */
public class PurchaseAdjuster implements TemporalAdjuster {
    @Override
    public Temporal adjustInto(Temporal temporal) {
        LocalDate date = LocalDate.from(temporal);
        int day;
        if (date.getDayOfMonth() < 15) {
            day = 15;
        } else {
            day = date.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();
        }
        date = date.withDayOfMonth(day);
        if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
            date = date.with(TemporalAdjusters.previous(DayOfWeek.FRIDAY));
        }
        return date;
    }
}
