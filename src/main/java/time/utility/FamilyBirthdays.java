package time.utility;

import java.time.Month;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;

public class FamilyBirthdays {
    // 只检查月和日
    public static Boolean isFamilyBirthday(TemporalAccessor date) {
        int month = date.get(ChronoField.MONTH_OF_YEAR);
        int day = date.get(ChronoField.DAY_OF_MONTH);

        // Angie's 的生日是4月3号
        if ((month == Month.APRIL.getValue()) && (day == 3))
            return Boolean.TRUE;

        // Sue's 的生日是6月18号
        if ((month == Month.JUNE.getValue()) && (day == 18))
            return Boolean.TRUE;

        // Joe's 的生日是5月29号
        if ((month == Month.MAY.getValue()) && (day == 29))
            return Boolean.TRUE;

        return Boolean.FALSE;
    }
}
