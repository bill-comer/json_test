package uk.co.beanfactory.sainsbury.display;

import java.io.IOException;

/**
 * Created by comerb on 12/04/2016.
 */
public interface ShoppingLister {
  void listItems(String url, boolean useFile) throws IOException;

  void setParser(ShoppingParser parser);
}
