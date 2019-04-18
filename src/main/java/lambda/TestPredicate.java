package lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class TestPredicate {
    public static void main(String[] args) {
        Person lei = new Person("Lei");
        Person bing = new Person("BingGuang");
        List<Person> personList = Arrays.asList(lei, bing);


        checkAndExecute(personList, person -> person.getFirstName().startsWith("L"), (person) -> System.out.println(person));
//以下代码为使用stream流取代静态函数
        personList.stream().filter(person -> person.getFirstName().startsWith("L")).forEach(person -> System.out.println(person));
        //两个::表示Method reference 使用别人已经写好的Object/Class的method来替代lambda expression
        personList.stream().filter(person -> person.getFirstName().startsWith("L")).forEach(System.out::println);
    }

    private static void checkAndExecute(List<Person> personList, Predicate<Person> predicate, Consumer<Person> consumer) {

        for (Person person : personList) {
            if (predicate.test(person)) {
                consumer.accept(person);
            }
        }
//对for循环简化后的代码
        personList.forEach(person -> {
            if (predicate.test(person)) {
                consumer.accept(person);
            }
        });
    }
}
