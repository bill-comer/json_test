package uk.co.beanfactory.sainsbury.shopping;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Before;
import org.junit.Test;
import uk.co.beanfactory.sainsbury.shopping.ShoppingItem;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Created by bill on 09/04/2016.
 */
public class ShoppingItemTest {

    String title = "foo's banana";
    String description = "foo_desc";
    BigDecimal price = new BigDecimal("1.23");
    long size = 11000;

    @Before
    public void before() {
        title = "foo's banana";
        description = "foo_desc";
        price = BigDecimal.valueOf(1.23);
        size = 11000;

    }

    /**
     * tests the {@link ShoppingItem#create(String, long, String, BigDecimal)} process
     */
    @Test
    public void testCreate() {

        ShoppingItem item = ShoppingItem.create(title, size, description, price);

        assertNotNull("no item generated", item);
        assertEquals("title should be 'foo'",title, item.getTitle());
        assertEquals("size should be " + size/1000,size/1000, item.getSize());
        assertEquals("unitPrice should be " + price,price, item.getUnitPrice());
    }

    @Test
    public void testEquals_same() {
        ShoppingItem item1 = ShoppingItem.create(title, size, description, price);
        ShoppingItem item2 = ShoppingItem.create(title, size, description, price);

        assertTrue("they are the same", item1.equals(item2));
    }

    @Test
    public void testEquals_difftTitle() {
        ShoppingItem item1 = ShoppingItem.create(title, size, description, price);
        ShoppingItem item2 = ShoppingItem.create(title + "bar", size, description, price);

        assertNotEquals("they have difft titles", item1.equals(item2));
    }

    @Test
    public void testEquals_difftSize() {
        ShoppingItem item1 = ShoppingItem.create(title, size, description, price);
        ShoppingItem item2 = ShoppingItem.create(title , 123, description, price);

        assertNotEquals("they have difft size", item1.equals(item2));
    }

    @Test
    public void testEquals_difftDescription() {
        ShoppingItem item1 = ShoppingItem.create(title, size, description, price);
        ShoppingItem item2 = ShoppingItem.create(title , size, description + "bar", price);

        assertNotEquals("they have difft descriptions", item1.equals(item2));
    }

    @Test
    public void testEquals_difftPrice() {
        ShoppingItem item1 = ShoppingItem.create(title, size, description, price);
        ShoppingItem item2 = ShoppingItem.create(title , size, description, new BigDecimal(999));

        assertNotEquals("they have difft prices", item1.equals(item2));
    }


    @Test
    public void convertToJson_andBackAgain() {
        ShoppingItem item = ShoppingItem.create(title, size, description, price);

        Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

        String gsonItem = gson.toJson(item);

        System.out.println( gsonItem );

        ShoppingItem convertedItem = new Gson().fromJson(gsonItem, ShoppingItem.class);

        assertTrue("they should be the same", item.equals(convertedItem));
        assertTrue(gsonItem.contains("\"size\": \"11Kb\""));
        assertTrue(gsonItem.contains("\"unit_price\": 1.23"));

    }

}
