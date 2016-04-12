package uk.co.beanfactory.sainsbury;

import uk.co.beanfactory.sainsbury.display.ShoppingDisplayLister;
import uk.co.beanfactory.sainsbury.display.ShoppingLister;
import uk.co.beanfactory.sainsbury.display.ShoppingListerCreator;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by bill on 09/04/2016.
 */
public class SainsburyDemo {

    public static String DEFAULT_URL = "http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html";
    public static String URL_OPTION = "-url=";

    public static void main(String[] args) {

        String url = DEFAULT_URL;

        if (args.length > 0) {
            url = getUrlFromArgs(args);
        }

        if (!isUrlValid(url)) {
            System.out.println("\nThe url[" + url + "] is invalid or you have no internet connection");
            System.out.println("You need the 'http://' at the start\n");
            return;
        }


        ShoppingLister shoppingDisplayLister = ShoppingListerCreator.create();
        try {
            shoppingDisplayLister.listItems(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static boolean isUrlValid(String url){

        url = url.replaceFirst("^https", "http"); // Otherwise an exception may be thrown on invalid SSL certificates.
        int timeout = 500;

        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setConnectTimeout(timeout);
            connection.setReadTimeout(timeout);
            connection.setRequestMethod("HEAD");
            int responseCode = connection.getResponseCode();
            return (200 <= responseCode && responseCode <= 399);
        } catch (IOException exception) {
            return false;
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
