package LiteCartMagazine;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;


import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.By.xpath;


public class CheckStikers { //Task 7

    private WebDriver driver;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\tools\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

//метод проверки наличия стикеров new/sale
    @Test
    public void CheckStikers() {
        driver.get("http://localhost/litecart/en/");
        driver.manage().window().maximize();

        List<WebElement> ducks = driver.findElements(xpath("//ul[@class='listing-wrapper products']/li"));
        for (WebElement duck: ducks) {

            List<WebElement> stickers = duck.findElements(By.xpath(".//div[contains(@class,'sticker')]"));
            assertEquals(1, stickers.size());

        }
    }
        @After
        public void close () {
            driver.quit();
            driver = null;
        }
    }