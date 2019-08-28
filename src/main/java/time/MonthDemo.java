package time;

import org.junit.Test;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

public class MonthDemo {
    @Test
    public void getDisplayName()
    {
        Month month = Month.AUGUST;
        Locale locale = Locale.getDefault();
        System.out.println(month.getDisplayName(TextStyle.FULL, locale));
        System.out.println(month.getDisplayName(TextStyle.NARROW, locale));
        System.out.println(month.getDisplayName(TextStyle.SHORT, locale));
    }
}
