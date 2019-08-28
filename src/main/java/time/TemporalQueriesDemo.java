package time;

import org.junit.Test;
import time.utility.FamilyBirthdays;
import time.utility.FamilyVacations;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.YearMonth;
import java.time.temporal.TemporalQueries;
import java.time.temporal.TemporalQuery;
import java.time.temporal.TemporalUnit;

public class TemporalQueriesDemo {

    /**
     * 精度查询
     */
    @Test
    public void precision() {
        // 精度查询，不过返回的是英文
        TemporalQuery<TemporalUnit> query = TemporalQueries.precision();
        System.out.printf("LocalDate precision is %s%n",
                LocalDate.now().query(query));
        System.out.printf("LocalDateTime precision is %s%n",
                LocalDateTime.now().query(query));
        System.out.printf("Year precision is %s%n",
                Year.now().query(query));
        System.out.printf("YearMonth precision is %s%n",
                YearMonth.now().query(query));
        System.out.printf("Instant precision is %s%n",
                Instant.now().query(query));
    }

    /**
     * 查询一个日子是否是家人重要的日子
     */
    @Test
    public void fun29() {
        LocalDate date = LocalDate.now();
        // 不使用拉姆达表达式查询
        Boolean isFamilyVacation = date.query(new FamilyVacations());

        // 使用拉姆达表达式查询
        Boolean isFamilyBirthday = date.query(FamilyBirthdays::isFamilyBirthday);

        if (isFamilyVacation.booleanValue() || isFamilyBirthday.booleanValue())
            System.out.printf("%s 是一个重要的日子!%n", date);
        else
            System.out.printf("%s 不是一个重要的日子.%n", date);
    }

}
