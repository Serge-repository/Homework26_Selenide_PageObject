package citrus_ui;

import com.codeborne.selenide.Condition;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductListPage;
import pages.TestBasis;

import static pages.enums.Phones.SAMSUNG_PHONES;
import static pages.enums.Phones.XIAOMI_PHONES;

public class FilterTests extends TestBasis {

    HomePage homePage = new HomePage();
    ProductListPage productListPage = new ProductListPage();

    @Test
    public void priceFilter() throws Exception {
        homePage.searchPhoneByModel(SAMSUNG_PHONES);
        productListPage.setMinPrice("5000")
                .setMaxPrice("10000");
        productListPage.getProductsNames().forEach(s -> s.hover().shouldHave(Condition.text("Samsung")));
        productListPage.comparePricesToLimits(5000, 10000);
    }

    @Test
    public void memorySizeFilter() {
        homePage.searchPhoneByModel(XIAOMI_PHONES);
        productListPage.selectRam("6");
        productListPage.getProductsNames().forEach(s -> s.shouldHave(Condition.text("Xiaomi")));
        productListPage.getProductsNames().forEach(s -> s.shouldHave(Condition.text("6/")));
        productListPage.unselectRam("6")
                .selectRam("8");
        productListPage.getProductsNames().forEach(s -> s.shouldHave(Condition.text("Xiaomi")));
        productListPage.getProductsNames().forEach(s -> s.shouldHave(Condition.text("8/")));
    }

    @Test
    public void bodyMaterialFilter() {
        homePage.searchPhoneByModel(XIAOMI_PHONES);
        productListPage.selectMaterial("metall");
        productListPage.getProductsNames().forEach(s -> s.shouldHave(Condition.text("Xiaomi")));
        productListPage.closeBanner();
        productListPage.checkProductsMaterial();
    }
}
