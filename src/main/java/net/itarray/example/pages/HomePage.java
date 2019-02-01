package net.itarray.example.pages;

import net.itarray.example.utils.Bundles;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElementsLocatedBy;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public ListingPage selectRandomListing() {

        while (!driver.getCurrentUrl().contains("/msg/")) {
            try {
                List<WebElement> categories = wait.until(visibilityOfAllElementsLocatedBy(cssSelector("table #main_img_div .main_category a,table .category a,table a[href*='msg']")));
                WebElement category = categories.get(new Random().nextInt(categories.size() - 1));
                Bundles.putString("latestChosenCategoryUrl", category.getAttribute("href"));
                category.click();
            } catch (TimeoutException ex) {
                driver.navigate().back();
            }
        }

        return new ListingPage(driver);
    }
}
