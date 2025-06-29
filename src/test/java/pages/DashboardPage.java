package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import utils.CommonMethods;

public class DashboardPage extends CommonMethods {

    public Locator pimOption;
    public Locator employeeListOption;
    public Locator addEmployeeOption;
    public Locator welcomeMessage;

    public DashboardPage() {
        this.pimOption = page.locator("#menu_pim_viewPimModule");
        this.employeeListOption = page.locator("#menu_pim_viewEmployeeList");
        this.addEmployeeOption = page.locator("#menu_pim_addEmployee");
        this.welcomeMessage = page.locator("#welcome");
    }
}