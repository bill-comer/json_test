package uk.co.beanfactory.sainsbury;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by bill on 09/04/2016.
 */
public class ShoppingListParserTest {

    @Test
    public void getPriceFromString() {
        ShoppingListParser sut = new ShoppingListParser();
        Double result = sut.getPriceFromElementString("&pound1.50/unit");
        assertNotNull(result);
        assertEquals("expected £1.50", new Double(1.50), result);
    }


    @Test
    public void getShoppingList() {
        Document doc = getTestFileDoc();

        ShoppingListParser sut = new ShoppingListParser();
        ShoppingDisplay result = sut.parse(doc);

        assertNotNull("no ShoppingDisplay created", result);
        assertNotNull(result.getResults());
        assertTrue(" expected more than one  ShoppingItem", result.getResults().size() > 0);
    }

    @Test
    public void getTitle() {
        Document doc = createTestProductDocument();
        Element product = doc.getElementsByClass("product").first();
        ShoppingListParser sut = new ShoppingListParser();

        String result = sut.getTitleFromProductClass(product);
        assertNotNull(result);
        assertEquals("expected[" + "Sainsbury's Avocado Ripe & Ready XL Loose 300g" + "]", "Sainsbury's Avocado Ripe & Ready XL Loose 300g", result);

    }

    @Test
    public void getPrice() {

        Document doc = createTestProductDocument();
        Element product = doc.getElementsByClass("product").first();
        ShoppingListParser sut = new ShoppingListParser();

        Double result = sut.getPriceFromProductClass(product);

        assertNotNull(result);
        assertEquals("", new Double(1.5), result);
    }

