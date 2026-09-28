package base;

import com.microsoft.playwright.*;
import config.TestConfig;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.nio.file.Paths;

public class BaseTest {
    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext browserContext;
    protected Page page;

    @BeforeMethod
    public void setup()
    {
        playwright=Playwright.create();
        browser=playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        browserContext=browser.newContext(new Browser.NewContextOptions().setStorageStatePath(Paths.get(TestConfig.USER_STATE)));
        page= browserContext.newPage();
    }

    @AfterMethod
    public void teardown()
    {
        if(browserContext!=null) browserContext.close();
        if(browser!=null) browser.close();
        if(playwright!=null) playwright.close();
    }
}
