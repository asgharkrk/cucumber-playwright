package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import utils.CommonMethods;
import utils.DBUtils;
import utils.ExcelReader;

import java.util.List;
import java.util.Map;

public class AddEmployeeSteps extends CommonMethods {

    private String expectedFN;
    private String expectedMN;
    private String expectedLN;
    private String employeeIDFE;

    @When("user clicks on Add Employee option")
    public void user_clicks_on_add_employee_option() {
        click(dashboardPage.addEmployeeOption);
    }

    @When("user enters firstname and middlename and lastname")
    public void user_enters_firstname_and_middlename_and_lastname() {
        sendText("gibril", addEmployeePage.firstNameLoc);
        sendText("MS", addEmployeePage.middleNameLoc);
        sendText("Hassan", addEmployeePage.lastNameLoc);
    }

    @When("user clicks on save button")
    public void user_clicks_on_save_button() {
        click(addEmployeePage.saveButton);
    }

    @When("user enters {string} , {string} and {string}")
    public void user_enters_and(String firstName, String middleName, String lastName) {
        sendText(firstName, addEmployeePage.firstNameLoc);
        sendText(middleName, addEmployeePage.middleNameLoc);
        sendText(lastName, addEmployeePage.lastNameLoc);

    }

    @Then("employee added successfully")
    public void employee_added_successfully() {
        System.out.println("Employee Added");
        System.out.println(expectedFN);

        String query = "select emp_firstname, emp_middle_name, emp_lastname from hs_hr_employees where employee_id='" + employeeIDFE + "'";
        List<Map<String, String>> data = DBUtils.fetch(query);
        Map<String, String> firstRowMap = data.get(0);
        String actualFN = firstRowMap.get("emp_firstname");
        String actualMN = firstRowMap.get("emp_middle_name");
        String actualLN = firstRowMap.get("emp_lastname");
        Assert.assertEquals(expectedFN, actualFN);
        Assert.assertEquals(expectedMN, actualMN);
        Assert.assertEquals(expectedLN, actualLN);
    }

    @When("user enters {string} and {string} and {string}.")
    public void user_enters_and_and(String fn, String mn, String ln) {
        sendText(fn, addEmployeePage.firstNameLoc);
        sendText(mn, addEmployeePage.middleNameLoc);
        sendText(ln, addEmployeePage.lastNameLoc);
        expectedFN = fn;
        expectedMN = mn;
        expectedLN = ln;
        employeeIDFE = addEmployeePage.employeeId.inputValue();
    }

    @When("user enters firstname and middlename and lastname from data table and verify it")
    public void user_enters_firstname_and_middlename_and_lastname_from_data_table_and_verify_it
            (io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
        List<Map<String, String>> newEmployees = dataTable.asMaps();

        for (Map<String, String> employee : newEmployees) {
            String firstNameValue = employee.get("firstname");
            String middleNameValue = employee.get("middlename");
            String lastNameValue = employee.get("lastname");

            sendText(firstNameValue, addEmployeePage.firstNameLoc);
            sendText(middleNameValue, addEmployeePage.middleNameLoc);
            sendText(lastNameValue, addEmployeePage.lastNameLoc);

            click(addEmployeePage.saveButton);
            Thread.sleep(2000);
            click(dashboardPage.addEmployeeOption);
            Thread.sleep(2000);
        }
    }

    @When("user adds multiple employees from excel and validate them")
    public void user_adds_multiple_employees_from_excel_and_validate_them() throws InterruptedException {
        List<Map<String, String>> employeeData = ExcelReader.read();

        for (Map<String, String> employee : employeeData) {
            sendText(employee.get("firstName"), addEmployeePage.firstNameLoc);
            sendText(employee.get("middleName"), addEmployeePage.middleNameLoc);
            sendText(employee.get("lastName"), addEmployeePage.lastNameLoc);
            sendText(employee.get("Photograph"), addEmployeePage.photograph);

            if (!addEmployeePage.checkBox.isVisible() || !addEmployeePage.checkBox.isChecked()) {
                click(addEmployeePage.checkBox);
            }

            sendText(employee.get("Username"), addEmployeePage.username);
            sendText(employee.get("Password"), addEmployeePage.passwordUser);
            sendText(employee.get("confirmPassword"), addEmployeePage.confirmPasswordUser);
            click(addEmployeePage.saveButton);

            Thread.sleep(2000);
            click(dashboardPage.addEmployeeOption);
            Thread.sleep(2000);
        }
    }
}