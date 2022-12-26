package LiteCartMagazine;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.By.xpath;

public class ClickEveryElements { //Task 6
    private WebDriver driver;

    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "C:\\tools\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }
    //Нажать на все элементы и проверить наличие заголовка h1
    @Test
    public void clickEveryElements() {
        driver.get("http://localhost/litecart/admin/");
        driver.manage().window().maximize();
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(xpath("//button[text()='Login']")).click();

        List<WebElement> elements = driver.findElements(xpath("//ul[@id='box-apps-menu']/li"));
        String subElement = "//ul[@id='box-apps-menu']/li/ul/li";
        String mainElement = "//td[@id='content']/h1";
        for (int i=1; i<=elements.size(); i++) {
           WebElement element = driver.findElement(xpath("//ul[@id='box-apps-menu']/li[" + i + "]"));
           element.click();
         assertTrue(areElementsPresent(driver, mainElement));

            List<WebElement> subElements = driver.findElements(xpath(subElement));
            if (subElements.size() > 0) {
                for (int j=1; j <= subElements.size(); j++) {
                    WebElement subElementsClick = driver.findElement(xpath("//ul[@id='box-apps-menu']/li/ul/li[" + j + "]"));
                    subElementsClick.click();
                    assertTrue(areElementsPresent(driver, mainElement));
                }
            }
        }
    }

    @After
    public void close(){
        driver.quit();
        driver = null;
    }
    boolean areElementsPresent(WebDriver driver,  String mainElement) {
        return driver.findElements(xpath(mainElement)).size() > 0;
    }
}
