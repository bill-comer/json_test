package uk.co.beanfactory.sainsbury;

import uk.co.beanfactory.sainsbury.display.ShoppingDisplayLister;

import java.io.IOException;

/**
 * Created by bill on 09/04/2016.
 */
public class SainsburyDemo {

    public static String DEFAULT_URL = "http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html";
    public static String URL_OPTION = "-url=";
    public static String TEST_OPTION = "-test";

    public static void main(String[] args) {

        String url = DEFAULT_URL;
        boolean useTestFile = false;

        if (args.length > 0) {
            url = getUrlFromArgs(args);
        }

        ShoppingDisplayLister shoppingDisplayLister = new ShoppingDisplayLister();
        try {
            shoppingDisplayLister.listItems(url, useTestFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    static String getUrlFromArgs(String[] args) {

        String[] url = new String[0];
        for (String arg : args) {
            if (arg.startsWith(URL_OPTION)) {
                 url = arg.split(URL_OPTION);
            }
        }

        if (url.length>1) {
            return url[1];
        } else {
            System.out.println("\nNB: using default url\n");
            return DEFAULT_URL;
        }
    }
}
