package uk.co.beanfactory.sainsbury;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;
import java.io.File;
import java.io.IOException;

/**
 * Created by bill on 09/04/2016.
 */
public class ShoppingDisplayLister {

    /**
     * reteives the shopping items from a URL or file & displays the results to Standard out.
     * @param url - the URL to get the shopping list from
     * @param useFile - if true then a local test copy of the web page is used
     */
    public void listItems(String url, boolean useFile) throws IOException {

        Document doc = getDocument(url, useFile);
        ShoppingListParser parser = new ShoppingListParser();
        parser.useTestFile = useFile;
        ShoppingDisplay shoppingDisplay = parser.parse(doc);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String gsonShoppingDisplay = gson.toJson(shoppingDisplay);

        System.out.println(gsonShoppingDisplay);

    }

    /**
     *
     * @param url
     * @param useFile test facility - grabs local copy of a shopping web page
     * @return
     */
    public Document getDocument(String url, boolean useFile) throws IOException {
        Document doc = null;
        if (useFile) {
            doc = getTestFileDocument(doc);
        } else {
            doc = getShoppingDisplayDocument(url);
        }
        return doc;
    }

    private Document getShoppingDisplayDocument(String url) throws IOException {
        return Jsoup.connect(url).get();
    }

    private Document getTestFileDocument(Document doc) {
            String sainsburyHtmlFileName = "sainsbury.html";
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource(sainsburyHtmlFileName).getFile());
            if (file == null || !file.exists()) {
                throw new RuntimeException("no file[" + sainsburyHtmlFileName + "] found");
            }

            try {
                doc = Jsoup.parse(file, "UTF-8", "");
            } catch (IOException ioe) {
                throw new RuntimeException("Failed to parse file[" + sainsburyHtmlFileName + "]");
            }
        return doc;
    }
}
