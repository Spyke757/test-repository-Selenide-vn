package LiteCartMagazine;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;

import java.time.Duration;
import java.util.List;
import java.util.logging.Level;


public class LogsBrowser { //Task 17 upd
    private WebDriver driver;
    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\tools\\drivers\\chromedriver.exe");
        LoggingPreferences prefs = new LoggingPreferences();
        prefs.enable("browser", Level.ALL);
        ChromeOptions options = new ChromeOptions();
        options.setCapability(CapabilityType.LOGGING_PREFS, prefs);
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @Test
    public void browserLogs() {
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");//admin
        driver.manage().window().maximize();//full size
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.xpath("//button[text()='Login']")).click();
        System.out.println(driver.manage().logs().getAvailableLogTypes());
        driver.manage().logs().get("browser").forEach(l-> System.out.println(l));
        List<WebElement> links = driver.findElements(By.xpath("//table[@class='dataTable']//td[3]/a"));
        for (int i = 0; i < links.size(); i++) {
            List<WebElement> product = driver.findElements(By.xpath("//table[@class='dataTable']//td[3]/a"));
            product.get(i).click();
            driver.manage().logs().get("browser").forEach(l-> System.out.println(l));
            driver.manage().logs().get("browser")
                    .getAll().forEach(logEntry -> System.out.println("BROWSER: " + logEntry));

            driver.navigate().back();
        }
    }
        @After
        public void close(){
            driver.quit();
            driver = null;
        }
    }