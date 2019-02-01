package net.itarray.example.steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.itarray.example.driver.DriverProvider;
import net.itarray.example.pages.*;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Steps {
    private static SoftAssertions softAssertions;
    private static WebDriver driver;
    private static HomePage homePage;
    private static MemoPage memoPage;
    private static SubCategoriesPage subCategoriesPage;
    private static CategoryPage categoryPage;
    private static ListingPage listingPage;

    @Before
    public void beforeScenario() {
        softAssertions = new SoftAssertions();
    }

    @After
    public void afterScenario() {
        softAssertions.assertAll();
    }

    @Given("^open browser$")
    public void open_browser() throws IOException {
        driver = DriverProvider.getDriver();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Given("^close browser$")
    public void close_browser() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    @When("^open the home page with language '(.+)'$")
    public void open_the_home_page_with_language_lv(String lang) {
        driver.get("https://www.ss.com/" + lang);
        homePage = new HomePage(driver);
    }

    @When("^select the random category$")
    public void select_the_random_category() {
        subCategoriesPage = homePage.selectRandomCategory();
        if (subCategoriesPage instanceof CategoryPage) {
            categoryPage = (CategoryPage) subCategoriesPage;
        }
    }

    @Then("^select the random sub-category$")
    public void select_the_random_sub_category() {
        categoryPage = subCategoriesPage.selectRandomCategory();
    }

    @Then("^select the random listing$")
    public void select_the_random_listing() {
        listingPage = categoryPage.selectRandomListing();
    }

    @Then("^the listing page is displayed$")
    public void the_listing_page_is_displayed() {
        listingPage.validate(softAssertions);
    }

    @When("^select button Add to Favourite$")
    public void select_button_Add_to_Favourite() {
        listingPage.selectAddToFavorites();
    }

    @When("^confirm adding to favourite$")
    public void confirm_adding_to_favourite() {
        listingPage.confirmAddToFavorites();
    }

    @Then("^the listing is added to favourites$")
    public void the_listing_is_added_to_favourites() {
        homePage.validateThatListingIsAdded(softAssertions);
    }

    @Then("^navigate to Memo page$")
    public void navigate_to_Memo_page() {
        memoPage = homePage.openMemomPage();
    }

    @Then("^select the random listing from the table$")
    public void select_the_random_listing_from_the_table() {
        memoPage.selectRandomListingFromTable();
    }

    @Then("^select Remove from favourites$")
    public void select_Remove_from_favourites() {
        memoPage.selectRemoveFromFavorites();
    }

    @Then("^the listing is removed from the table$")
    public void the_listing_is_removed_from_the_table() {
        memoPage.validateThatListingIsRemoved(softAssertions);
    }

}
