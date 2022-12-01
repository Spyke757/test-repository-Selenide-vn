package LiteCartMagazine;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.omg.CORBA.Environment;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


import java.util.concurrent.TimeUnit;

public class LoginLiteCart {

    private WebDriver driver;

    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\vladislav.nazar\\Documents\\GitHub\\test-repository-Selenide-vn\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

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

