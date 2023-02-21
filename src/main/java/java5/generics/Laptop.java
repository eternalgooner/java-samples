package java5.generics;

import java.math.BigDecimal;

public class Laptop extends Device {
    public Laptop(int age, BigDecimal value) {
        super(DeviceType.LAPTOP, age, value);
    }
}
