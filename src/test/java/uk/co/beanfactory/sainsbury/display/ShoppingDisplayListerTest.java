package uk.co.beanfactory.sainsbury.display;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;
import uk.co.beanfactory.sainsbury.display.ShoppingDisplayLister;

import java.io.IOException;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotNull;

/**
 * Created by bill on 09/04/2016.
 */
public class ShoppingDisplayListerTest {

    ShoppingLister sut = new ShoppingTestFileLister();

    @Test
    public void testGetFile() throws IOException {
        Document doc = sut.getDocument(null);

        assertNotNull("failed to find file", doc);
        Elements productLister = doc.getElementsByClass("productLister");
        assertNotNull("could not see productLister class", productLister);
        assertTrue(productLister.size() > 0);
    }

    @Test
    public void testListFiles() throws IOException {
        sut.setParser(new ShoppingTestFileListParser());

        String results = sut.listItems(null);

        assertTrue("no results section", results.contains("\"results\": ["));
        assertTrue("no total", results.contains("\"total\": 15.10"));
        assertTrue(results.contains("\"title\": \"Sainsbury's Apricot"));

        int count = StringUtils.countMatches(results, "\"title\": \"Sainsbury's ");
        assertEquals("expected 7 items", 7, count);


    }
}
