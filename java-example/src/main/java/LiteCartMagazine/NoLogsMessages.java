package LiteCartMagazine;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;


public class NoLogsMessages { //Task 17
    private WebDriver driver;
    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\vladislav.nazar\\Documents\\GitHub\\test-repository-Selenide-vn\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        ChromeOptions options = new ChromeOptions();
        DesiredCapabilities caps = new DesiredCapabilities(options);
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        caps.setCapability(ChromeOptions.CAPABILITY, logPrefs);
        options.setCapability(ChromeOptions.CAPABILITY, caps);

    }
    @Test
    public void browserLogs() {
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");//admin
        driver.manage().window().maximize();//full size
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.xpath("//button[text()='Login']")).click();
        List<WebElement> links = driver.findElements(By.xpath("//table[@class='dataTable']//td[3]/a"));
        for (int i = 0; i < links.size(); i++) {
            List<WebElement> product = driver.findElements(By.xpath("//table[@class='dataTable']//td[3]/a"));
            product.get(i).click();
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