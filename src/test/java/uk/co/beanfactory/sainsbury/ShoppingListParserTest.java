package uk.co.beanfactory.sainsbury;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by bill on 09/04/2016.
 */
public class ShoppingListParserTest {

    @Test
    public void getShoppingList() {
        Document doc = getTestFileDoc();

        ShoppingListParser sut = new ShoppingListParser();
        ShoppingDisplay result = sut.parse(doc);

        assertNotNull("no ShoppingDisplay created", result);
    }

    private Document getTestFileDoc() { String sainsburyHtmlFileName = "sainsbury.html";
        ClassLoader classLoader = getClass().getClassLoader();

        assertNotNull("classloader can not see file", classLoader.getResource(sainsburyHtmlFileName));

        File file = new File(classLoader.getResource(sainsburyHtmlFileName).getFile());
        assertTrue("can not see file", file.exists());

        Document doc = null;
        try {
            doc = Jsoup.parse(file, "UTF-8", "");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        assertNotNull("Failed to create doc from file", doc);
        return doc;
    }
}
