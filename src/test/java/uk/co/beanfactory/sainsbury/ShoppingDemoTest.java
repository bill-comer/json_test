package uk.co.beanfactory.sainsbury;

import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by comerb on 11/04/2016.
 */
public class ShoppingDemoTest {

  @Test
  public void getUrlFromArgs() {
    String[] args  = {"-url=Foo","-test"};

    String url = SainsburyDemo.getUrlFromArgs(args);
    assertNotNull(url);
    assertEquals("Foo", url);
  }

  @Test
  public void getUrlFromArgs_incorrectFormat() {
    String[] args  = {"-udrl=Foo","-test"};

    String url = SainsburyDemo.getUrlFromArgs(args);
    assertNotNull(url);
    assertEquals(SainsburyDemo.DEFAULT_URL, url);
  }

}
