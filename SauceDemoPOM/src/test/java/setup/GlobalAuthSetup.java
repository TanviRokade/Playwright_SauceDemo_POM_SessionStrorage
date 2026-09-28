package setup;

import com.microsoft.playwright.*;
import config.TestConfig;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

//actual login and save the storage state.
public class GlobalAuthSetup {
    public static void loginAndSaveState(String username, String password, String outputPath)
    {
        try(Playwright obj_playwright = Playwright.create())
        {
            Browser obj_browser = obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext obj_context = obj_browser.newContext();
            Page obj_Page = obj_context.newPage();

            obj_Page.navigate(TestConfig.LOGIN_URL);
            obj_Page.fill("#user-name",username);
            obj_Page.fill("#password",password);
            obj_Page.click("#login-button");
            obj_Page.waitForURL("**/inventory.html");

            Path path = Paths.get(outputPath);
            Files.createDirectories(path.getParent());

            obj_context.storageState(new BrowserContext.StorageStateOptions().setPath(path));

            obj_browser.close();

        }
        catch (RuntimeException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
