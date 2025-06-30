package utils;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;

import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonMethods extends PageInitializer {

    public static Playwright playwright;
    public static Browser browser;
    public static BrowserContext context;
    public static Page page;

    public static void openBrowserAndLaunchApplication() throws IOException {
        playwright = Playwright.create();
        switch (ConfigReader.read("browser")) {
            case "Chrome":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false
                ));
                Browser.NewContextOptions contextOptions = new Browser.NewContextOptions();
                contextOptions.setIgnoreHTTPSErrors(true);

                BrowserContext context = browser.newContext(contextOptions);
                page = context.newPage();
                break;
            case "Firefox":
                browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
            case "Edge":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("msedge").setHeadless(false));
                break;
            default:
                throw new RuntimeException("Invalid Browser Name");
        }
        context = browser.newContext();
        page = context.newPage();
        page.navigate(ConfigReader.read("url"));
        initializePageObjects();
    }

    public static void closeBrowser() {
        if (page != null) {
            page.close();
        }
        if (context != null) {
            context.close();
        }
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }

    public static void selectFromDropDown(Locator dropDown, int index) {
        dropDown.selectOption(new SelectOption().setIndex(index));
    }

    public static void selectFromDropDown(Locator dropDown, String visibleText) {
        dropDown.selectOption(visibleText);
    }

    public static void selectFromDropDown(String value, Locator dropDown) {
        dropDown.selectOption(new SelectOption().setValue(value));
    }

    public static void sendText(String text, Locator element) {
        element.fill(text);
    }

    public static void click(Locator element) {
        element.click();
    }

    public static byte[] takeScreenshot(String fileName) {
        byte[] picBytes = page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get(Constants.SCREENSHOT_FILEPATH + fileName + " " + getTimeStamp("yyyy-MM-dd-HH-mm-ss") + ".png"))
                .setFullPage(true));
        return picBytes;
    }

    public static String getTimeStamp(String pattern) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

}