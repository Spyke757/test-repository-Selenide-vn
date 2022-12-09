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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CheckCountriesAndGeoZone { //���� �� �������� ���������� ����� � ������
    private WebDriver driver;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\vladislav.nazar\\Documents\\GitHub\\test-repository-Selenide-vn\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @Test
    public void CheckCountries() {
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.manage().window().maximize();
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.xpath("//button[text()='Login']")).click();

        List<WebElement> countriesList = driver.findElements(By.xpath("//tr[@class='row']/td[5]")); //�������� ������ �����
        ArrayList<String> countries = new ArrayList<>();//������� ��������� ��������� ������
        for (int i = 0; i < countriesList.size(); i++) {//����� ���� ���������� ������ ���������� �����
            countries.add(i, countriesList.get(i).getText());
        }
        ArrayList<String> sortedCountries = new ArrayList<>(countries);//������� ��������� ��������� ������ �� ��������
        Collections.sort(sortedCountries);//������������� ������
        for (int i = 0; i < countries.size(); i++) {
            assertEquals(countries.get(i), sortedCountries.get(i));//��������� ���������, ��� �������������
            if (countries.equals(sortedCountries)) {
                System.out.println("��������������� ������: " + sortedCountries);
            } else {
                System.out.println("������ � ������ ����������� �� � ���������� �������");
            }
        }
            List<WebElement> countCountries = driver.findElements(By.xpath("//table[@class='dataTable']//tr[@class='row']//td[6]"));
            for (int j = 0; j < countCountries.size(); j++) {
                WebElement checkzone = countCountries.get(j);
                String check = checkzone.getAttribute("textContent");
                if (check.equals("0")) {
                }
                else {
                    checkzone.findElement(By.xpath("./preceding-sibling::td[1]/a")).click();
                    List<WebElement> zone2 = driver.findElements(By.xpath("//table[@id='table-zones']//td[3]"));
                    int size2 = zone2.size() - 1;
                    List<String> elements = new ArrayList<>();
                    for (int k = 0; k < size2; k++) {
                        elements.add(zone2.get(k).getText());
                    }
                    List<String> sortedelements = new ArrayList<>(elements);
                    Collections.sort(sortedelements);
                    for (int y = 0; y < size2; y++) {
                        if (elements.get(y).equals(sortedelements.get(y))) {
                            System.out.println("������� �� ��������:" +sortedelements);
                        } else {
                            System.out.println("������ ����������� �� �� ��������!");
                            break;
                        }
                    }
                    driver.navigate().back();
                    countCountries = driver.findElements(By.xpath("//table[@class='dataTable']//tr[@class='row']//td[6]"));
                }
            }
        }

    @After
    public void close(){
        driver.quit();
        driver = null;
    }
}