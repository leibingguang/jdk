package lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class TestPredicate {
    public static void main(String[] args) {
        Person lei = new Person("Lei");
        Person bing = new Person("BingGuang");
        List<Person> personList = Arrays.asList(lei, bing);
        Comparator<Person> byWeight =
                (Person a1, Person a2) -> a1.getFirstName().compareTo(a2.getFirstName());

        Collections.sort(personList, byWeight);
        checkAndExecute(personList, person -> person.getFirstName().startsWith("L"), (person) -> System.out.println(person));
//���´���Ϊʹ��stream��ȡ����̬����
        personList.stream().filter(person -> person.getFirstName().startsWith("L")).forEach(person -> System.out.println(person));
        //����::��ʾMethod reference ʹ�ñ����Ѿ�д�õ�Object/Class��method�����lambda expression
        personList.stream().filter(person -> person.getFirstName().startsWith("L")).forEach(System.out::println);
    }

    private static void checkAndExecute(List<Person> personList, Predicate<Person> predicate, Consumer<Person> consumer) {

        for (Person person : personList) {
            if (predicate.test(person)) {
                consumer.accept(person);
            }
        }
//��forѭ���򻯺�Ĵ���
        personList.forEach(person -> {
            if (predicate.test(person)) {
                consumer.accept(person);
            }
        });
    }
}
