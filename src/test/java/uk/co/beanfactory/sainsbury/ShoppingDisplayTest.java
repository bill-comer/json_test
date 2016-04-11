package uk.co.beanfactory.sainsbury;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by bill on 09/04/2016.
 */
public class ShoppingDisplayTest {
    String title = "foo";
    String description = "foo_desc";
    BigDecimal price = BigDecimal.valueOf(1.23);
    long size = 11;

    @Before
    public void before() {
        title = "foo";
        description = "foo_desc";
        price = BigDecimal.valueOf(1.23);
        size = 11;

    }


    @Test
    public void testJsonOneItem_backAgain() {
        ShoppingItem item1 = ShoppingItem.create(title, size, description, price);

        ShoppingDisplay shoppingDisplay = new ShoppingDisplay();
        shoppingDisplay.addItem(item1);
        //shoppingDisplay.setTotal(99D);

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
               // .registerTypeAdapter(ShoppingDisplay.class, new ShoppingDisplayDeserializer())
                .create();
        String gsonItem = gson.toJson(shoppingDisplay);

        assertTrue(gsonItem.contains("results"));

        ShoppingDisplay convertedDisplay = new Gson().fromJson(gsonItem, ShoppingDisplay.class);
        assertNotNull("could not convert back to ShoppingDisplay", convertedDisplay);
        assertTrue("there should be some results", convertedDisplay.results != null);
        assertTrue("there should be one results", convertedDisplay.results.size() == 1);

        ShoppingItem convertedItem = convertedDisplay.results.get(0);
        assertTrue("convertedItem should be same as created item" , convertedItem.equals(item1));

        System.out.println(gsonItem);
    }

}
