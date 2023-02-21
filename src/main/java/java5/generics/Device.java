package java5.generics;

import java.math.BigDecimal;

public abstract class Device {

    private DeviceType type;
    private int age;
    private BigDecimal value;

    public Device(DeviceType type, int age, BigDecimal value) {
        this.age = age;
        this.value = value;
        this.type = type;
    }

    public DeviceType getType() {
        return type;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Device{" +
                "type='" + type + '\'' +
                ", age=" + age +
                ", value=" + value +
                '}';
    }
}

enum DeviceType {
    MOBILE, PC, LAPTOP;
}
