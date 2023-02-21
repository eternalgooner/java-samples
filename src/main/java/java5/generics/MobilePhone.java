package java5.generics;

import java.math.BigDecimal;

public class MobilePhone extends Device {
    public MobilePhone(int age, BigDecimal value) {
        super(DeviceType.MOBILE, age, value);
    }
}
