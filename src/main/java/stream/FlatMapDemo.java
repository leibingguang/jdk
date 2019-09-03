package stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class FlatMapDemo {
    @Test
    public void test() {
        List<String> words = Arrays.asList("Hello", "World");
        words.stream()
                .map(word -> word.split(""))//得到两个元素序列，每个元素序列都是一个String数组
                .map(Arrays::stream)//让每个数组编程一个单独的流
                .distinct()
                .forEach(System.out::println);

        words.stream()
                .map(word -> word.split(""))//得到两个元素序列，每个元素序列都是一个String数组
                .distinct()//对获取到的两个元素序列进行差异化比较
                .forEach(System.out::println);

        words.stream()
                .map(w -> w.split(""))//得到两个元素序列，每个元素序列都是一个String数组 Stream<String[]>
                .flatMap(Arrays::stream)//扁平化处理，得到N个元素序列，每个元素序列为split后的单个元素
                .distinct()//对元素序列做差异化处理
                .forEach(System.out::println);

        // 给定两个数字列表，如何返回所有的数对呢？例如，给定列表[1, 2, 3]和列表[3, 4]，
        // 应该返回[(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]。为简单起见，你可以用有两个元素的数组来代表数对。
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<int[]> pairs = numbers1.stream()
                .flatMap(
                        i -> numbers2.stream().map(j -> new int[]{i, j})
                )
                .collect(toList());
    }
}
