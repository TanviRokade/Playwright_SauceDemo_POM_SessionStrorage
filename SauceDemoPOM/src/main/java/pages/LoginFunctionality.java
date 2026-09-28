package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;


public class LoginFunctionality {
    private Page page;
    private Locator username;
    private Locator password;
    private Locator loginBtn;
    private Locator error_Message;

    public LoginFunctionality(Page page)
    {
        this.page = page;
        this.username=page.locator("#user-name");
        this.password=page.locator("#password");
        this.loginBtn=page.locator("#login-button");
        this.error_Message=page.locator("data-test=['error']");
    }

    public void performLogin(String userNameInput, String passwordInput){
        username.fill(userNameInput);
        password.fill(passwordInput);
        loginBtn.click();
        page.waitForLoadState();
    }
/*
    public String getLoginErrorMessage()
    {
        return error_Message.textContent();
    }*/

}
