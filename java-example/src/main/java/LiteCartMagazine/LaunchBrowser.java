package LiteCartMagazine;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;


import java.io.File;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class LaunchBrowser {// Test 2
    //Test 3

    private static WebDriver driver;

    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "C:\\tools\\drivers\\chromedriver.exe");
//        System.setProperty("webdriver.gecko.driver", "C:\\tools\\drivers\\geckodriver.exe");
//        System.setProperty("webdriver.ie.driver","driver path\\IEDriverServer.exe");
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary(new FirefoxBinary(new File("C:\\Users\\vladislav.nazar\\AppData\\Local\\Firefox Nightly\\firefox.exe")));


//        driver = new ChromeDriver();
//        driver = new InternetExplorerDriver();
        driver = new FirefoxDriver();
        driver = new FirefoxDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @Test
    public void LoginLiteCart() {
        driver.get("http://localhost/litecart/admin/");
        driver.manage().window().maximize();

        driver.findElement(By.name("username")).sendKeys("admin");;
        driver.findElement(By.name("password")).sendKeys("admin");;
        driver.findElement(By.xpath("//button[text()='Login']")).click();
    }

    @After
    public void close(){
        driver.quit();
        driver = null;
    }
}
