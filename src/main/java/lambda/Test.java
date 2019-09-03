package lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Test {
    int portNumber = 1337;

    public static void main(String[] args) {
        Person lei = new Person("Lei");
        Person bing = new Person("BingGuang");
        List<Person> personList = Arrays.asList(lei, bing);

        checkAndExecute(personList, person -> person.getFirstName().startsWith("L"), (person, i) -> System.out.println(person));

    }

    private static void checkAndExecute(List<Person> personList, NameChecker nameChecker, Executor executor) {
        for (Person person : personList) {
            if (nameChecker.check(person)) {
                executor.execute(person, 1);
            }
        }
    }

    @org.junit.Test
    public void testLamdba() {
        t(person -> {
            System.out.println(person);
            return true;
        });
    }

    public void t(NameChecker nameChecker) {
        nameChecker.check(new Person("name"));
    }


}
