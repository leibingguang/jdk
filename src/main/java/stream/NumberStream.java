package stream;

import org.junit.Test;
import stream.entity.Dish;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 数值流
 */
public class NumberStream {
    List<Dish> menu = Arrays.asList(
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
     * 计算热量总和
     */
    @Test
    public void sumCalories() {
        int calories = menu.stream().mapToInt(Dish::getCalories).sum();
        System.out.println(calories);
    }

    /**
     * 将数值流装箱成非特化流(对象)
     */
    @Test
    public void boxed() {
        menu.stream().mapToInt(Dish::getCalories).boxed();
    }

    /**
     * 给定默认值
     */
    @Test
    public void optionalInt() {
        OptionalInt maxCalories = menu.stream().mapToInt(Dish::getCalories).max();
        //如果没有最大值的话，返回默认值 -1
//        maxCalories.orElse(-1);
        int max = maxCalories.orElseGet(() -> -1);
    }

    /**
     * 数值范围
     */
    @Test
    public void range() {
        //输出1-99(不包含100)中能被3整除的数
        IntStream.range(1, 99).filter(a -> a % 3 == 0).forEach(System.out::println);
        //包含99
        IntStream.rangeClosed(1, 99).filter(a -> a % 3 == 0).forEach(System.out::println);
    }

    /**
     * 获取直角边小于100且三边边长都是整数的勾股数
     */
    @Test
    public void pythagoreanTriples() {
        Stream<double[]> pythagoreanTriples = IntStream.rangeClosed(1, 100)
                .boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        .mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)})
                        .filter(t -> t[2] % 1 == 0)
                );
        pythagoreanTriples.limit(10000000)
                .forEach(t ->
                        System.out.println(t[0] + ", " + t[1] + ", " + t[2]));
    }
}
