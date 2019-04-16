import java.util.Calendar;
import java.util.Date;

public class TestLock {
    public static void main(String[] args) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        int dayForWeek = 0;
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            dayForWeek = 7;
        } else {
            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        }
        System.out.println(dayForWeek);
    }

}
