package citrus_ui;

import com.codeborne.selenide.Condition;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductListPage;
import pages.TestBasis;

public class CompareProductsTests extends TestBasis {

    HomePage homePage = new HomePage();
    ProductListPage productListPage = new ProductListPage();

    @Test
    public void compareThreeProducts() {
        homePage.searchAcerNotebooks();
        productListPage.setMinPrice("5000")
                .setMaxPrice("20000");
        String firstProductName = productListPage.getSearchFragment().getProductsNamesFromSideMenu().get(0);
        String secondProductName = productListPage.getSearchFragment().getProductsNamesFromSideMenu().get(1);
        String firstProductPrice = productListPage.getSearchFragment().getProductsPricesFromSideMenu().get(0);
        String secondProductPrice = productListPage.getSearchFragment().getProductsPricesFromSideMenu().get(1);
        productListPage.closeBanner();
        productListPage.getSearchFragment().getCompareButtonsViaSideSearch().get(0).hover().hover().click();
        productListPage.closeBanner();
        productListPage.getSearchFragment().getCompareButtonsViaSideSearch().get(1).hover().hover().click();
        productListPage.closeBanner();
        productListPage.getCompareFragment().clickOnCompareHeaderButton();
        productListPage.getCompareFragment().goodsInCompareQuantity().shouldHave(Condition.text("2"));
        productListPage.getSearchFragment().getGoodsInCompareListNames().get(0).shouldHave(Condition.text(secondProductName.substring(0, 26)));
        productListPage.getSearchFragment().getGoodsInCompareListNames().get(2).shouldHave(Condition.text(firstProductName.substring(0, 26)));
        productListPage.getSearchFragment().getGoodsInCompareListPrices().get(0).shouldHave(Condition.text(secondProductPrice));
        productListPage.getSearchFragment().getGoodsInCompareListPrices().get(2).shouldHave(Condition.text(firstProductPrice));

        productListPage.getSearchFragment().openSideMenuAndSearchAcer();
        productListPage.setMinPrice("5000")
                .setMaxPrice("20000");
        String thirdProductName = productListPage.getSearchFragment().getProductsNamesFromSideMenu().get(2);
        String thirdProductPrice = productListPage.getSearchFragment().getProductsPricesFromSideMenu().get(2);
        productListPage.closeBanner();
        productListPage.getSearchFragment().getCompareButtonsViaSideSearch().get(2).hover().hover().click();
        productListPage.closeBanner();
        productListPage.getCompareFragment().clickOnCompareHeaderButton();
        productListPage.getCompareFragment().goodsInCompareQuantity().shouldHave(Condition.text("3"));
        productListPage.getSearchFragment().getGoodsInCompareListNames().get(2).shouldHave(Condition.text(thirdProductName.substring(0, 26)));
        productListPage.getSearchFragment().getGoodsInCompareListPrices().get(2).shouldHave(Condition.text(thirdProductPrice));

    }
}
