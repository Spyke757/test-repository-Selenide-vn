package LiteCartMagazine.TestPages19.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends BasePage {

    @FindBy(xpath = "//*[@id='box-most-popular']/div/ul/li[1]")
    public WebElement firstProduct;
    @FindBy(xpath = "//span[@class='quantity']")
    public WebElement quantity;

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get("http://localhost/litecart/en/");
        driver.manage().window().maximize();
    }

    public int getQuantityFromCart() {
        return Integer.parseInt(quantity.getText());
    }
}