package uk.co.beanfactory.sainsbury.shopping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * List of all the items visible from a URL
 * Created by bill on 09/04/2016.
 */
public class ShoppingDisplay {
    List<ShoppingItem> results;
    BigDecimal total;


    public ShoppingDisplay() {
        this.results = new ArrayList<>();
        total = BigDecimal.ZERO;
    }

    /**
     * Adds an item to the list & increases the total appropiately.
     *
     * @param item
     */
    public void addItem(ShoppingItem item) {
        results.add(item);
        total = total.add(item.getUnitPrice());
    }

    public List<ShoppingItem> getResults() {
        return results;
    }

    public BigDecimal getTotal() {
        return total;
    }
}
