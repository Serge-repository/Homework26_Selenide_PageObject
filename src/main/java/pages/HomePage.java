package pages;

import com.codeborne.selenide.SelenideElement;
import fragments.SearchFragment;

import static com.codeborne.selenide.Selenide.$x;

public class HomePage extends GeneralPage {

    SearchFragment searchFragment = new SearchFragment();

    private final SelenideElement mainMenuSmartphonesTab = $x("(//a[@href='/smartfony/']//child::span)[3]");
    private final SelenideElement mainMenuNotebooksTab = $x("(//a[@href='/planshety-noutbuki-i-kompyutery/']//child::span)[3]");
    private final SelenideElement applePhonesLink = $x("//a[@href='/smartfony/brand-apple/']");
    private final SelenideElement acerNotebooksLink = $x("//a[@href='/noutbuki-i-ultrabuki/brand-acer/']");
    private final SelenideElement samsungPhonesLink = $x("//a[@href='/smartfony/brand-samsung/']");
    private final SelenideElement xiaomiPhonesLink = $x("//a[@href='/smartfony/brand-xiaomi/']");

    public SearchFragment getSearchFragment() {
        return searchFragment;
    }

    public void searchApplePhones() {
        hoverAndSearch(mainMenuSmartphonesTab, applePhonesLink);
    }

    public void searchSamsungPhones() {
        hoverAndSearch(mainMenuSmartphonesTab, samsungPhonesLink);
    }

    public void searchXiaomiPhones() {
        hoverAndSearch(mainMenuSmartphonesTab, xiaomiPhonesLink);
    }

    public void searchAcerNotebooks() {
        hoverAndSearch(mainMenuNotebooksTab, acerNotebooksLink);
    }
}
