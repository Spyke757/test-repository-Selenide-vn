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

public class BasketCart { //Task 13

    private WebDriver driver;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\tools\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
//add ducks
    @Test
    public void addGoods() {
        driver.get("http://localhost/litecart/en/");
        driver.manage().window().maximize();
        for (int i = 0; i < 3; i++) {
            int qtyBeforeAdd = getQuantity(driver);
            driver.findElement(By.xpath("//*[@id='box-most-popular']/div/ul/li[1]/a[1]")).click();
            selectSize(driver);
            driver.findElement(By.xpath("//button[@name='add_cart_product']")).click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until((ExpectedCondition<Boolean>) driver1 -> getQuantity(driver1) > qtyBeforeAdd);
            driver.findElement(By.xpath("//li[@class='general-0']")).click();
        }
        driver.findElement(By.xpath("//div[@id='cart']/a[3]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        List<WebElement> qtyList = driver.findElements(By.xpath("//td[@class='item']/../td[1]"));
        for (int i = 0; i < qtyList.size(); i++) {
            int qtyBeforeRemove = getQtySum(driver);
            //delete
           driver.findElement(By.xpath("//button[@name='remove_cart_item']")).click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until((ExpectedCondition<Boolean>) driver10 -> getQtySum(driver10) < qtyBeforeRemove);
        }
    }
//checkout
    private int getQtySum(WebDriver driver) {
        List<WebElement> QuantityList = driver.findElements(By.xpath("//td[@class='item']/../td[1]"));
        int qantity = 0;
        for (WebElement size : QuantityList) {
            qantity = qantity + Integer.parseInt(size.getText());
        }
        return qantity;
    }

    //wait upd item
    private int getQuantity(WebDriver driver) {
        WebElement quantity = driver.findElement(By.xpath("//span[@class='quantity']"));
        return Integer.parseInt(quantity.getText());
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