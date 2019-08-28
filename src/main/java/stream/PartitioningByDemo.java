package stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * 分区
 */
public class PartitioningByDemo {
    public static boolean isPrime(List<Integer> primes, int candidate) {
        return primes.stream().noneMatch(i -> candidate % i == 0);
    }

    public Map<Boolean, List<Integer>> partitionPrimesWithCustomCollector
            (int n) {
        return IntStream.rangeClosed(2, n).boxed()
                .collect(
                        () -> new HashMap<Boolean, List<Integer>>() {{
                            put(true, new ArrayList<Integer>());
                            put(false, new ArrayList<Integer>());
                        }},
                        (acc, candidate) -> {
                            acc.get(isPrime(acc.get(true), candidate)).add(candidate);
                        },
                        (map1, map2) -> {
                            map1.get(true).addAll(map2.get(true));
                            map1.get(false).addAll(map2.get(false));
                        }
                );
    }

    /**
     * 实现自定义收集器
     */
    @Test
    public void MyPartition() {
        Map<Boolean, List<Integer>> partitionPrimesWithCustomCollector = partitionPrimesWithCustomCollector(100);
        System.out.println(partitionPrimesWithCustomCollector);
    }
}
