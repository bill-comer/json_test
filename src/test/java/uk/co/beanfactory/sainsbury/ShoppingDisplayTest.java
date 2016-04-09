package uk.co.beanfactory.sainsbury;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by bill on 09/04/2016.
 */
public class ShoppingDisplayTest {
    String title = "foo";
    String description = "foo_desc";
    Double price = 1.23;
    Double size = 11.2;

    @Before
    public void before() {
        title = "foo";
        description = "foo_desc";
        price = 1.23;
        size = 11.2;

    }


    @Test
    public void testJsonOneItem_backAgain() {
        ShoppingItem item1 = ShoppingItem.create(title, size, description, price);

        ShoppingDisplay shoppingDisplay = new ShoppingDisplay();
        shoppingDisplay.getResults().add(item1);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String gsonItem = gson.toJson(shoppingDisplay);

        assertTrue(gsonItem.contains("results"));

        ShoppingDisplay convertedDisplay = new Gson().fromJson(gsonItem, ShoppingDisplay.class);
        assertNotNull("could not convert back to ShoppingDisplay", convertedDisplay);
        assertTrue("there should be some results", convertedDisplay.getResults() != null);
        assertTrue("there should be one results", convertedDisplay.getResults().size() == 1);

        ShoppingItem convertedItem = convertedDisplay.getResults().get(0);
        assertTrue("convertedItem should be same as created item" , convertedItem.equals(item1));
    }

}
