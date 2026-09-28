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
import pages.LogoutFunctionality;


import java.util.Arrays;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.AssertJUnit.assertTrue;

public class LogoutTest extends BaseTest {
   LogoutFunctionality logoutFunctionality;

    @Test
    public void validateLogout() {
        logoutFunctionality=new LogoutFunctionality(page);
        page.navigate(TestConfig.BASE_URL + "/inventory.html");


        logoutFunctionality.clickMenuBtn();
        assertThat(page.getByText("Open Menu")).isEnabled();

        assertThat(page.getByText("Logout")).isEnabled();
        logoutFunctionality.clickLogoutBtn();

        assertThat(page).hasURL("https://www.saucedemo.com/");

    }
}
