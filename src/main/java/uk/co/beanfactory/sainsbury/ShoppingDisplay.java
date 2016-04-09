package uk.co.beanfactory.sainsbury;

import java.util.ArrayList;
import java.util.List;

/**
 * List of items listed on a display
 * Created by bill on 09/04/2016.
 */
public class ShoppingDisplay {
    List<ShoppingItem> shoppingItems;

    public ShoppingDisplay() {
        this.shoppingItems = new ArrayList<>();
    }

    public List<ShoppingItem> getShoppingItems() {
        return shoppingItems;
    }

    public void setShoppingItems(List<ShoppingItem> shoppingItems) {
        this.shoppingItems = shoppingItems;
    }



}
