package LiteCartMagazine;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.time.Duration;
import java.time.LocalDateTime;


public class AddNewProduct {//Task 12 upd
    private WebDriver driver;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\tools\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }
    @Test
    public void addProduct() throws InterruptedException {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");;
        driver.findElement(By.name("password")).sendKeys("admin");;
        driver.findElement(By.xpath("//button[text()='Login']")).click();
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//span[text()='Catalog']")).click();
        driver.findElement(By.linkText("Add New Product")).click();
        //General
        driver.findElement(By.xpath("//input[@type='radio' and @value=1]")).click();
        String rootName = generateName();
        driver.findElement(By.xpath("//input[@name='name[en]']")).sendKeys(rootName);
        driver.findElement(By.xpath("//input[@name='code']")).sendKeys("rd0009");
        driver.findElement(By.xpath("//input[@type='checkbox' and @value=1]")).click();
        driver.findElement(By.xpath("//select[@name='default_category_id']")).click();
        driver.findElement(By.name("default_category_id")).click();
        driver.findElement(By.xpath("//input[@value='1-3']")).click();
        driver.findElement(By.xpath("//input[@name = 'quantity']")).clear();
        driver.findElement(By.xpath("//input[@name = 'quantity']")).sendKeys("50");
        File upload = new File("duck.png");
        String absolute = upload.getAbsolutePath();
        driver.findElement(By.xpath("//input[@name='new_images[]']")).sendKeys(absolute);
        driver.findElement(By.xpath("//input[@type='date' and @name='date_valid_from']")).clear();
        driver.findElement(By.xpath("//input[@type='date' and @name='date_valid_from']")).sendKeys("01.12.2022");
        driver.findElement(By.xpath("//input[@type='date' and @name='date_valid_to']")).clear();
        driver.findElement(By.xpath("//input[@type='date' and @name='date_valid_to']")).sendKeys("31.12.2022");
        Thread.sleep(1000);

        //Information
        driver.findElement(By.xpath("//a[@href='#tab-information']")).click();
        driver.findElement(By.xpath("//select[@name='manufacturer_id']/option[@value='1']")).click();
        driver.findElement(By.xpath("//input[@name='keywords']")).sendKeys("Ducks for all");
        driver.findElement(By.xpath("//input[@name='short_description[en]']")).sendKeys("Something new!");
        driver.findElement(By.xpath("//*[@class='trumbowyg-editor']")).sendKeys("This is something new from the duck collection!");
        driver.findElement(By.xpath("//input[@name='head_title[en]']")).sendKeys("New duck");
        driver.findElement(By.xpath("//input[@name='meta_description[en]']")).sendKeys("New duck");
        Thread.sleep(1000);

        //Prices
        driver.findElement(By.xpath("//a[@href='#tab-prices']")).click();
        driver.findElement(By.xpath("//input[@name = 'purchase_price']")).clear();
        driver.findElement(By.xpath("//input[@name = 'purchase_price']")).sendKeys("30");
        driver.findElement(By.xpath("//select[@name='purchase_price_currency_code']")).click();
        driver.findElement(By.xpath("//select[@name='purchase_price_currency_code']")).sendKeys("US Dollars");
        driver.findElement(By.xpath("//input[@name = 'prices[USD]']")).sendKeys("25");
        driver.findElement(By.xpath("//input[@name = 'prices[EUR]']")).sendKeys("30");
        driver.findElement(By.xpath("//button[@name='save']")).click();
        driver.findElement(By.linkText(rootName)).click();
        Thread.sleep (1000);

        //check
        driver.get("http://localhost/litecart/en/");
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,1000)");
        Thread.sleep (1000);
    }
    public String generateName() {
        String time = LocalDateTime.now().toString();
        time = time.replace(":", "").replace(".", "").replace("-", "");
        return time;
    }

    @After
    public void close() {
        driver.quit();
        driver = null;
    }
}
