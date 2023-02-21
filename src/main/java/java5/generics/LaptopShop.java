package java5.generics;


public class LaptopShop {

    private Laptop item;

    public LaptopShop(Laptop item) {
        this.item = item;
    }

    public void sellItem() {
        // generic code to sell items
        System.out.println("The shop is selling item: " + item);
        removeItemFromStock(item);
    }

    private void removeItemFromStock(Laptop item){
        System.out.println(item + " has been removed from stock");
    }
}
