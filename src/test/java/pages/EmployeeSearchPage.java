package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import utils.CommonMethods;

public class EmployeeSearchPage extends CommonMethods {

    public Locator empIdSearchField;
    public Locator empNameSearchField;
    public Locator searchButton;

    public EmployeeSearchPage() {
        this.empIdSearchField = page.locator("#empsearch_id");
        this.empNameSearchField = page.locator("#empsearch_employee_name_empName");
        this.searchButton = page.locator("#searchBtn");
    }
}