package uk.co.beanfactory.sainsbury.display;

/**
 * Created by comerb on 12/04/2016.
 */
public class ShoppingListerCreator {

    /**
     * Manual injection
     *
     * @return
     */
    public static ShoppingLister create() {

        ShoppingLister lister = new ShoppingDisplayLister();
        ShoppingParser parser = new ShoppingListParser();

        lister.setParser(parser);
        return lister;
    }
}
