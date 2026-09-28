package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CheckoutFunctionality {
    private Page page;
    private Locator checkoutBtn;
    private Locator firstName;
    private Locator lastName;
    private Locator postalCode;
    private Locator continueBtn;
    private Locator finishBtn;

    public CheckoutFunctionality(Page page)
    {
        this.page = page;
        this.checkoutBtn=page.getByText("Checkout");
        this.firstName=page.getByPlaceholder("First Name");
        this.lastName=page.getByPlaceholder("Last Name");
        this.postalCode=page.getByPlaceholder("Zip/Postal Code");
        this.continueBtn=page.locator("#continue");
        this.finishBtn=page.locator("#finish");
    }

    public void clickCheckoutBtn()
    {
        checkoutBtn.click();
    }

    public void fillCheckoutDetails(String firstname, String lastname, String postalcode)
    {
        firstName.fill(firstname);
        lastName.fill(lastname);
        postalCode.fill(postalcode);
    }

    public void clickContinueBtn(){
        continueBtn.click();
    }

    public void clickFinishBtn(){
        finishBtn.click();
    }
}
