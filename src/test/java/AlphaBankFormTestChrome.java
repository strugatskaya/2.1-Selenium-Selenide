import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
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
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
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
    void shouldTestAllFieldsIsCorrectPositive1() {
        driver.get("http://localhost:9999");
        List<WebElement> textField = driver.findElements(By.className("input__control"));
        textField.get(0).sendKeys("Понд Эми");
        textField.get(1).sendKeys("+39999999999");
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.tagName("button")).click();
        String text = driver.findElement(By.className("Success_successBlock__2L3Cw")).getText();
        Assertions.assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
    }

    @Test
    void shouldTestPositiveAllFieldsIsCorrectWithDashPositive() {
        driver.get("http://localhost:9999");
        List<WebElement> textField = driver.findElements(By.className("input__control"));
        textField.get(0).sendKeys("Клара Освин-Освальд");
        textField.get(1).sendKeys("+70000000000");
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.tagName("button")).click();
        String text = driver.findElement(By.className("Success_successBlock__2L3Cw")).getText();
        Assertions.assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
    }

    @Test
    void shouldTestWithLatinLettersNameNegative() {
        driver.get("http://localhost:9999");
        List<WebElement> textField = driver.findElements(By.className("input__control"));
        textField.get(0).sendKeys("The Doctor");
        textField.get(1).sendKeys("+00000000000");
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.tagName("button")).click();
        String text = driver.findElement(By.className("input__sub")).getText();
        Assertions.assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", text.trim());
    }

    @Test
    void shouldTestWithTenTelNumbersNegative() {
        driver.get("http://localhost:9999");
        List<WebElement> textField = driver.findElements(By.className("input__control"));
        textField.get(0).sendKeys("The Doctor");
        textField.get(1).sendKeys("+0000000000");
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.tagName("button")).click();
        String text = driver.findElement(By.className("input__sub")).getText();
        Assertions.assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", text.trim());
    }

    @Test
    void shouldTestWithNumbersInNameNegative() {
        driver.get("http://localhost:9999");
        List<WebElement> textField = driver.findElements(By.className("input__control"));
        textField.get(0).sendKeys("Понд Эми6");
        textField.get(1).sendKeys("+39999999999");
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.tagName("button")).click();
        String text = driver.findElement(By.className("input__sub")).getText();
        Assertions.assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", text.trim());
    }

    @Test
    void shouldTestWithNoPlusIntelNegative() {
        driver.get("http://localhost:9999");
        List<WebElement> textField = driver.findElements(By.className("input__control"));
        textField.get(0).sendKeys("Понд Эми");
        textField.get(1).sendKeys("39999999999");
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.tagName("button")).click();
        String text = driver.findElement(By.className("input__sub")).getText();
        Assertions.assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", text.trim());
    }

    @Test
    void shouldTestWithNoNameNegative() {
        driver.get("http://localhost:9999");
        List<WebElement> textField = driver.findElements(By.className("input__control"));
        textField.get(0).sendKeys("");
        textField.get(1).sendKeys("+39999999999");
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.tagName("button")).click();
        String text = driver.findElement(By.className("input__sub")).getText();//
        Assertions.assertEquals("Поле обязательно для заполнения", text.trim());
    }

    @Test
    void shouldTestWithNoTelNegative() {
        driver.get("http://localhost:9999");
        List<WebElement> textField = driver.findElements(By.className("input__control"));
        textField.get(0).sendKeys("Эми Понд");
        textField.get(1).sendKeys("");
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.tagName("button")).click();
        String text = driver.findElement(By.className("input__sub")).getText();//
        Assertions.assertEquals("Поле обязательно для заполнения.", text.trim());
    }

    @Test
    void shouldTestWithNumbersInsteadNameNegative() {
        driver.get("http://localhost:9999");
        List<WebElement> textField = driver.findElements(By.className("input__control"));
        textField.get(0).sendKeys("+0000000000");
        textField.get(1).sendKeys("+0000000000");
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.tagName("button")).click();
        String text = driver.findElement(By.className("input__sub")).getText();
        Assertions.assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", text.trim());

    }

    @Test
    void shouldTestWithLettersInsteadOfTelNegative() {
        driver.get("http://localhost:9999");
        List<WebElement> textField = driver.findElements(By.className("input__control"));
        textField.get(0).sendKeys("Арагорн Элессар");
        textField.get(1).sendKeys("Арагорн Элессар");
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.tagName("button")).click();
        String text = driver.findElement(By.className("input__sub")).getText();
        Assertions.assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", text.trim());

    }

    @Test
    void shouldTestTwelveNumbersInTesNegative() {
        driver.get("http://localhost:9999");
        List<WebElement> textField = driver.findElements(By.className("input__control"));
        textField.get(0).sendKeys("Арагорн");
        textField.get(1).sendKeys("+399999999999");
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.tagName("button")).click();
        String text = driver.findElement(By.className("input__sub")).getText();
        Assertions.assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", text.trim());
    }

    @Test
    void shouldTestSpacesInsteadOfNameNegative() {
        driver.get("http://localhost:9999");
        List<WebElement> textField = driver.findElements(By.className("input__control"));
        textField.get(0).sendKeys("   ");
        textField.get(1).sendKeys("+70000000000");
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.tagName("button")).click();
        String text = driver.findElement(By.className("input__sub")).getText();
        Assertions.assertEquals("Поле обязательно для заполнения", text.trim());

    }

    @Test
    void shouldTestSpacesInsteadOfTelNegative() {
        driver.get("http://localhost:9999");
        List<WebElement> textField = driver.findElements(By.className("input__control"));
        textField.get(0).sendKeys("Том Бомбадил");
        textField.get(1).sendKeys("     ");
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.tagName("button")).click();
        String text = driver.findElement(By.className("input__sub")).getText();
        Assertions.assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", text.trim());

    }
//    @Test
//    void shouldTestFindElementAndSendKeysOnChromeNegativeNoCheckBoxClick() {
//        driver.get("http://localhost:9999");
//        List<WebElement> textField = driver.findElements(By.className("input__control"));
//        textField.get(0).sendKeys("Понд Эми");
//        textField.get(1).sendKeys("+3999999999");
//        driver.findElement(By.className("checkbox"));
//        driver.findElement(By.tagName("button"));
//        String color = driver.findElement(By.className("checkbox")).getAttribute("#ff5c5c!important");
//        Assertions.assertEquals("#ff5c5c!important", color);
//
//    }
}


