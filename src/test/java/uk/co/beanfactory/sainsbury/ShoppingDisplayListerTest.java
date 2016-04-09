package uk.co.beanfactory.sainsbury;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotNull;

/**
 * Created by bill on 09/04/2016.
 */
public class ShoppingDisplayListerTest {

    @Test
    public void testGetFile() {
        ShoppingDisplayLister sut = new ShoppingDisplayLister();
        Document doc = sut.getDocument(null, true);

        assertNotNull("failed to find file", doc);
        Elements productLister = doc.getElementsByClass("productLister");
        assertNotNull("could not see productLister class", productLister);
        assertTrue(productLister.size()>0);
    }
}
