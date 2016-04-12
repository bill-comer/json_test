package uk.co.beanfactory.sainsbury.display;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created by bill on 12/04/2016.
 */
public class ShoppingTestFileListParser implements ShoppingParser {
    /**
     * Get the product description from the product URL
     *
     * @param url the url from which to get the description
     * @return the description
     */
    @Override
    public String getDescriptionFrom(URL url) {

        Document doc = getTestFileProductInfoDoc();

        return getDescriptionFromDoc(doc);
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
