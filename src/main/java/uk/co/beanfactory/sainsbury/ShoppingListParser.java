package uk.co.beanfactory.sainsbury;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by bill on 09/04/2016.
 */
public class ShoppingListParser {

    boolean useTestFile = false;

    /**
     * class name of products
     */
    public static final String PRODUCT = "product";

    public ShoppingDisplay parse(Document doc) {
        ShoppingDisplay result = new ShoppingDisplay();

        Elements products = doc.getElementsByClass(PRODUCT);

        products.forEach( product -> {
            Double unitPrice = getPriceFromProductClass(product);
            ShoppingItem item = getTitleSizeDescFromProductClass(product, unitPrice);
            result.getResults().add(item);
        } );

        return result;
    }


    ShoppingItem getTitleSizeDescFromProductClass(Element product, Double unitPrice) {
        String title = "";
        String description = "";
        long size  ;
        ShoppingItem shoppingItem = null;

        Element productInfo = product.getElementsByClass("productInfo").first();

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

                shoppingItem = ShoppingItem.create(title, new Double(size), description, unitPrice);


            } catch (IOException e) {
                e.printStackTrace();
            }


        };

        return shoppingItem;
    }

    String getDescriptionFrom(URL url) {

        Document doc = null;
        try {
            if (useTestFile) {
                doc = getTestFileProductInfoDoc();
            } else {
                doc = Jsoup.connect(url.toString()).get();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Element infoElement = doc.select("div#information").first();
        Element descElement = infoElement.getElementsByClass("productText").first();
        String result = descElement.text();
        return result;
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

    private Document getTestFileProductInfoDoc() {
        String sainsburyHtmlFileName = "sainsburyProductInfo.html";
        return getTestFileDoc(sainsburyHtmlFileName);
    }

    private Document getTestFileDoc(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();


        File file = new File(classLoader.getResource(fileName).getFile());

        Document doc = null;
        try {
            doc = Jsoup.parse(file, "UTF-8", "");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return doc;
    }


}
