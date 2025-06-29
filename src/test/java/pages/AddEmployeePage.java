package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import utils.CommonMethods;

public class AddEmployeePage extends CommonMethods {

    public Locator firstNameLoc;
    public Locator middleNameLoc;
    public Locator lastNameLoc;
    public Locator saveButton;
    public Locator photograph;
    public Locator checkBox;
    public Locator username;
    public Locator passwordUser;
    public Locator confirmPasswordUser;
    public Locator employeeId;

    public AddEmployeePage() {
        this.firstNameLoc = page.locator("#firstName");
        this.middleNameLoc = page.locator("#middleName");
        this.lastNameLoc = page.locator("#lastName");
        this.saveButton = page.locator("#btnSave");
        this.photograph = page.locator("#photofile");
        this.checkBox = page.locator("#chkLogin");
        this.username = page.locator("#user_name");
        this.passwordUser = page.locator("#user_password");
        this.confirmPasswordUser = page.locator("#re_password");
        this.employeeId = page.locator("#employeeId");
    }
}