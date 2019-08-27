package stream;

import org.junit.Test;
import stream.entity.Dish;
import stream.entity.Dish.Type;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 查找和匹配
 */
public class FindAndMatchDemo {
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
     * 检查谓词是否至少匹配一个元素
     */
    @Test
    public void anyMatch() {
        boolean match = menu.stream().anyMatch(d -> d.getType() == Type.VEGETABLES);
        System.out.println(match);
    }

    /**
     * 检查谓词是否匹配所有元素
     */
    @Test
    public void allMatch() {
        boolean match = menu.stream().allMatch(d -> d.getType() == Type.FISH);
        System.out.println(match);
    }

    /**
     * 流中没有任何元素与给定的谓词匹配
     */
    @Test
    public void noneMatch() {
        boolean match = menu.stream().noneMatch(d -> d.getType() == Type.VEGETABLES);
        System.out.println(match);
    }

    /**
     * 返回当前流中的任意元素
     */
    @Test
    public void findAny() {
        Optional<Dish> dishOptional = menu.stream().filter(d -> d.getType() == Type.FISH).findAny();
        System.out.println(dishOptional.get());
    }

    /**
     * 查找第一个元素
     */
    @Test
    public void findFirst() {
        Optional<Dish> dishOptional = menu.stream().filter(d -> d.getType() == Type.FISH).findFirst();
        System.out.println(dishOptional.get());
    }

}
