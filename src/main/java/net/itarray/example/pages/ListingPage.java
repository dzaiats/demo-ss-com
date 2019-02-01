package net.itarray.example.pages;

import net.itarray.automotion.validation.ResponsiveUIValidator;
import net.itarray.automotion.validation.UISnapshot;
import net.itarray.example.utils.Bundles;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ListingPage extends BasePage {
    public ListingPage(WebDriver driver) {
        super(driver);
        int favVal = getFavVal();

        Bundles.putInt("lastSavedFavCounter", favVal);
    }

    private int getFavVal() {
        int favVal = 0;
        WebElement favCounter = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("mnu_fav_id")));
        if (favCounter.isDisplayed()) {
            String valFavCounter = favCounter.getText().replaceAll("\\D", "");
            favVal = Integer.parseInt(valFavCounter);
        }
        return favVal;
    }

    public void validate(SoftAssertions softAssertions) {
        List<WebElement> detailsElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.cssSelector("#msg_div_preload,#msg_div_msg,#tr_foto,#tr_err,#tr_cont")
        ));
        ResponsiveUIValidator uiValidator = (ResponsiveUIValidator) Bundles.getObjectOfType(ResponsiveUIValidator.class, "ResponsiveUIValidator");
        UISnapshot snapshot = uiValidator.snapshot("Are Top Aligned");
        boolean result = snapshot.findElements(detailsElements)
                .haveEqualWidth()
                .areLeftAligned()
                .doNotOverlap()
                .validate();

        softAssertions.assertThat(result).as("Elements are aligned correctly").isTrue();
    }

    public void selectAddToFavorites() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("a_fav"))).click();
    }

    public void confirmAddToFavorites() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("alert_ok"))).click();
    }

    public void validateThatListingIsAdded(SoftAssertions softAssertions) {
        int lastSavedFavCounter = Bundles.getInt("lastSavedFavCounter");
        int currentFavVal = getFavVal();
        softAssertions.assertThat(currentFavVal== lastSavedFavCounter + 1).as("Listing was added to favorites").isTrue();
        Bundles.putInt("lastSavedFavCounter", currentFavVal);
    }
}
