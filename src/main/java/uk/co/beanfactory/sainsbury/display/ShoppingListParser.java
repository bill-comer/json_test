package uk.co.beanfactory.sainsbury.display;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import uk.co.beanfactory.sainsbury.shopping.ShoppingDisplay;
import uk.co.beanfactory.sainsbury.shopping.ShoppingItem;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * retrives a ShoppingDisplay listing all the items visible
 * <p>
 * Created by bill on 09/04/2016.
 */
public class ShoppingListParser implements ShoppingParser {

    /**
     * Get the product description from the product URL
     *
     * @param url the url from which to get the description
     * @return the description
     */
    @Override
    public String getDescriptionFrom(URL url) {

        Document doc = null;
        try {
            doc = Jsoup.connect(url.toString()).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return getDescriptionFromDoc(doc);
    }


}
