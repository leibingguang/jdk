package stream;

import org.junit.Test;
import stream.entity.Trader;
import stream.entity.Transaction;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

/**
 * 付诸实践
 */
public class PracticeDemo {
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

    /**
     * 找出2011年的所有交易并按交易额排序（从低到高）
     */
    @Test
    public void trader2011() {
        List<Transaction> trader2011 = transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(comparing(Transaction::getValue))
                .collect(toList());
        System.out.println(trader2011);
    }

    /**
     * 交易员都在哪些不同的城市工作过
     */
    @Test
    public void distinctCities() {
        List<String> cities = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(toList());
        System.out.println(cities);
    }

    /**
     * 查找所有来自于剑桥的交易员，并按姓名排序
     */
    @Test
    public void cambridgeTrader() {
        List<Trader> cabridgeTraderList = transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .distinct()
                .sorted(comparing(Trader::getName))
                .collect(toList());
        System.out.println(cabridgeTraderList);
    }

    /**
     * 返回所有交易员的姓名字符串，按字母顺序排序
     */
    @Test
    public void traderName() {
        String traderNames = transactions.stream()
                .map(Transaction::getTrader)
                .distinct()
                .map(Trader::getName)
                .reduce("", (a, b) -> a + " " + b);
        System.out.println(traderNames);
    }

    /**
     * 有没有交易员是在米兰工作的
     */
    @Test
    public void milanBased() {
        Optional<Trader> optionalTrader = transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Milan"))
                .findAny();
//        boolean milanBased = transactions.stream().anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
        System.out.println(optionalTrader.isPresent());
    }

    /**
     * 生活在剑桥的交易员的所有交易额
     */
    @Test
    public void transactionAmount() {

        transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .forEach(System.out::println);
        //交易总额
        int transactionAmount = transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(transaction -> transaction.getValue())
                .reduce(0, (a, b) -> a + b);
        System.out.println(transactionAmount);
    }

    /**
     * 所有交易中，最高的交易额是多少
     */
    @Test
    public void highestTransactionAmount() {
        Optional<Integer> highestTransactionAmount = transactions.stream()
                .map(transaction -> transaction.getValue())
                .reduce(Integer::max);

        System.out.println(highestTransactionAmount.get());
    }

    /**
     * 找到交易额最小的交易
     */
    @Test
    public void smallestTransaction() {
        Optional<Transaction> smallestTransaction = transactions.stream()
                .reduce((t1, t2) -> t1.getValue() > t2.getValue() ? t2 : t1);
//        Optional<Transaction> smallestTransaction = transactions.stream().min(comparing(Transaction::getValue));
        System.out.println(smallestTransaction.get());
    }
}
