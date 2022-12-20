package LiteCartMagazine;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import static java.lang.Thread.sleep;


public class CheckOpenesNewWindows { //Task 14
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\vladislav.nazar\\Documents\\GitHub\\test-repository-Selenide-vn\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @Test
    public void checkWindow() {
        driver.get("http://localhost/litecart/admin/");//admin
        driver.manage().window().maximize();//full size
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.xpath("//button[text()='Login']")).click();
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");//get countries page
        driver.findElement(By.linkText("Albania")).click();
        String mainWindow = driver.getWindowHandle();
        final Set<String> oldWindowsSet = driver.getWindowHandles();

        List<WebElement> links = driver.findElements(By.xpath("//i[@class='fa fa-external-link']"));
        for (WebElement link : links) {
            link.click();
            String newWindow = getString(driver, oldWindowsSet);
            driver.switchTo().window(newWindow);
            new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            driver.close();
            driver.switchTo().window(mainWindow);
        }
        driver.switchTo().window(mainWindow);
        driver.quit();
    }
    public String getString(WebDriver driver, Set<String> oldWindowsSet) {
        String newWindow = (new WebDriverWait(driver, Duration.ofSeconds(10)))
                .until(new ExpectedCondition<String>() {
                           public String apply(WebDriver driver) {
                               Set<String> newWindowsSet = driver.getWindowHandles();
                               newWindowsSet.removeAll(oldWindowsSet);
                               return newWindowsSet.size() > 0 ?
                                       newWindowsSet.iterator().next() : null;
                           }
                       }
                );
        return newWindow;
    }
}