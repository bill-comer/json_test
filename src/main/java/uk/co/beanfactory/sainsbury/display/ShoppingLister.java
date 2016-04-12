package uk.co.beanfactory.sainsbury.display;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.nodes.Document;
import uk.co.beanfactory.sainsbury.shopping.ShoppingDisplay;

import java.io.IOException;

/**
 * Created by comerb on 12/04/2016.
 */
public interface ShoppingLister {

    void setParser(ShoppingParser parser);

    ShoppingParser getParser();

    Document getDocument(String url) throws IOException;

    /**
     * retrieves the shopping items from a URL or file & displays the results to Standard out.
     *
     * @param url - the URL to get the shopping list from
     */
    default String listItems(String url) throws IOException {

        Document doc = getDocument(url);
        ShoppingDisplay shoppingDisplay = getParser().parse(doc);

        Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
        String gsonShoppingDisplay = gson.toJson(shoppingDisplay);

        System.out.println(gsonShoppingDisplay);
        return gsonShoppingDisplay;
    }
}
