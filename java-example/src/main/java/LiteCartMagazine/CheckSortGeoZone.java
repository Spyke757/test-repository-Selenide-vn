package LiteCartMagazine;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CheckSortGeoZone { //Task 9
    private WebDriver driver;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\vladislav.nazar\\Documents\\GitHub\\test-repository-Selenide-vn\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void CheckSortGeoZone () {
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        driver.manage().window().maximize();
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.xpath("//button[text()='Login']")).click();

        List <WebElement> countries = driver.findElements(By.xpath("//table[@class='dataTable']//tr[@class='row']//td[3]"));//получили список стран
        for (int i = 0; i < countries.size(); i++) {//в цикле перебрали размер
            countries = driver.findElements(By.xpath("//table[@class='dataTable']//tr[@class='row']//td[3]/a"));//получаем размер стран
            countries.get(i).click();//нажимаем на полученную страну
            List<WebElement> zone = driver.findElements(By.xpath("//table[@id='table-zones']//td[3]//select[contains(@name,'zones[')]//option[@selected]"));//получаем список стран по алфавиту в выбр стране
            List<String> sortedelements = new ArrayList<>();//записали в массив список стран
            for (int j=0; j < zone.size(); j++) {
                sortedelements.add(zone.get(j).getText());
            }
            List<String> allcountries = new ArrayList<>(sortedelements);
            Collections.sort(sortedelements);
            for (int j=0; j < zone.size(); j++) {
                if (allcountries.get(j).equals(sortedelements.get(j))) {
                }
                else {
                    System.out.println("Страны расположены не по алфавиту!");
                    break;
                }
            }
            driver.navigate().back();
        }
    }
    @After
    public void close(){
        driver.quit();
        driver = null;
    }
}
