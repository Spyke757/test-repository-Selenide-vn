package LiteCartMagazine.TestPages19.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class ProductPage extends BasePage {
    @FindBy(xpath = "//li[@class='general-0']")
    public WebElement homePage;
    @FindBy(xpath = "//button[@name='add_cart_product']")
    public WebElement addCartButton;

    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void getQtyWithTimeOut(int qtyBeforeAdd) {
        wait.until((ExpectedCondition<Boolean>) driver1 -> ProductPage.this.getQuantityFromCart(driver1) > qtyBeforeAdd);
    }

    public void selectSize() {
        if (!driver.findElements(By.xpath("//select[@required='required']")).isEmpty()) {
            driver.findElement(By.xpath("//option[@value='Large']")).click();
        }
    }

    public int getQuantityFromCart(WebDriver driver) {
        String qtyLocator = "//span[@class='quantity']";
        WebElement quantity = driver.findElement(By.xpath(qtyLocator));
        return Integer.parseInt(quantity.getText());
    }
}