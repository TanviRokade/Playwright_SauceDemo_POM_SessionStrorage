package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LogoutFunctionality {
    private Page page;
    private Locator menuBtn;
    private Locator logoutBtn;


    public LogoutFunctionality(Page page){
        this.page=page;
        this.menuBtn= page.locator("#react-burger-menu-btn");
        this.logoutBtn= page.locator("#logout_sidebar_link");
    }

    public void clickMenuBtn(){
        menuBtn.click();
    }

    public  void clickLogoutBtn(){
        logoutBtn.click();
    }
}
