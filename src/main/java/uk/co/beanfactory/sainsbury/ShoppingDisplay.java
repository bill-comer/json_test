package uk.co.beanfactory.sainsbury;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by bill on 09/04/2016.
 */
public class ShoppingDisplay {
    List<ShoppingItem> results;
    Double total;


    public ShoppingDisplay() {
        this.results = new ArrayList<>();
        total = 0D;
    }

    /*
    public List<ShoppingItem> getResults() {
        return results;
    }
    */

    public void addItem(ShoppingItem item) {
        results.add(item);
        total += item.getUnit_price();
    }


    public Double getTotal() {
        return total;
    }
}
