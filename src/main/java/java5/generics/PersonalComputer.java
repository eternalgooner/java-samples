package java5.generics;

import java.math.BigDecimal;

public class PersonalComputer extends Device {
    public PersonalComputer(int age, BigDecimal value) {
        super(DeviceType.PC, age, value);
    }
}
