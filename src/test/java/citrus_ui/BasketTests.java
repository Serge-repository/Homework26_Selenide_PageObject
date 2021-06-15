package citrus_ui;

import com.codeborne.selenide.Condition;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductListPage;
import pages.ProductPage;
import pages.TestBasis;

import static pages.enums.Phones.APPLE_PHONES;

public class BasketTests extends TestBasis {

    HomePage homePage = new HomePage();
    ProductListPage productListPage = new ProductListPage();
    ProductPage productPage = new ProductPage();

    @Test
    public void addProductToBasketUsingProductPage() {
        homePage.searchPhoneByModel(APPLE_PHONES);
        productListPage.clickOnExactProduct("Apple iPhone SE 2020 64Gb PRODUCT Red");
        String iPhoneSEPrice = productPage.getIPhoneSEPrice();
        productPage.clickOnBuyButton();
        productPage.getBasketFragment().getNumberOfGoodsInBasket().shouldHaveSize(1);
        productPage.getBasketFragment().getGoodsInBasketNames().get(0).shouldHave(Condition.text("Apple iPhone SE 2020 64Gb"));
        productPage.getBasketFragment().getGoodsInBasketPrices().get(0).shouldHave(Condition.text(iPhoneSEPrice));
    }

    @Test
    public void addToBasketUsingSearch() {
        homePage.getSearchFragment().performSearch("Apple iPhone 11");
        String iPhone11Price = productListPage.getPhonePrice("Apple iPhone 11 128Gb Black");
        productListPage.buyButton("Apple iPhone 11 128Gb Black");
        productListPage.getBasketFragment().getNumberOfGoodsInBasket().shouldHaveSize(1);
        productListPage.getBasketFragment().getGoodsInBasketNames().get(0).shouldHave(Condition.text("Apple iPhone 11 128Gb Black"));
        productListPage.getBasketFragment().getGoodsInBasketPrices().get(0).shouldHave(Condition.text(iPhone11Price));
    }

    @Test
    public void addTwoProductsToBasket() {
        homePage.getSearchFragment().performSearch("Apple iPhone");
        String firstProductName = productListPage.getSearchFragment().getProductsNamesList().get(0);
        String secondProductName = productListPage.getSearchFragment().getProductsNamesList().get(1);
        String firstProductPrice = productListPage.getSearchFragment().getProductsPricesList().get(0).replace(" ", "");
        String secondProductPrice = productListPage.getSearchFragment().getProductsPricesList().get(1).replace(" ", "");
        int priceSumAsInt = Integer.parseInt(firstProductPrice) + Integer.parseInt(secondProductPrice);
        String priceSum = String.valueOf(priceSumAsInt);
        productListPage.buyButton(firstProductName)
                .getBasketFragment().closeBasket();
        productListPage.buyButton(secondProductName);
        productListPage.getBasketFragment().getNumberOfGoodsInBasket().shouldHaveSize(2);
        productListPage.getBasketFragment().getGoodsInBasketNames().get(0).shouldHave(Condition.text(firstProductName));
        productListPage.getBasketFragment().getGoodsInBasketNames().get(1).shouldHave(Condition.text(secondProductName));
        productListPage.getBasketFragment().getGoodsInBasketPrices().get(0).shouldHave(Condition.text(firstProductPrice.substring(0, 1)));
        productListPage.getBasketFragment().getGoodsInBasketPrices().get(1).shouldHave(Condition.text(secondProductPrice.substring(0, 1)));
        productListPage.getBasketFragment().getBasketSumPrice().shouldHave(Condition.text(priceSum.substring(0, 1)));
    }

    @Test
    public void addTwoProductsToBasketFromComparison() {
        homePage.getSearchFragment().performSearch("Apple iPhone");
        String firstProductName = productListPage.getSearchFragment().getProductsNamesList().get(0);
        String secondProductName = productListPage.getSearchFragment().getProductsNamesList().get(1);
        String firstProductPrice = productListPage.getSearchFragment().getProductsPricesList().get(0).replace(" ", "");
        String secondProductPrice = productListPage.getSearchFragment().getProductsPricesList().get(1).replace(" ", "");
        int priceSumAsInt = Integer.parseInt(firstProductPrice) + Integer.parseInt(secondProductPrice);
        String priceSum = String.valueOf(priceSumAsInt);
        productListPage.compareButton(firstProductName)
                .compareButton(secondProductName)
                .getCompareFragment().clickOnCompareHeaderButton();
        productListPage.getCompareFragment().getBuyButtons().get(0).click();
        productListPage.getBasketFragment().closeBasket();
        productListPage.getCompareFragment().getBuyButtons().get(2).click();
        productListPage.getBasketFragment().getNumberOfGoodsInBasket().shouldHaveSize(2);
        productListPage.getBasketFragment().getGoodsInBasketNames().get(0).shouldHave(Condition.text(secondProductName));
        productListPage.getBasketFragment().getGoodsInBasketNames().get(1).shouldHave(Condition.text(firstProductName));
        productListPage.getBasketFragment().getGoodsInBasketPrices().get(0).shouldHave(Condition.text(firstProductPrice.substring(0, 2)));
        productListPage.getBasketFragment().getGoodsInBasketPrices().get(1).shouldHave(Condition.text(secondProductPrice.substring(0, 2)));
        productListPage.getBasketFragment().getBasketSumPrice().shouldHave(Condition.text(priceSum.substring(0, 1)));
    }
}
