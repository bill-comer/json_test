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
 *
 * Created by bill on 09/04/2016.
 */
public class ShoppingListParser implements ShoppingParser{

  boolean useTestFile = false;
  @Override
  public void setUseTestFile(boolean useTestFile) {
    this.useTestFile = useTestFile;
  }


    /**
     * class name of products
     */
    public static final String PRODUCT = "product";
    public static final String PRODUCT_INFO = "productInfo";
    public static final String PRICE_PER_UNIT = "pricePerUnit";
    public static final String DESCRIPTION_DIV = "information";
    public static final String DESCRIPTION_PRODUCT_TEXT = "productText";

  /**
   * Parses the Document of the wed page & produces a populated ShoppingItem
   * @param doc Document of the web page
   * @return the calculated ShoppingItem
   */
    @Override
    public ShoppingDisplay parse(Document doc) {
        ShoppingDisplay result = new ShoppingDisplay();

        Elements products = doc.getElementsByClass(PRODUCT);

        products.forEach( product -> {
            BigDecimal unitPrice = getPriceFromProductClass(product);
            ShoppingItem item = getTitleSizeDescFromProductClass(product, unitPrice);
            result.addItem(item);
        } );

        return result;
    }


  /**
   * Creates a ShoppingItem from the product element & the unitPrice that is passed in.
   *
   * @param product an element from which to read the title, size & description
   * @param unitPrice
   * @return a fully populated ShoppingItem
   */
    ShoppingItem getTitleSizeDescFromProductClass(Element product, BigDecimal unitPrice) {
        String title = "";
        String description = "";
        long size  ;
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
        };

        return shoppingItem;
    }

  /**
   * Get the product description from the product URL
   * @param url the url from which to get the description
   * @return the description
   */
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

        Element infoElement = doc.select("div#" + DESCRIPTION_DIV).first();
        Element descElement = infoElement.getElementsByClass(DESCRIPTION_PRODUCT_TEXT).first();
        String result = descElement.text();
        return result;
    }

  /**
   * Gets the price from a 'product' element
   *
   * @param product the element from which to read the price
   * @return the calculated price
   */
    BigDecimal getPriceFromProductClass(Element product) {
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
    BigDecimal getPriceFromElementString(String text) {
        Pattern p = Pattern.compile("[.0-9]+");

        String strippedDownText = null;
        Matcher m = p.matcher(text);
        if (m.find()) {
            strippedDownText = m.group();
        }

        BigDecimal result = new BigDecimal(strippedDownText).setScale(2, BigDecimal.ROUND_UP);;
        return result;
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
