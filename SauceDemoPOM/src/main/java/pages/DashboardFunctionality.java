package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class DashboardFunctionality {
    private Page page;
    private Locator inventorycontainer;
    private Locator product_sort_container;

    public DashboardFunctionality(Page page)
    {
        this.page = page;
        this.inventorycontainer=page.locator("[data-test='inventory-container']");
        this.product_sort_container= page.locator("[data-test='product-sort-container']");
    }

    public void clickonFilter(){
        product_sort_container.click();
    }

    public void selectOptionOnFilter(String option)
    {
        clickonFilter();
        product_sort_container.selectOption(option);
    }
}
