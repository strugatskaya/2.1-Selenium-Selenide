import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

public class AlphaBankFormTestChrome {
    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        System.setProperty("webdriver.chrome.driver", "/driver/win/chromedriver");
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);

    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void shouldTestFindElementAndSendKeysOnChromePositive1() {
        driver.get("http://localhost:9999");
        List<WebElement> textField = driver.findElements(By.className("input__control"));
        textField.get(0).sendKeys("Понд Эми");
        textField.get(0).sendKeys("+39999999999");
    }

    @Test
    void shouldTestFindElementAndSendKeysOnChromePositive2() {
        driver.get("http://localhost:9999");
        List<WebElement> textField = driver.findElements(By.className("input__control"));
        textField.get(0).sendKeys("Клара Освин-Освальд");
        textField.get(0).sendKeys("+00000000000");
    }

    @Test
    void shouldTestFindElementAndSendKeysOnChromeNegative1() {
        driver.get("http://localhost:9999");
        List<WebElement> textField = driver.findElements(By.className("input__control"));
        textField.get(0).sendKeys("The Doctor");
        textField.get(0).sendKeys("+3999999999");
    }

    @Test
    void shouldTestFindElementAndSendKeysOnChromeNegative2() {
        driver.get("http://localhost:9999");
        List<WebElement> textField = driver.findElements(By.className("input__control"));
        textField.get(0).sendKeys("Понд Эми6");
        textField.get(0).sendKeys("39999999999");
    }

    @Test
    void shouldTestFindElementAndSendKeysOnChromeNegative3() {
        driver.get("http://localhost:9999");
        List<WebElement> textField = driver.findElements(By.className("input__control"));
        textField.get(0).sendKeys("");
        textField.get(0).sendKeys("");
    }

    @Test
    void shouldTestFindElementAndSendKeysOnChromeNegative4() {
        driver.get("http://localhost:9999");
        List<WebElement> textField = driver.findElements(By.className("input__control"));
        textField.get(0).sendKeys("48");
        textField.get(0).sendKeys("тардисфон");

    }

    @Test
    void shouldTestFindElementAndSendKeysOnChromeNegative5() {
        driver.get("http://localhost:9999");
        List<WebElement> textField = driver.findElements(By.className("input__control"));
        textField.get(0).sendKeys("Роуз");
        textField.get(0).sendKeys("+399999999999");

    }

    @Test
    void shouldTestFindElementAndSendKeysOnChromeNegative6() {
        driver.get("http://localhost:9999");
        List<WebElement> textField = driver.findElements(By.className("input__control"));
        textField.get(0).sendKeys("   ");
        textField.get(0).sendKeys("   ");

    }

    @Test
    void shouldTestFindElementAndClickOnChrome() {
        driver.get("http://localhost:9999");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.tagName("button")).click();
    }

}


