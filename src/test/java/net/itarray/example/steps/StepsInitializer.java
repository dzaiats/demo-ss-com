package net.itarray.example.steps;

import cucumber.api.Scenario;
import net.itarray.example.driver.DriverProvider;
import net.itarray.example.pages.HomePage;
import net.itarray.example.pages.ListingPage;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

class StepsInitializer {

    static SoftAssertions softAssertions;
    static WebDriver driver;
    static HomePage homePage;
    static ListingPage listingPage;

    void takeScreenshot(Scenario s) {
        if (driver != null) {
            try {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                s.embed(screenshot, "image/png");
                String currentUrl = driver.getCurrentUrl();
                s.write(String.format("URL at failure: <a href=\"%s\" target=\"_blank\">%s</a>", currentUrl, currentUrl));
            } catch (Exception e) {
                s.write("Embed Failed " + e.getMessage());
            }
        }
    }

    void startDriver() throws IOException {
        driver = DriverProvider.getDriver();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
