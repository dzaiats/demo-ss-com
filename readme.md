### Web application under the testing
https://www.ss.com

### Scenario
 - Validate that randomly chosen item can be added to favorites.
 - Validate that page of the single listening looks properly:
 ```sh
    - Equal width
    - Not overlapped
    - Aligned on the left and right side
 ```
 More details can be found in the class "**ListingPage.java**"

Scenario:
```sh
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
```

### Requirements
 - Java 1.8
 - Maven
 - OS: MacOS or Linux

### Execution

```sh
$ mvn clean test
```

### Results
 - Maven output in console
 - Automotion report of visual validation: target/automotion/*
 

#### Contact
denys.zaiats@gmail.com