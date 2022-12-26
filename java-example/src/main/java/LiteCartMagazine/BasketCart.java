package LiteCartMagazine;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static java.lang.Thread.sleep;

public class BasketCart { //Task 13 upd

    private WebDriver driver;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\tools\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
//add ducks
    @Test
    public void addGoods() throws InterruptedException {
        driver.get("http://localhost/litecart/en/");
        driver.manage().window().maximize();
        for (int i = 0; i < 3; i++) {
            driver.findElement(By.xpath("//div[@id='box-most-popular']//li/a[1]")).click();
            selectSize(driver);
            driver.findElement(By.xpath("//button[@name='add_cart_product']")).click();
            sleep(1000);
            driver.findElement(By.xpath("//li[@class='general-0']")).click();
        }
        driver.findElement(By.linkText("Checkout »")).click();
        List<WebElement> qtyList = driver.findElements(By.xpath("//td[@class='item']/../td[1]"));
        for (int i = 0; i < qtyList.size(); i++) {

            //delete
           driver.findElement(By.xpath("//button[@name='remove_cart_item']")).click();
           sleep(1500);
        }
    }

    //select size yellow duck if need it
    private void selectSize(WebDriver driver) {
        if (!driver.findElements(By.xpath("//select[@required='required']")).isEmpty()) {
           driver.findElement(By.xpath("//option[@value='Large']")).click();
        }
    }
    @After
    public void close() {
        driver.quit();
        driver = null;
    }
}