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

public class BasketCart2 { //Task 13 upd

    private WebDriver driver;


    @Before
    public void setup() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\tools\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
    }
    //add ducks
    @Test
    public void addGoods() throws InterruptedException {
        driver.get("http://localhost/litecart/en/");
        driver.manage().window().maximize();
        for (int i = 0; i < 3; i++) {
            int qtyBeforeAdd = getQuantityFromCart(driver);
            driver.findElement(By.xpath("//div[@id='box-most-popular']//li/a[1]")).click();
            selectSize(driver);
            driver.findElement(By.xpath("//button[@name='add_cart_product']")).click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until((ExpectedCondition<Boolean>) driver1 -> getQuantityFromCart(driver1) > qtyBeforeAdd);
            driver.findElement(By.xpath("//li[@class='general-0']")).click();
        }
        driver.findElement(By.linkText("Checkout »")).click();
        List<WebElement> qtyList = driver.findElements(By.xpath("//td[@class='item']/../td[1]"));
        for (int i = 0; i < qtyList.size(); i++) {
            int qtyBeforeRemove = getQtySum(driver);
            driver.findElement(By.xpath("//button[@name='remove_cart_item']")).click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until((ExpectedCondition<Boolean>) driver1 -> getQtySum(driver1) < qtyBeforeRemove);
        }
    }

    //select size yellow duck if need it
    private void selectSize(WebDriver driver) {
        if (!driver.findElements(By.xpath("//select[@required='required']")).isEmpty()) {
            driver.findElement(By.xpath("//option[@value='Large']")).click();
        }
    }
    private int getQtySum(WebDriver driver) {
        List<WebElement> QuantityList = driver.findElements(By.xpath("//td[@class='item']/../td[1]"));
        int qantity = 0;
        for (WebElement size : QuantityList) {
            qantity = qantity + Integer.parseInt(size.getText());
        }
        return qantity;
    }

    //    wait upd item
    private int getQuantityFromCart(WebDriver driver) {
        String qtyLocator = "//span[@class='quantity']";
        WebElement quantity = driver.findElement(By.xpath(qtyLocator));
        return Integer.parseInt(quantity.getText());
    }
    @After
    public void close() {
        driver.quit();
        driver = null;
    }
}