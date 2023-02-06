package java8.groupingBy;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

/**
 * The grouping by method in the collectors class allows us to
 * operate on a stream of elements and group by a set criteria.
 * In the sample below we group by country.
 */
public class GroupingBy {

    public static void main(String[] args) {

        // create some dummy data
        Person person1 = new Person("dav", "ire", 30);
        Person person2 = new Person("tom", "eng", 31);
        Person person3 = new Person("sal", "ire", 30);
        Person person4 = new Person("ted", "sco", 40);
        Person person5 = new Person("ken", "ire", 30);
        Person person6 = new Person("pip", "ire", 40);
        Person person7 = new Person("red", "sco", 33);
        Person person8 = new Person("zeb", "ire", 30);

        // put the dummy data into a list
        final List<Person> people = List.of(person1, person2, person3, person4, person5, person6, person7, person8);

        // group the list of people by country
        final Map<String, List<Person>> collect = people.stream()
                .collect(groupingBy(Person::getCountry));
        System.out.println(collect);

    }
}
class Person {
    private String name;
    private String country;
    private int age;

    public Person(String name, String country, int age) {
        this.name = name;
        this.country = country;
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", age=" + age +
                '}';
    }
}

