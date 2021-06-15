package pages;

import com.codeborne.selenide.SelenideElement;
import fragments.BasketFragment;

import static com.codeborne.selenide.Selenide.$x;

public class ProductPage extends GeneralPage {

    private final BasketFragment basketFragment = new BasketFragment();

    private final SelenideElement iPhoneSEPrice = $x("(//b[@class='buy-section__new-price'])[1]");
    private final SelenideElement buyButton = $x("(//div[@class='buy-section__main-buy']/button)[1]");

    public BasketFragment getBasketFragment() {
        return basketFragment;
    }

    private String getProductPrice(SelenideElement selenideElement){
        return selenideElement.getText().replace("₴", "").trim();
    }

    public String getIPhoneSEPrice(){
        return getProductPrice(iPhoneSEPrice);
    }

    public void clickOnBuyButton(){
        clickAction(buyButton);
    }
}