    private Document createTestProductDocument() {
        return Jsoup.parse(" <div class=\"product \">\n" +
                "\t            <div class=\"productInner\">\n" +
                "\t                <div class=\"productInfoWrapper\">\n" +
                "\t                    <div class=\"productInfo\">\n" +
                "\t                        \n" +
                "\t                                <h3>\n" +
                "\t                                    <a href=\"http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/sainsburys-avocado-xl-pinkerton-loose-300g.html\" >\n" +
                "\t                                        Sainsbury's Avocado Ripe & Ready XL Loose 300g\n" +
                "\t                                        <img src=\"http://c2.sainsburys.co.uk/wcsstore7.11.1.161/ExtendedSitesCatalogAssetStore/images/catalog/productImages/51/0000000202251/0000000202251_M.jpeg\" alt=\"\" />\n" +
                "\t                                    </a>\n" +
                "\t                                </h3>\n" +
                "\t                            \n" +
                "\t\t\t\t\t\t\t\t<div class=\"ThumbnailRoundel\">\n" +
                "\t\t\t\t\t\t\t\t<!--ThumbnailRoundel -->\n" +
                "\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t<div class=\"promoBages\">\n" +
                "\t\t\t\t\t\t\t\t\t<!-- PROMOTION -->\n" +
                "\t\t\t\t\t\t\t\t</div>\n" +
                "\n" +
                "\t\n" +
                "\t                        <!-- Review --><!-- BEGIN CatalogEntryRatingsReviewsInfo.jspf --><!-- productAllowedRatingsAndReviews: false --><!-- END CatalogEntryRatingsReviewsInfo.jspf -->\n" +
                "\t                    </div>\n" +
                "\t                </div>\n" +
                "\t         \t\n" +
                "\t                <div class=\"addToTrolleytabBox\">\n" +
                "\t                <!-- addToTrolleytabBox LIST VIEW--><!-- Start UserSubscribedOrNot.jspf --><!-- Start UserSubscribedOrNot.jsp --><!-- \n" +
                "\t\t\tIf the user is not logged in, render this opening \n" +
                "\t\t\tDIV adding an addtional class to fix the border top which is removed \n" +
                "\t\t\tand replaced by the tabs\n" +
                "\t\t-->\n" +
                "\t\t<div class=\"addToTrolleytabContainer addItemBorderTop\">\n" +
                "\t<!-- End AddToSubscriptionList.jsp --><!-- End AddSubscriptionList.jspf --><!-- \n" +
                "\t                        ATTENTION!!!\n" +
                "\t                        <div class=\"addToTrolleytabContainer\">\n" +
                "\t                        This opening div is inside \"../../ReusableObjects/UserSubscribedOrNot.jsp\"\n" +
                "\t                        -->\n" +
                "\t                \t<div class=\"pricingAndTrolleyOptions\">\t\n" +
                "\t    \t                <div class=\"priceTab activeContainer priceTabContainer\" id=\"addItem_572163\">\t\n" +
                "\t    \t                    <div class=\"pricing\">\n" +
                "                                   \n" +
                "\t    \t                        \n" +
                "<p class=\"pricePerUnit\">\n" +
                "&pound1.50<abbr title=\"per\">/</abbr><abbr title=\"unit\"><span class=\"pricePerUnitUnit\">unit</span></abbr>\n" +
                "</p>\n" +
                " \n" +
                "    <p class=\"pricePerMeasure\">&pound1.50<abbr \n" +
                "            title=\"per\">/</abbr><abbr \n" +
                "            title=\"each\"><span class=\"pricePerMeasureMeasure\">ea</span></abbr>\n" +
                "    </p>\n" +
                " \n" +
                "\t                                \t\n" +
                "\t    \t                    </div>\t\n" +
                "\t    \t                    \t\n" +
                "\t    \t                                <div class=\"addToTrolleyForm \">\t\n" +
                "\t    \t                                    \n" +
                "<form class=\"addToTrolleyForm\" name=\"OrderItemAddForm_572163\" action=\"OrderItemAdd\" method=\"post\" id=\"OrderItemAddForm_572163\" class=\"addToTrolleyForm\">\n" +
                "    <input type=\"hidden\" name=\"storeId\" value=\"10151\"/>\n" +
                "    <input type=\"hidden\" name=\"langId\" value=\"44\"/>\n" +
                "    <input type=\"hidden\" name=\"catalogId\" value=\"10122\"/>\n" +
                "    <input type=\"hidden\" name=\"URL\" value=\"http://www.sainsburys.co.uk/webapp/wcs/stores/servlet/CategoryDisplay?msg=&amp;categoryId=185749&amp;langId=44&amp;storeId=10151&amp;krypto=dwlvaeB6%2FxULwIdnZBpXIWTi8eDrMLVBDvxz1SYU6pQ4HZ0p1fQ4WzDDbX58qo25joVKwiFFlmQW%0A0wrexmT0zSs9NxHPxri6CctBDvXHKi15cZntIRJRW%2F%2BHeNnUqybiZXu%2FL47P9A658zhrWd08mA5Y%0Azhm9vwQK7oLCWKF5VeQF9UiLmiVnffGVqRM76kUBxmRLDA%2BardwWtMA49XQA4Iqwf%2BSvFr8RJOHK%0Afp2%2Fk0F6LH6%2Fmq5%2FM97LMdaXyk0YneYUccDUWQUNnbztUkimdSo%2FydqEDvTdI5qgO6sKl0Q%3D\"/>\n" +
                "    <input type=\"hidden\" name=\"errorViewName\" value=\"CategoryDisplayView\"/>\n" +
                "    <input type=\"hidden\" name=\"SKU_ID\" value=\"7678882\"/>\n" +
                "    \n" +
                "        <label class=\"access\" for=\"quantity_572162\">Quantity</label>\n" +
                "        \n" +
                "\t        <input name=\"quantity\" id=\"quantity_572162\" type=\"text\" size=\"3\" value=\"1\" class=\"quantity\"   />\n" +
                "\t        \n" +
                "        \n" +
                "        <input type=\"hidden\" name=\"catEntryId\" value=\"572163\"/>\n" +
                "        <input type=\"hidden\" name=\"productId\" value=\"572162\"/>\n" +
                "    \n" +
                "    <input type=\"hidden\" name=\"page\" value=\"\"/>\n" +
                "    <input type=\"hidden\" name=\"contractId\" value=\"\"/>\n" +
                "    <input type=\"hidden\" name=\"calculateOrder\" value=\"1\"/>\n" +
                "    <input type=\"hidden\" name=\"calculationUsage\" value=\"-1,-2,-3\"/>\n" +
                "    <input type=\"hidden\" name=\"updateable\" value=\"1\"/>\n" +
                "    <input type=\"hidden\" name=\"merge\" value=\"***\"/>\n" +
                "     \n" +
                "   \t<input type=\"hidden\" name=\"callAjax\" value=\"false\"/>\n" +
                "    \n" +
                "         <input class=\"button process\" type=\"submit\" name=\"Add\" value=\"Add\" />\n" +
                "     \n" +
                "</form>\n" +
                "\n" +
                "\t    <div class=\"numberInTrolley hidden numberInTrolley_572163\" id=\"numberInTrolley_572163\">\n" +
                "\t        \n" +
                "\t    </div>\n" +
                "\t \n" +
                "\t    \t                                </div>\t\n" +
                "\t    \t                            \n" +
                "\t                        </div><!-- END priceTabContainer Add container --><!-- Subscribe container --><!-- Start AddToSubscriptionList.jspf --><!-- Start AddToSubscriptionList.jsp --><!-- End AddToSubscriptionList.jsp --><!-- End AddToSubscriptionList.jspf -->\t\n" +
                "\t                    </div>\t\n" +
                "\t                </div>\t\n" +
                "\t            </div>\n" +
                "            </div> \t\n" +
                "        \t</div> ");
    }

    private Document getTestFileDoc() { String sainsburyHtmlFileName = "sainsbury.html";
        ClassLoader classLoader = getClass().getClassLoader();

        assertNotNull("classloader can not see file", classLoader.getResource(sainsburyHtmlFileName));

        File file = new File(classLoader.getResource(sainsburyHtmlFileName).getFile());
        assertTrue("can not see file", file.exists());

        Document doc = null;
        try {
            doc = Jsoup.parse(file, "UTF-8", "");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        assertNotNull("Failed to create doc from file", doc);
        return doc;
    }
}
