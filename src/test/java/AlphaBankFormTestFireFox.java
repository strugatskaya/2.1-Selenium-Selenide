import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.List;

public class AlphaBankFormTestFireFox {
    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        System.setProperty("webdriver.chrome.driver", "/driver/win/geckodriver");
        WebDriverManager.firefoxdriver().setup();
    }

    @BeforeEach
    void setUp() {

        FirefoxOptions options1 = new FirefoxOptions();
        options1.addArguments("--disable-dev-shm-usage");
        options1.addArguments("--no-sandbox");
        options1.addArguments("--headless");
        driver = new FirefoxDriver();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void shouldTestFindElementAndSendKeysOnFireFoxPositive1() {
        driver.get("http://localhost:9999");
        List<WebElement> textField = driver.findElements(By.className("input__control"));
        textField.get(0).sendKeys("Понд Эми");
        textField.get(0).sendKeys("+39999999999");
    }

    @Test
    void shouldTestFindElementAndSendKeysOnFireFoxPositive2() {
        driver.get("http://localhost:9999");
        List<WebElement> textField = driver.findElements(By.className("input__control"));
        textField.get(0).sendKeys("Клара Освин-Освальд");
        textField.get(0).sendKeys("+00000000000");
    }

    @Test
    void shouldTestFindElementAndSendKeysOnFireFoxNegative1() {
        driver.get("http://localhost:9999");
        List<WebElement> textField = driver.findElements(By.className("input__control"));
        textField.get(0).sendKeys("The Doctor");
        textField.get(0).sendKeys("+3999999999");
    }

    @Test
    void shouldTestFindElementAndSendKeysOnFireFoxNegative2() {
        driver.get("http://localhost:9999");
        List<WebElement> textField = driver.findElements(By.className("input__control"));
        textField.get(0).sendKeys("Понд Эми6");
        textField.get(0).sendKeys("39999999999");
    }

    @Test
    void shouldTestFindElementAndSendKeysOnFireFoxNegative3() {
        driver.get("http://localhost:9999");
        List<WebElement> textField = driver.findElements(By.className("input__control"));
        textField.get(0).sendKeys("");
        textField.get(0).sendKeys("");
    }

    @Test
    void shouldTestFindElementAndSendKeysOnFireFoxNegative4() {
        driver.get("http://localhost:9999");
        List<WebElement> textField = driver.findElements(By.className("input__control"));
        textField.get(0).sendKeys("48");
        textField.get(0).sendKeys("тардисфон");

    }

    @Test
    void shouldTestFindElementAndSendKeysOnFireFoxNegative5() {
        driver.get("http://localhost:9999");
        List<WebElement> textField = driver.findElements(By.className("input__control"));
        textField.get(0).sendKeys("Роуз");
        textField.get(0).sendKeys("+399999999999");

    }

    @Test
    void shouldTestFindElementAndSendKeysOnChromeFireFoxNegative6() {
        driver.get("http://localhost:9999");
        List<WebElement> textField = driver.findElements(By.className("input__control"));
        textField.get(0).sendKeys("   ");
        textField.get(0).sendKeys("   ");

    }

    @Test
    void shouldTestFindElementAndClickOnFireFox() {
        driver.get("http://localhost:9999");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.tagName("button")).click();
    }

}
