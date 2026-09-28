package tests;

import base.BaseTest;
import com.microsoft.playwright.*;
import config.TestConfig;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AddToCartFunctionality;
import pages.CheckoutFunctionality;
import pages.LoginFunctionality;

import java.util.Arrays;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.AssertJUnit.assertTrue;

public class CheckoutTest extends BaseTest {

    AddToCartFunctionality addItemToCart;
    CheckoutFunctionality checkoutPage;

    @Test
    public void validateCheckout()
    {
        addItemToCart = new AddToCartFunctionality(page);
        checkoutPage = new CheckoutFunctionality(page);

        page.navigate(TestConfig.BASE_URL + "/inventory.html");
        addItemToCart.clickonCart();
        checkoutPage.clickCheckoutBtn();

        assertTrue(page.url().contains("/checkout-step-one.html"));
        Locator checkoutTitle = page.getByText("Checkout: Your Information");
        assertThat(checkoutTitle).hasText("Checkout: Your Information");

        checkoutPage.fillCheckoutDetails("Tejas","Dhamale", "144060");

        checkoutPage.clickContinueBtn();

        Locator checkoutSummaryContainer = page.locator(".checkout_summary_container");
        assertThat(checkoutSummaryContainer).isVisible();

        Locator finishBtn = page.locator("#finish");
        assertThat(finishBtn).isVisible();
        assertThat(finishBtn).isEnabled();
        checkoutPage.clickFinishBtn();

        Locator orderCompletionLocator = page.getByText("Thank you for your order!");
        assertThat(orderCompletionLocator).isVisible();
    }
}
