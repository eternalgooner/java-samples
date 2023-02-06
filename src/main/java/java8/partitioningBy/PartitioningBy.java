package java8.partitioningBy;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.partitioningBy;

/**
 * We can use the partitioningBy(predicate) to partition a data structure into 2
 * partitions. 1 partition that satisfies the predicate & 1 partition that does not.
 */
public class PartitioningBy {

    public static void main(String[] args) {

        // create dummy data
        Person person1 = new Person("dav", "ire", 30);
        Person person2 = new Person("tom", "eng", 31);
        Person person3 = new Person("sal", "ire", 30);
        Person person4 = new Person("ted", "sco", 40);
        Person person5 = new Person("ken", "ire", 30);
        Person person6 = new Person("pip", "ire", 40);
        Person person7 = new Person("red", "sco", 33);
        Person person8 = new Person("zeb", "ire", 30);

        final List<Person> people = List.of(person1, person2, person3, person4, person5, person6, person7, person8);
        final Map<Boolean, List<Person>> peopleFromIreland = people.stream()
                .collect(partitioningBy(p -> p.country.equals("ire")));
        System.out.println(peopleFromIreland);

    }
}

class Person {
    String name;
    String country;
    int age;

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
