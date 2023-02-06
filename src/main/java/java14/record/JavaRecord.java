package java14.record;

import java.util.Objects;

public class JavaRecord {

    /**
     * New to Java 14 is the 'record' keyword which allows us
     * to create immutable instances of data objects with a lot boiler plate code
     * generated under the hood.
     * They are ideal for simple data objects not intended to be modified.
     * @param args
     */
    public static void main(String[] args) {

        // create a record, passing the required properties/types
        Person person = new Person("bob", "ire", 50);
        System.out.println(person);

        // calling the record properties follows the dot notation without the 'get',
        // as would be the norm with a standard java class.
        // so to get the country property we just call .country() on the record
        String country = person.country();
    }
}

/**
 * define a record like so.
 * The properties are defined inline, like a constructors parameters.
 * All properties are effectively final and private.
 * This simple 1 line of code gives us:
 *  - All args public constructor
 *  - Getters for all properties (no setters, it's immutable)
 *  - Nice toString() method
 *  - equals() for all fields
 *  - hashCode() for all fields
 */

record Person(String name, String country, int age) {}

/**
 * That 1 line of code required for a record is the equivalent
 * to the below plain Java pre records (43 lines)
 */

class PersonOld {
    private final String name;
    private final String country;
    private final int age;

    public PersonOld(String name, String country, int age) {
        this.name = name;
        this.country = country;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonOld personOld)) return false;
        return getAge() == personOld.getAge() && getName().equals(personOld.getName()) && getCountry().equals(personOld.getCountry());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCountry(), getAge());
    }

    @Override
    public String toString() {
        return "PersonOld{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", age=" + age +
                '}';
    }
}