package stream;

import org.junit.Test;
import stream.entity.Dish;

import java.util.Arrays;
import java.util.List;

/**
 * 筛选和切片
 */
public class FilterDemo {
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
     * 循环
     */
    @Test
    public void forEach() {
        menu.stream().forEach(System.out::println);
    }

    /**
     * 跳过元素前N个元素
     */
    @Test
    public void skip() {
        menu.stream().filter((dish) -> dish.getCalories() > 400).forEach(System.out::println);
        menu.stream().filter((dish) -> dish.getCalories() > 400).skip(2).forEach(System.out::println);
    }

    /**
     * 截短
     * 该方法会返回一个不超过给定长度的流。所需的长度作为参数传递
     * 给limit。如果流是有序的，则最多会返回前n个元素
     */
    @Test
    public void limit() {
        menu.stream().filter((dish) -> dish.getCalories() > 400).limit(2).forEach(System.out::println);
    }

    /**
     * 该操作会接受一个谓词（一个返回boolean的函数）作为参数，并返回一个包括所有符合谓词的元素的流
     */
    @Test
    public void filter() {
        menu.stream().filter((dish) -> dish.getCalories() > 400).forEach(System.out::println);
    }

    /**
     * 流还支持一个叫作distinct的方法，它会返回一个元素各异（根据流所生成元素的
     * hashCode和equals方法实现）的流。
     */
    @Test
    public void distinct() {
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream().filter(i -> i % 2 == 0).distinct().forEach(System.out::println);
    }
}
