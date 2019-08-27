package stream;

import org.junit.Test;
import stream.entity.Dish;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class MappingDemo {
    List<Dish> menu = asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH));

    /**
     * 接受一个函数作为参数。这个函数会被应用到每个元素上，并将其映射成一个新的元素
     */
    @Test
    public void map() {
        menu.stream().map(Dish::getName).forEach(System.out::println);
    }

    /**
     * flatmap方法让你把一个流中的每个值都换成另一个流，然后把所有的流连接起来成为一个流。
     */
    @Test
    public void flatMap() {
        List<String> words = asList("Goodbye", "World");
        //返回完整的字符集合GoodbyeWorld
        words.stream().map((s) -> s.split("")).distinct().flatMap(Arrays::stream).forEach(System.out::print);
        System.out.println();

        System.out.println(words.stream().map(s -> s.split("")).collect(toList()));
        //返回：GodbyeWrl
        words.stream()
                .map(s -> s.split(""))//lambda入参为String，返回String[], map返回Stream<String[]>
                //接收一个数组，并返回数组拆分后的每个元素的流
                .flatMap(Arrays::stream)//方法引用入参为String[],出参为String， flatMap返回String<String>
                .distinct()//返回Stream<String>
                .forEach(System.out::print);
    }

    @Test
    public void testMap() {

        //给定特定数字列表，返回各个元素的平方
        List<Integer> integers = asList(1, 2, 3, 4, 5);
        integers.stream().map(i -> i * i).forEach(System.out::println);

        //给定两个数字列表，如何返回所有的数对呢？例如，给定列表[1, 2, 3]和列表[3, 4]，应
        //该返回[(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]。为简单起见，你可以用有两个元素的数组来代表数对。
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        numbers1.stream()
                .flatMap(i -> numbers2.stream().map(j -> new int[]{i, j}))
                .collect(toList());

        //输出两数相加能被3整除的数对
        numbers1.stream()
                .flatMap(i -> numbers2.stream().filter(j -> (i + j) % 3 == 0).map(j -> new int[]{i, j}))
                .collect(toList());
    }


    @Test
    public void test() {
        List<String> words = asList("Goodbye", "World");

        words.stream().map(word->word.split("")).flatMap(Arrays::stream);
    }
}
