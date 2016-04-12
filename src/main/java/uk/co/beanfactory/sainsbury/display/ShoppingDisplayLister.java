package uk.co.beanfactory.sainsbury.display;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;
import uk.co.beanfactory.sainsbury.shopping.ShoppingDisplay;

import java.io.File;
import java.io.IOException;

/**
 * Created by bill on 09/04/2016.
 */
public class ShoppingDisplayLister implements ShoppingLister {


    ShoppingParser parser;

    @Override
    public void setParser(ShoppingParser parser) {
        this.parser = parser;
    }

    @Override
    public ShoppingParser getParser() {
        return parser;
    }


    /**
     * Gets a Document from the URL
     *
     * @param url
     * @return
     */
    @Override
    public Document getDocument(String url) throws IOException {
        return Jsoup.connect(url).get();
    }
}
