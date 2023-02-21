package java5.generics;

/**
 * Generic shop class that has a bounded type, must be of type Device
 * @param <T>
 */
public class GenericsBoundedShop<T extends Device> {

    private T item;

    public GenericsBoundedShop(T item) {
        this.item = item;
    }

    public void sellItem() {
        // generic code to sell items
        System.out.println("The shop is selling item: " + item);
    }
}
