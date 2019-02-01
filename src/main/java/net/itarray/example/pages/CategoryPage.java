package net.itarray.example.pages;

import org.openqa.selenium.WebDriver;

public class CategoryPage extends SubCategoriesPage {
    public CategoryPage(WebDriver driver) {
        super(driver);
    }

    public ListingPage selectRandomListing() {
        return new ListingPage(driver);
    }
}
