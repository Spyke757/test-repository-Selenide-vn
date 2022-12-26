package LiteCartMagazine;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CreateNewUser { //Task 11
    private WebDriver driver;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\tools\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @Test
    public void create() throws InterruptedException {
        driver.get("http://localhost/litecart/en/");
        driver.manage().window().maximize();
        driver.findElement(By.linkText("New customers click here")).click();
        driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("Vladislav");//имя
        driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("N");//Фамилия
        driver.findElement(By.xpath("//input[@name='address1']")).sendKeys("Trout Ave");//адрес
        driver.findElement(By.xpath("//input[@name='address2']")).sendKeys("8 level");//доп адрес
        driver.findElement(By.xpath("//input[@name='postcode']")).sendKeys("11223");//индекс
        driver.findElement(By.xpath("//input[@name='city']")).sendKeys("Brooklyn");//город
        driver.findElement(By.xpath("//span[@class='select2-selection__arrow']")).click();//страна dropdown
        driver.findElement(By.xpath("//input[@class='select2-search__field']")).sendKeys("United States" + Keys.ENTER);//выбираем us+энтер
        String email = new Date().getTime() + "@email.com";//рандомная почта
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys(email);//вводим почту
        driver.findElement(By.xpath("//input[@name='phone']")).sendKeys("+15189007856");//номер тел
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("12345678");//пароль
        driver.findElement(By.xpath("//input[@name='confirmed_password']")).sendKeys("12345678");//дубль пароля
        driver.findElement(By.xpath("//button[@name='create_account']")).click();//создали учетку
        Thread.sleep(3000);//ожидалка появления
        Select zone = new Select(driver.findElement(By.xpath("//select[@name='zone_code']")));
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].selectedIndex=11", zone);
        javascriptExecutor.executeScript("arguments[0].selectedIndex=11;arguments[0].dispatchEvent(new Event('change'));", zone);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("12345678");
        driver.findElement(By.xpath("//input[@name='confirmed_password']")).sendKeys("12345678");
        driver.findElement(By.xpath("//button[@name='create_account']")).click();
        Thread.sleep(3000);

        String logOutXpath = "//a[text()=\"Logout\"]";//вышли
        driver.findElements(By.xpath(logOutXpath)).get(0).click();
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys(email);//логин
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("12345678");
        driver.findElement(By.xpath("//button[@name='login']")).click();
        driver.findElements(By.xpath(logOutXpath)).get(0).click();
    }

    @After
    public void close() {
        driver.quit();
        driver = null;
    }
}
