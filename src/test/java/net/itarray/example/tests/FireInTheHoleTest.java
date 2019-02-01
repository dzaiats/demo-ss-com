package net.itarray.example.tests;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(format = {"html:target/site/cucumber-pretty", "json:target/cucumberJson/cucumber.json"},
        glue = {"net/itarray/example/steps"},
        features = {"src/test/resources/features"})
public class FireInTheHoleTest {
}
