package uk.co.beanfactory.sainsbury;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by bill on 09/04/2016.
 */
public class ShoppingItemTest {

    String title = "foo";
    String description = "foo_desc";
    Double price = 1.23;
    long size = 11;

    @Before
    public void before() {
        title = "foo";
        description = "foo_desc";
        price = 1.23;
        size = 11;

    }

    /**
     * tests the {@link ShoppingItem#create(String, long, String, Double)} process
     */
    @Test
    public void testCreate() {

        ShoppingItem item = ShoppingItem.create(title, size, description, price);

        assertNotNull("no item generated", item);
        assertEquals("title should be 'foo'",title, item.getTitle());
        assertEquals("size should be " + size,size, item.getSize());
        assertEquals("unitPrice should be " + price,price, item.getUnit_price());
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
        ShoppingItem item2 = ShoppingItem.create(title , size, description, new Double(999));

        assertNotEquals("they have difft prices", item1.equals(item2));
    }


    @Test
    public void convertToJson_andBackAgain() {
        ShoppingItem item = ShoppingItem.create(title, size, description, price);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String gsonItem = gson.toJson(item);

        System.out.println( gsonItem );

        ShoppingItem convertedItem = new Gson().fromJson(gsonItem, ShoppingItem.class);

        assertTrue("they should be the same", item.equals(convertedItem));

    }

}
