package net.itarray.example.pages;

import net.itarray.example.utils.Bundles;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Random;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public SubCategoriesPage selectRandomCategory() {
        List<WebElement> categories = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("table #main_img_div .main_category a")));
        WebElement category = categories.get(new Random().nextInt(categories.size() - 1));
        Bundles.putString("latestChosenCategoryUrl", category.getAttribute("href"));
        category.click();

        List<WebElement> subCategories = driver.findElements(By.cssSelector("table .category a"));

        if (subCategories.size() > 0) {
            return new SubCategoriesPage(driver);
        } else {
            return new CategoryPage(driver);
        }
    }

    public void validateThatListingIsAdded(SoftAssertions softAssertions) {

    }
}
