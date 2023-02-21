package java5.generics;

/**
 * Generic shop class that takes any type and sells that item
 * @param <T> any class type
 */
public class GenericsShop<T> {

    private T item;

    public GenericsShop(T item) {
        this.item = item;
    }

    public void sellItem() {
        // generic code to sell items
        System.out.println("The shop is selling item: " + item);
        removeItemFromStock(item);
    }

    private <T> void removeItemFromStock(T item){
        System.out.println(item + " has been removed from stock");
    }
}
