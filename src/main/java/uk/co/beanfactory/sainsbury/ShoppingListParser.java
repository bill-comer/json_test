package uk.co.beanfactory.sainsbury;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by bill on 09/04/2016.
 */
public class ShoppingListParser {

    /**
     * class name of products
     */
    public static final String PRODUCT = "product";

    public ShoppingDisplay parse(Document doc) {
        ShoppingDisplay result = new ShoppingDisplay();

        Elements products = doc.getElementsByClass(PRODUCT);

        products.forEach( product -> {
            Double unitPrice = getPriceFromProductClass(product);
            String title = getTitleFromProductClass(product);

        } );

        return result;
    }

    String getTitleFromProductClass(Element product) {
        final String[] title = {null};
        Elements productInfo = product.getElementsByClass("productInfo");
        productInfo.forEach( item -> {
            if (item.hasText()) {
                title[0] = item.text();
            }
        });

        return title[0];
    }

    Double getPriceFromProductClass(Element product) {
        Elements priceElement = product.getElementsByClass("pricePerUnit");
        Double price = getPriceFromElementString(priceElement.text());
        return price;
    }

    Double getPriceFromElementString(String text) {
        Pattern p = Pattern.compile("[.0-9]+");

        String text2 = null;
        Matcher m = p.matcher(text);
        if (m.find()) {
            text2 = m.group();
        }

        Double result = new Double(text2);
        return  result;
    }
}
