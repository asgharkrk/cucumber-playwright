package Exp;

import java.time.Duration;

public class E1 {
    public static void main(String[] args) {
        // first

        WebElement dropdownTrigger = driver.findElement(By.id("CREDIT_APP_FORM#stateOfIncorporation"));

        // Click to open the dropdown
        dropdownTrigger.click();

        // Wait for the dropdown options to appear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dropdownList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-selectonemenu-items")));

        // Find and click the "Alaska" option
        WebElement alaskaOption = dropdownList.findElement(By.xpath(".//li[@data-label='Alaska']"));
        alaskaOption.click();




        // second
       WebElement dropdownInput = driver.findElement(By.id("CREDIT_APP_FORM#stateOfIncorporation"));

        // Click to open the dropdown
        dropdownInput.click();

        // Use Actions to type and select "Alaska"
        Actions actions = new Actions(driver);
        actions.moveToElement(dropdownInput)
                .click()
                .sendKeys("Alaska")
                .sendKeys(Keys.ENTER)
                .perform();


        // Third
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Locate the dropdown input element
        WebElement dropdownTrigger = driver.findElement(By.id("CREDIT_APP_FORM#stateOfIncorporation"));

        // Open the dropdown (if required by the UI)
        dropdownTrigger.click();

        // Wait for the dropdown options to be available
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-selectonemenu-items")));

        // Use JavaScript to select "Alaska" by setting the data-label or triggering a selection
        js.executeScript("document.querySelector('li[data-label=\"Alaska\"]').click();");
    }
}
