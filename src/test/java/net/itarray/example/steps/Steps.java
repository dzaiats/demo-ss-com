package net.itarray.example.steps;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.itarray.automotion.validation.ResponsiveUIValidator;
import net.itarray.example.pages.HomePage;
import net.itarray.example.utils.Bundles;
import org.assertj.core.api.SoftAssertions;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class Steps extends StepsInitializer {

    @Before
    public void beforeScenario() {
        softAssertions = new SoftAssertions();
    }

    @After
    public void afterScenario(Scenario s) {
        ResponsiveUIValidator uiValidator = (ResponsiveUIValidator) Bundles.getObjectOfType(ResponsiveUIValidator.class, "ResponsiveUIValidator");
        uiValidator.generateReport();

        if (s.isFailed()) {
            takeScreenshot(s);
        }

        assertTrue(softAssertions.errorsCollected().toString(), softAssertions.errorsCollected().isEmpty());
    }

    @Given("^open browser$")
    public void open_browser() throws IOException {
        startDriver();
        ResponsiveUIValidator responsiveUIValidator = new ResponsiveUIValidator(driver);
        responsiveUIValidator.withTolerance(5);

        Bundles.putObjectOfType(ResponsiveUIValidator.class, "ResponsiveUIValidator", responsiveUIValidator);
    }

    @Given("^close browser$")
    public void close_browser() {
        closeDriver();
    }

    @When("^open the home page with language '(.+)'$")
    public void open_the_home_page_with_language_lv(String lang) {
        driver.get("https://www.ss.com/" + lang);
        homePage = new HomePage(driver);
    }

    @When("^select the random listing$")
    public void select_the_random_category() {
        listingPage = homePage.selectRandomListing();
    }

    @Then("^the listing page is displayed correctly$")
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
        listingPage.validateThatListingIsAdded(softAssertions);
    }

}
