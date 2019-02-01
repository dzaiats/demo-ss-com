Feature: Just some feature
  The goal of this feature to show that I have some clue about Cucumber and stuff :)

  Scenario: start browser
    Given open browser

  Scenario Outline: : : Add to favourites using page object approach
    When open the home page with language '<lang>'
    And select the random listing
    When select button Add to Favourite
    And confirm adding to favourite
    Then the listing is added to favourites
    And the listing page is displayed correctly
    Examples:
      | lang |
      | lv   |
      | en   |

  Scenario: close browser
    Given close browser