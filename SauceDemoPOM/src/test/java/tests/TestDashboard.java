package tests;

import com.microsoft.playwright.*;
import dataProviders.LoginDataProvider;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.DashboardFunctionality;
import pages.LoginFunctionality;

import java.util.Arrays;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class TestDashboard {
    Playwright obj_playwright;
    Browser obj_browser;
    BrowserContext obj_context;
    Page obj_Page;
    LoginFunctionality loginFunctionality;
    DashboardFunctionality dashboardFunctionality;

    @BeforeMethod
    public void setUp(){
        obj_playwright = Playwright.create();
        obj_browser = obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)
                .setArgs(Arrays.asList("--start-maximized")));
        obj_context = obj_browser.newContext(new Browser.NewContextOptions().setViewportSize(null));
        obj_Page = obj_context.newPage();
        loginFunctionality=new LoginFunctionality(obj_Page);
        dashboardFunctionality = new DashboardFunctionality(obj_Page);
    }

    @Test()
    public void validateValidSauceDemo()
    {
        obj_Page.navigate("https://www.saucedemo.com/");
        loginFunctionality.performLogin("standard_user","secret_sauce");

        assertThat(obj_Page.locator(".inventory_container")).isVisible();
        dashboardFunctionality.selectOptionOnFilter("Name (A to Z)");

    }

    @AfterMethod
    public void teardown()
    {
        obj_Page.close();
    }
}
