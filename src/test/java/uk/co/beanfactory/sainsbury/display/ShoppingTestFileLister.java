package uk.co.beanfactory.sainsbury.display;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.print.Doc;
import java.io.File;
import java.io.IOException;

/**
 * Created by bill on 12/04/2016.
 */
public class ShoppingTestFileLister implements ShoppingLister {


    ShoppingParser parser ;
    @Override
    public void setParser(ShoppingParser parser) {

        this.parser = parser;
    }

    @Override
    public ShoppingParser getParser() {
        return parser;
    }


    @Override
    public Document getDocument(String url) {
        Document doc;
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
