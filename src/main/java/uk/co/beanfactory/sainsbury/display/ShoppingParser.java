package uk.co.beanfactory.sainsbury.display;

import org.jsoup.nodes.Document;
import uk.co.beanfactory.sainsbury.shopping.ShoppingDisplay;

/**
 * Created by comerb on 12/04/2016.
 */
public interface ShoppingParser {
  ShoppingDisplay parse(Document doc);

  void setUseTestFile(boolean useFile);
}
