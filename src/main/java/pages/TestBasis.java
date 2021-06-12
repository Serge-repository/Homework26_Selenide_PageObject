package pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import static com.codeborne.selenide.Selenide.open;

public class TestBasis {
    @BeforeClass
    public void configsSetup(){
        Configuration.startMaximized = true;
        Configuration.browser = "chrome";
        Configuration.timeout = 6000;
    }

    @BeforeMethod
    public void openWebsiteCleanStorage(){
        open("https://www.citrus.ua/");
        Selenide.clearBrowserLocalStorage();
        Selenide.clearBrowserCookies();
    }
}
