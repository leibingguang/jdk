package lambda;

import org.junit.Test;
import stream.entity.Trader;
import stream.entity.Transaction;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

public class LambdaDemo {
    Trader raoul = new Trader("Raoul", "Cambridge");
    Trader mario = new Trader("Mario", "Milan");
    Trader alan = new Trader("Alan", "Cambridge");
    Trader brian = new Trader("Brian", "Cambridge");
    List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
    );

    @Test
    public void filterAndGroup() {
        Map<Integer, List<Transaction>> groupbyYearTransactionList =
                transactions.stream()
                        .filter(price -> price.getValue() >= 400)
                        .collect(groupingBy(transaction -> transaction.getYear()));
        System.out.println(groupbyYearTransactionList);
    }
}
