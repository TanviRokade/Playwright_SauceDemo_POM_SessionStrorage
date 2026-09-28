package tests;

import base.BaseTest;
import config.TestConfig;
import org.testng.annotations.Test;
import pages.AddToCartFunctionality;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.AssertJUnit.assertTrue;

public class AddToCartTests extends BaseTest {

    AddToCartFunctionality addItemToCart;

    @Test
    public void validateAddTocart()
    {
        addItemToCart = new AddToCartFunctionality(page);
        page.navigate(TestConfig.BASE_URL + "/inventory.html");

        addItemToCart.addItemToCart();
        String cartBadge=addItemToCart.getNoofIteminCart();
        assertTrue(cartBadge.equals("3"));

        addItemToCart.clickonCart();
        assertThat(page.locator("#cart_contents_container")).isVisible();
        page.waitForLoadState();

        assertThat(page.getByText("Checkout")).isEnabled();
        assertThat(page.getByText("Checkout")).isVisible();
    }

}
