package net.itarray.example.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;

public class DriverProvider {

    public static WebDriver getDriver() throws IOException {
        String platform = "linux";
        String osName = System.getProperty("os.name").toLowerCase();

        if (osName.contains("mac")) {
            platform = "mac";
        }
        String chromeDriver = "src/main/resources/drivers/" + platform + "/chromedriver";

        System.setProperty("webdriver.chrome.driver", chromeDriver);
        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(chromeDriver))
                .usingAnyFreePort().build();

        service.start();

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.setExperimentalOption("useAutomationExtension", false);
        chromeOptions.addArguments("--window-size=1920,1080");

        return new ChromeDriver(service, chromeOptions);
    }
}
