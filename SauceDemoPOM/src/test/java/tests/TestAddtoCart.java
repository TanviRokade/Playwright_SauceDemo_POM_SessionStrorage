package tests;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AddToCartFunctionality;
import pages.LoginFunctionality;

import java.util.Arrays;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class TestAddtoCart {
    Playwright obj_playwright;
    Browser obj_browser;
    BrowserContext obj_context;
    Page obj_Page;
    LoginFunctionality loginFunctionality;
    AddToCartFunctionality addToCartFunctionality;


    @BeforeMethod
    public void setUp(){
        obj_playwright = Playwright.create();
        obj_browser = obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)
                .setArgs(Arrays.asList("--start-maximized")));
        obj_context = obj_browser.newContext(new Browser.NewContextOptions().setViewportSize(null));
        obj_Page = obj_context.newPage();
        loginFunctionality=new LoginFunctionality(obj_Page);
        addToCartFunctionality =new AddToCartFunctionality(obj_Page);
    }

    @Test
    public void validateAddTocart()
    {
        obj_Page.navigate("https://www.saucedemo.com/");
        loginFunctionality.performLogin("standard_user","secret_sauce");

        addToCartFunctionality.addItemToCart();
        String cartBadge= addToCartFunctionality.getNoofIteminCart();
        assertTrue(cartBadge.equals("3"));

        addToCartFunctionality.clickonCart();
        assertThat(obj_Page.locator("#cart_contents_container")).isVisible();
        obj_Page.waitForLoadState();

        assertThat(obj_Page.getByText("Checkout")).isEnabled();
        assertThat(obj_Page.getByText("Checkout")).isVisible();
    }

    @AfterMethod
    public void teardown()
    {
        obj_Page.close();
    }
}
