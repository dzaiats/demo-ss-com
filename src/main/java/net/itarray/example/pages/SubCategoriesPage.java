package net.itarray.example.pages;

import org.openqa.selenium.WebDriver;

public class SubCategoriesPage extends BasePage {
    public SubCategoriesPage(WebDriver driver) {
        super(driver);
    }

    public CategoryPage selectRandomCategory() {
        return new CategoryPage(driver);
    }
}
