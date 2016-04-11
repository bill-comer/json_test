package uk.co.beanfactory.sainsbury;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by bill on 09/04/2016.
 */
public class ShoppingDisplay {
    List<ShoppingItem> results;
    BigDecimal total;


    public ShoppingDisplay() {
        this.results = new ArrayList<>();
        total = BigDecimal.ZERO;
    }

    /*
    public List<ShoppingItem> getResults() {
        return results;
    }
    */

    public void addItem(ShoppingItem item) {
        results.add(item);
        total = total.add(item.getUnitPrice());
    }


    public BigDecimal getTotal() {
        return total;
    }
}
