package tests;

import com.microsoft.playwright.*;
import dataProviders.LoginDataProvider;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginFunctionality;

import java.util.Arrays;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.AssertJUnit.assertFalse;

public class TestLogin {
    Playwright obj_playwright;
    Browser obj_browser;
    BrowserContext obj_context;
    Page obj_Page;
    LoginFunctionality loginFunctionality;
    SoftAssert softAssert;

    @BeforeMethod
    public void setUp(){
        obj_playwright = Playwright.create();
        obj_browser = obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)
                .setArgs(Arrays.asList("--start-maximized")));
        obj_context = obj_browser.newContext(new Browser.NewContextOptions().setViewportSize(null));
        obj_Page = obj_context.newPage();
        softAssert = new SoftAssert();
        loginFunctionality=new LoginFunctionality(obj_Page);
    }

    @Test(priority = 1, dataProvider = "validLoginData", dataProviderClass = LoginDataProvider.class)
    public void validateValidSauceDemo(String username, String password)
    {
        obj_Page.navigate("https://www.saucedemo.com/");
        loginFunctionality.performLogin(username,password);
        assertThat(obj_Page).hasURL("https://www.saucedemo.com/inventory.html");
    }

    @Test(priority = 2, dataProvider = "invalidLoginData", dataProviderClass = LoginDataProvider.class)
    public void validateInvalidCredentials(String username, String password)
    {
        obj_Page.navigate("https://www.saucedemo.com/");
        loginFunctionality.performLogin(username,password);
        assertFalse(obj_Page.url().contains("https://www.saucedemo.com/inventory1.html"));
    }

    @Test(priority = 3)
    public void validateEmptyCredentials()
    {
        obj_Page.navigate("https://www.saucedemo.com/");
        loginFunctionality.performLogin("","");
        softAssert.assertFalse(obj_Page.url().contains("https://www.saucedemo.com/inventory.html"));
        assertThat(obj_Page.locator("h3[data-test='error']"))
                .hasText("Epic sadface: Username is required");
        softAssert.assertAll();
    }

    @AfterMethod
    public void teardown()
    {
        obj_Page.close();
    }
}
