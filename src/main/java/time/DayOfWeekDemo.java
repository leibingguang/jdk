package time;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Locale;

public class DayOfWeekDemo {
    @Test
    public void getDisplayName() {
        DayOfWeek dow = DayOfWeek.MONDAY;
        Locale locale = Locale.getDefault();
        System.out.println(locale.getDisplayCountry());;
        // 星期一
        System.out.println(dow.getDisplayName(TextStyle.FULL, locale));
        // 一
        System.out.println(dow.getDisplayName(TextStyle.NARROW, locale));
        // 星期一
        System.out.println(dow.getDisplayName(TextStyle.SHORT, locale));
    }
}
