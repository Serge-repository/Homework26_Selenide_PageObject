package citrus_ui;

import com.codeborne.selenide.Condition;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductListPage;
import pages.TestBasis;

public class FilterTests extends TestBasis {

    HomePage homePage = new HomePage();
    ProductListPage productListPage = new ProductListPage();

    @Test
    public void priceFilter() throws Exception {
        homePage.searchSamsungPhones();
        productListPage.setMinPrice("5000")
                .setMaxPrice("10000");
        productListPage.getProductsNames().forEach(s -> s.hover().shouldHave(Condition.text("Samsung")));
        productListPage.comparePricesToLimits(5000, 10000);
    }

    @Test
    public void memorySizeFilter() {
        homePage.searchXiaomiPhones();
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
        homePage.searchXiaomiPhones();
        productListPage.selectMaterial("metall");
        productListPage.getProductsNames().forEach(s -> s.shouldHave(Condition.text("Xiaomi")));
        productListPage.closeBanner();
        productListPage.checkProductsMaterial();
    }
}
