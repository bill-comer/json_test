package uk.co.beanfactory.sainsbury;

import junit.framework.Assert;
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
  public void testUrl_invalid() {

    String url = "//www.google.com";
    assertFalse("Url[" + url + "] should be invalid",SainsburyDemo.isUrlValid(url));
  }

  @Test
  public void testUrl_valid() {

    String url = "http://www.google.com";
    assertTrue("Url[" + url + "] should be valid",SainsburyDemo.isUrlValid(url));
  }

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
