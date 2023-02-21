package java5.generics;

import java.math.BigDecimal;

public class GenericsApp {

    public static void main(String[] args) {

        //non generic shop
        LaptopShop laptopShop = new LaptopShop(new Laptop(3, BigDecimal.valueOf(120)));

        // create generic Shop, which takes any type
        GenericsShop<House> houseShop = new GenericsShop<>(new House("bungalow", 20));
        GenericsShop<Bike> bikeShop = new GenericsShop<>(new Bike("bmx", 2));

        houseShop.sellItem();
        bikeShop.sellItem();

        //create generic shop with bounded type, must be of type Device
        GenericsBoundedShop<Laptop> laptopGenericsBoundedShop = new GenericsBoundedShop<>(new Laptop(3, BigDecimal.valueOf(300)));
        GenericsBoundedShop<MobilePhone> mobilePhoneGenericsBoundedShop = new GenericsBoundedShop<>(new MobilePhone(3, BigDecimal.valueOf(300)));

        laptopGenericsBoundedShop.sellItem();
        mobilePhoneGenericsBoundedShop.sellItem();

        // this is not allowed as Bike does not extend Device
        //GenericsBoundedShop<Bike> bikeGenericsBoundedShop = new GenericsBoundedShop<>(new Bike("mountain", 20));
    }

}
