package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class AddToCartFunctionality {
    Page page;
    Locator tshirt;
    Locator backpack;
    Locator fleeceJacket;
    Locator cartCount;
    Locator cartBtn;
    Locator cartContainer;
    AddToCartFunctionality addToCartFunctionality;


    public AddToCartFunctionality(Page page){
        this.page=page;
        this.backpack=page.locator("#add-to-cart-sauce-labs-backpack");
        this.tshirt=page.locator("#add-to-cart-sauce-labs-bolt-t-shirt");
        this.fleeceJacket=page.locator("#add-to-cart-sauce-labs-fleece-jacket");
        this.cartCount=page.locator(".shopping_cart_badge");
        this.cartBtn=page.locator("#shopping_cart_container");
        this.cartContainer=page.locator("#cart_contents_container");
    }

    public void addItemToCart(){
        backpack.click();
        tshirt.click();
        fleeceJacket.click();
    }

    public String getNoofIteminCart(){
        return cartCount.innerText();
    }

    public void clickonCart(){
        cartBtn.click();
        page.waitForLoadState();
    }

}
