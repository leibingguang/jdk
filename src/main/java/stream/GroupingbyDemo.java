package stream;

import org.junit.Test;
import stream.entity.Dish;
import stream.entity.Dish.CaloricLevel;
import stream.entity.Dish.Type;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

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

    /**
     * 先按类型，再按热量分组
     */
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

    /**
     * 获取每个分类最高热量的食物
     */
    @Test
    public void mostCaloricByType() {
        Map<Type, Dish> mostCaloricByType = menu.stream().collect(
                groupingBy(Dish::getType,//分类函数
                        collectingAndThen(//第二级收集器
                                maxBy(comparing(Dish::getCalories)),//包装后的收集器
                                Optional::get))//转换函数
        );
        System.out.println(mostCaloricByType);
    }

    /**
     * 每种类型的总热量
     */
    @Test
    public void totalCaloricByType() {
        Map<Type, Integer> totalCaloricByType
                = menu.stream().collect(groupingBy(Dish::getType, summingInt(Dish::getCalories)));
    }

    /**
     * 每种类型，有哪些CaloricLevel
     */
    @Test
    public void caloricLevelsByType() {
        Map<Type, Set<CaloricLevel>> caloricLevelsByType = menu.stream().collect(groupingBy(Dish::getType, mapping(dish -> {
            if (dish.getCalories() <= 400)
                return CaloricLevel.DIET;
            else if (dish.getCalories() <= 700)
                return CaloricLevel.NORMAL;
            else
                return CaloricLevel.FAT;
        }, toSet())));
        System.out.println(caloricLevelsByType);
    }
}
