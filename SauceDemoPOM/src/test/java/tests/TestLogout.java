package tests;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AddToCartFunctionality;
import pages.CheckoutFunctionality;
import pages.LoginFunctionality;
import pages.LogoutFunctionality;


import java.util.Arrays;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.AssertJUnit.assertTrue;

public class TestLogout {
    Playwright obj_playwright;
    Browser obj_browser;
    BrowserContext obj_context;
    Page obj_Page;
    LoginFunctionality loginFunctionality;
    AddToCartFunctionality addToCartFunctionality;
    CheckoutFunctionality checkoutFunctionality;
    LogoutFunctionality logoutFunctionality;

    @BeforeMethod
    public void setUp(){
        obj_playwright = Playwright.create();
        obj_browser = obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)
                .setArgs(Arrays.asList("--start-maximized")));
        obj_context = obj_browser.newContext(new Browser.NewContextOptions().setViewportSize(null));
        obj_Page = obj_context.newPage();
        loginFunctionality=new LoginFunctionality(obj_Page);
        addToCartFunctionality =new AddToCartFunctionality(obj_Page);
        checkoutFunctionality = new CheckoutFunctionality(obj_Page);
        logoutFunctionality=new LogoutFunctionality(obj_Page);
    }

    @Test
    public void validateLogout() {
        obj_Page.navigate("https://www.saucedemo.com/");
        loginFunctionality.performLogin("standard_user","secret_sauce");
        addToCartFunctionality.addItemToCart();
        addToCartFunctionality.clickonCart();
        obj_Page.waitForLoadState();
        checkoutFunctionality.clickCheckoutBtn();

        assertTrue(obj_Page.url().contains("/checkout-step-one.html"));

        Locator checkoutTitle = obj_Page.getByText("Checkout: Your Information");
        assertThat(checkoutTitle).hasText("Checkout: Your Information");

        checkoutFunctionality.fillCheckoutDetails("Tejas","Dhamale", "144060");

        checkoutFunctionality.clickContinueBtn();

        Locator checkoutSummaryContainer = obj_Page.locator(".checkout_summary_container");
        assertThat(checkoutSummaryContainer).isVisible();

        Locator finishBtn = obj_Page.locator("#finish");
        assertThat(finishBtn).isVisible();
        assertThat(finishBtn).isEnabled();
        checkoutFunctionality.clickFinishBtn();

        logoutFunctionality.clickMenuBtn();
        assertThat(obj_Page.getByText("Open Menu")).isEnabled();

        assertThat(obj_Page.getByText("Logout")).isEnabled();
        logoutFunctionality.clickLogoutBtn();

        assertThat(obj_Page).hasURL("https://www.saucedemo.com/");

    }
    @AfterMethod
    public void teardown()
    {
        obj_Page.close();
    }
}
