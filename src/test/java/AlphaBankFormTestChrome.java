
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
        String text = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
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
        String text = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
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
        String text = driver.findElement(By.cssSelector("[data-test-id=name] .input__sub")).getText();
        Assertions.assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", text.trim());
    }

    @Test
    void shouldTestWithTenTelNumbersNegative() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("input[name=name]")).sendKeys("Доктор Тардис");
        driver.findElement(By.cssSelector("input[name=phone]")).sendKeys("+7999999999");
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.tagName("button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=phone] .input__sub")).getText().trim();
        Assertions.assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", text.trim());

    }

    @Test
    void shouldTestWithNumbersInNameNegative() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("input[name=name]")).sendKeys("Понд Эми6");
        driver.findElement(By.cssSelector("input[name=phone]")).sendKeys("+10000000000");
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.tagName("button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=name] .input__sub")).getText().trim();
        Assertions.assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", text.trim());
    }

    @Test
    void shouldTestWithNoPlusIntelNegative() {
        driver.get("http://localhost:9999");

        driver.findElement(By.cssSelector("input[name=name]")).sendKeys("Понд Эми");
        driver.findElement(By.cssSelector("input[name=phone]")).sendKeys("39999999999");
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.tagName("button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=phone] .input__sub")).getText().trim();
        Assertions.assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", text.trim());
    }

    @Test
    void shouldTestWithNoNameNegative() {
        driver.get("http://localhost:9999");

        driver.findElement(By.cssSelector("input[name=name]")).sendKeys("");
        driver.findElement(By.cssSelector("input[name=phone]")).sendKeys("+39999999999");
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.tagName("button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=name] .input__sub")).getText().trim();
        Assertions.assertEquals("Поле обязательно для заполнения", text.trim());
    }

    @Test
    void shouldTestWithNoTelNegative() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("input[name=name]")).sendKeys("Эми Понд");
        driver.findElement(By.cssSelector("input[name=phone]")).sendKeys("");
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.tagName("button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=phone] .input__sub")).getText().trim();
        Assertions.assertEquals("Поле обязательно для заполнения", text.trim());
    }

    @Test
    void shouldTestWithNumbersInsteadNameNegative() {
        driver.get("http://localhost:9999");

        driver.findElement(By.cssSelector("input[name=name]")).sendKeys("+0000000000");
        driver.findElement(By.cssSelector("input[name=phone]")).sendKeys("+0000000000");
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.tagName("button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=name] .input__sub")).getText().trim();
        Assertions.assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", text.trim());

    }

    @Test
    void shouldTestWithLettersInsteadOfTelNegative() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("input[name=name]")).sendKeys("Арагорн Элессар");
        driver.findElement(By.cssSelector("input[name=phone]")).sendKeys("Арагорн Элессар");
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.tagName("button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=phone] .input__sub")).getText().trim();
        Assertions.assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", text.trim());

    }

    @Test
    void shouldTestTwelveNumbersInTesNegative() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("input[name=name]")).sendKeys("Арагорн");
        driver.findElement(By.cssSelector("input[name=phone]")).sendKeys("+399999999999");
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.tagName("button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=phone] .input__sub")).getText().trim();
        Assertions.assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", text.trim());
    }

    @Test
    void shouldTestSpacesInsteadOfNameNegative() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("input[name=name]")).sendKeys("   ");
        driver.findElement(By.cssSelector("input[name=phone]")).sendKeys("+70000000000");
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.tagName("button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=name] .input__sub")).getText().trim();
        Assertions.assertEquals("Поле обязательно для заполнения", text.trim());

    }

    @Test
    void shouldTestSpacesInsteadOfTelNegative() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("input[name=name]")).sendKeys("Том Бомбадил");
        driver.findElement(By.cssSelector("input[name=phone]")).sendKeys("     ");
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.tagName("button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=phone] .input__sub")).getText().trim();
        Assertions.assertEquals("Поле обязательно для заполнения", text.trim());

    }
}


