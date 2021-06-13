package citrus_ui;

import com.codeborne.selenide.Condition;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductListPage;
import pages.TestBasis;
import pages.TestLogger;

public class CompareProductsTests extends TestBasis {

    HomePage homePage = new HomePage();
    ProductListPage productListPage = new ProductListPage();
    TestLogger testLogger = new TestLogger();

    @Test
    public void compareThreeProducts() {
        testLogger.log("Select Acer notebooks");
        homePage.searchAcerNotebooks();
        productListPage.setMinPrice("5000")
                .setMaxPrice("20000");
        String firstProductName = productListPage.getSearchFragment().getProductsNamesFromSideMenu().get(0);
        String secondProductName = productListPage.getSearchFragment().getProductsNamesFromSideMenu().get(1);
        String firstProductPrice = productListPage.getSearchFragment().getProductsPricesFromSideMenu().get(0);
        String secondProductPrice = productListPage.getSearchFragment().getProductsPricesFromSideMenu().get(1);
        productListPage.closeBanner();
        testLogger.log("Add first and second laptop to comparison");
        productListPage.getSearchFragment().getCompareButtonsViaSideSearch().get(0).hover().hover().click();
        productListPage.getSearchFragment().getCompareButtonsViaSideSearch().get(1).hover().hover().click();
        testLogger.log("Open compare menu");
        productListPage.getCompareFragment().clickOnCompareHeaderButton();
        productListPage.closeBanner();
        testLogger.log("Verification of 2 products in comparison, prices, names");
        productListPage.getCompareFragment().goodsInCompareQuantity().shouldHave(Condition.text("2"));
        productListPage.getSearchFragment().getGoodsInCompareListNames().get(0).shouldHave(Condition.text(secondProductName.substring(0, 26)));
        productListPage.getSearchFragment().getGoodsInCompareListNames().get(2).shouldHave(Condition.text(firstProductName.substring(0, 26)));
        productListPage.getSearchFragment().getGoodsInCompareListPrices().get(0).shouldHave(Condition.text(secondProductPrice));
        productListPage.getSearchFragment().getGoodsInCompareListPrices().get(2).shouldHave(Condition.text(firstProductPrice));

        testLogger.log("Search Acer notebook via side menu");
        productListPage.getSearchFragment().openSideMenuAndSearchAcer();
        productListPage.closeBanner();
        productListPage.setMinPrice("5000")
                .setMaxPrice("20000");
        String thirdProductName = productListPage.getSearchFragment().getProductsNamesFromSideMenu().get(2);
        String thirdProductPrice = productListPage.getSearchFragment().getProductsPricesFromSideMenu().get(2);
        productListPage.closeBanner();
        testLogger.log("Add third laptop to comparison");
        productListPage.getSearchFragment().getCompareButtonsViaSideSearch().get(2).hover().hover().click();
        productListPage.getCompareFragment().clickOnCompareHeaderButton();
        productListPage.closeBanner();
        testLogger.log("Verification of 3 products in comparison, prices, names");
        productListPage.getCompareFragment().goodsInCompareQuantity().shouldHave(Condition.text("3"));
        productListPage.getSearchFragment().getGoodsInCompareListNames().get(4).shouldHave(Condition.text(thirdProductName.substring(0, 26)));
        productListPage.getSearchFragment().getGoodsInCompareListPrices().get(2).shouldHave(Condition.text(thirdProductPrice));

    }
}
