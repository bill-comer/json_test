package uk.co.beanfactory.sainsbury;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by bill on 09/04/2016.
 */
public class ShoppingDisplay {
    List<ShoppingItem> results;

    public ShoppingDisplay() {
        this.results = new ArrayList<>();
    }

    public List<ShoppingItem> getResults() {
        return results;
    }

    public void setResults(List<ShoppingItem> results) {
        this.results = results;
    }



}
