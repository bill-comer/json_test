package uk.co.beanfactory.sainsbury;

/**
 * Created by bill on 09/04/2016.
 */
public class SainsburyDemo {

    public static String DEFAULT_URL = "http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html";

    public static void main(String[] args) {

        String url = DEFAULT_URL;
        boolean useFile = true;

        ShoppingDisplayLister shoppingDisplayLister = new ShoppingDisplayLister();
        shoppingDisplayLister.listItems(url, useFile);

    }
}
