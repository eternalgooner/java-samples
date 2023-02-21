package java5.generics;

public class House {

    private String type;
    private int age;

    public House(String type, int age) {
        this.type = type;
        this.age = age;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "House{" +
                "type='" + type + '\'' +
                ", age=" + age +
                '}';
    }
}
