package uk.co.beanfactory.sainsbury.display;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import uk.co.beanfactory.sainsbury.shopping.ShoppingDisplay;
import uk.co.beanfactory.sainsbury.shopping.ShoppingItem;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by comerb on 12/04/2016.
 */
public interface ShoppingParser {
    /**
     * class name of products
     */
    String PRODUCT = "product";
    String PRODUCT_INFO = "productInfo";
    String PRICE_PER_UNIT = "pricePerUnit";
    String DESCRIPTION_DIV = "information";
    String DESCRIPTION_PRODUCT_TEXT = "productText";

    String getDescriptionFrom(URL url);

    /**
     * Parses the Document of the wed page & produces a populated ShoppingItem
     *
     * @param doc Document of the web page
     * @return the calculated ShoppingItem
     */
    default ShoppingDisplay parse(Document doc) {
        ShoppingDisplay result = new ShoppingDisplay();

        Elements products = doc.getElementsByClass(PRODUCT);

        products.forEach(product -> {
            BigDecimal unitPrice = getPriceFromProductClass(product);
            ShoppingItem item = getTitleSizeDescFromProductClass(product, unitPrice);
            result.addItem(item);
        });

        return result;
    }

    /**
     * Gets the price from a 'product' element
     *
     * @param product the element from which to read the price
     * @return the calculated price
     */
    default BigDecimal getPriceFromProductClass(Element product) {
        Elements priceElement = product.getElementsByClass(PRICE_PER_UNIT);
        BigDecimal price = getPriceFromElementString(priceElement.text());
        return price;
    }

    /**
     * extracts the price from the string within the product element
     *
     * @param text full text from which to extract the price
     * @return the extracted price
     */
    default BigDecimal getPriceFromElementString(String text) {
        Pattern p = Pattern.compile("[.0-9]+");

        String strippedDownText = null;
        Matcher m = p.matcher(text);
        if (m.find()) {
            strippedDownText = m.group();
        }

        BigDecimal result = new BigDecimal(strippedDownText).setScale(2, BigDecimal.ROUND_UP);
        ;
        return result;
    }


    /**
     * Creates a ShoppingItem from the product element & the unitPrice that is passed in.
     *
     * @param product   an element from which to read the title, size & description
     * @param unitPrice
     * @return a fully populated ShoppingItem
     */
    default ShoppingItem getTitleSizeDescFromProductClass(Element product, BigDecimal unitPrice) {
        String title = "";
        String description = "";
        long size;
        ShoppingItem shoppingItem = null;

        Element productInfo = product.getElementsByClass(PRODUCT_INFO).first();

        if (productInfo != null) {
            if (productInfo.hasText()) {
                title = productInfo.text();
            }

            Element h3 = productInfo.select("h3").first();
            Element urlElement = h3.select("a").first();
            String prodUrl = urlElement.attr("href");

            try {
                final URL url = new URL(prodUrl);
                size = url.openConnection().getContentLength();
                description = getDescriptionFrom(url);

                shoppingItem = ShoppingItem.create(title, size, description, unitPrice);

            } catch (IOException e) {
                throw new RuntimeException("URL[" + prodUrl + "] is not valid, error" + e.getMessage() + "]", e);
            }
        }
        ;

        return shoppingItem;
    }

    default String getDescriptionFromDoc(Document doc) {
        Element infoElement = doc.select("div#" + DESCRIPTION_DIV).first();
        Element descElement = infoElement.getElementsByClass(DESCRIPTION_PRODUCT_TEXT).first();
        String result = descElement.text();
        return result;
    }


}
