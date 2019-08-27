package stream;

import org.junit.Test;
import stream.entity.Dish;
import stream.entity.Dish.CaloricLevel;
import stream.entity.Dish.Type;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

/**
 * 分组
 */
public class GroupingbyDemo {
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
     * 按类型分组
     */
    @Test
    public void dishesByType() {
        Map<Type, List<Dish>> groupingByType = menu.stream().collect(groupingBy(Dish::getType));
        System.out.println(groupingByType);
    }

    /**
     * 按热量分组，高热量，普通热量，低热量
     */
    @Test
    public void dishesByCaloricLevel() {
        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel
                = menu.stream()
                .collect(groupingBy(dish -> {
                    if (dish.getCalories() <= 400)
                        return CaloricLevel.DIET;
                    else if (dish.getCalories() <= 700)
                        return CaloricLevel.NORMAL;
                    else
                        return CaloricLevel.FAT;
                }));
        System.out.println(dishesByCaloricLevel);
    }

    @Test
    public void dishByTypeCaloricLevel() {
        Map<Type, Map<CaloricLevel, List<Dish>>> dishByTypeCaloricLevel
                = menu.stream()
                .collect(groupingBy(Dish::getType, groupingBy(dish -> {
                    if (dish.getCalories() <= 400)
                        return CaloricLevel.DIET;
                    else if (dish.getCalories() <= 700)
                        return CaloricLevel.NORMAL;
                    else
                        return CaloricLevel.FAT;
                })));
        System.out.println(dishByTypeCaloricLevel);
    }

}
