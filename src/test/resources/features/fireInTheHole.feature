# Created by zad at 2019-02-01
Feature: Just some feature
  The goal of this feature to show that I have some clue about Cucumber and stuff :)

  Scenario: start browser
    Given open browser

  Scenario Outline: : : Add to favourites using page object approach
    When open the home page with language '<lang>'
    And select the random category
    Then select the random sub-category
    And select the random listing
    Then the listing page is displayed
    When select button Add to Favourite
    And confirm adding to favourite
    Then the listing is added to favourites
    Examples:
      | lang |
      | lv   |
      | en   |

  Scenario: Remove from favourites, from the Memo page
    When open the home page with language 'en'
    Then navigate to Memo page
    And select the random listing from the table
    And select Remove from favourites
    Then the listing is removed from the table

  Scenario: close browser
    Given close browser