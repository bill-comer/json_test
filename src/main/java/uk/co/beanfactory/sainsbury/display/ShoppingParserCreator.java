package uk.co.beanfactory.sainsbury.display;

/**
 * Created by comerb on 12/04/2016.
 */
public class ShoppingParserCreator {

  public static ShoppingParser create() {
    ShoppingParser parser = new ShoppingListParser();
    return parser;
  }
}
