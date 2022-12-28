package LiteCartMagazine.TestPages19.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.List;

public class CartPage extends BasePage {
    @FindBy(xpath = "//button[@name='remove_cart_item']")
    public WebElement removeProductButton;
    @FindBy(linkText = "Checkout »")
    public WebElement checkoutCartButton;

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public int getQtySum() {
        String qtyLocator = "//td[@class='item']/../td[1]";
        List<WebElement> qtyList = driver.findElements(By.xpath(qtyLocator));
        int qantity = 0;
        for (WebElement qty : qtyList) {
            qantity = qantity + Integer.parseInt(qty.getText());
        }
        return qantity;
    }

    public int getQtySum(WebDriver driver) {
        String qtyLocator = "//td[@class='item']/../td[1]";
        List<WebElement> qtyList = driver.findElements(By.xpath(qtyLocator));
        int qantity = 0;
        for (WebElement qty : qtyList) {
            qantity = qantity + Integer.parseInt(qty.getText());
        }
        return qantity;
    }

    public void getQtyWithTimeOut(int qtyBeforeRemove) {
        wait.until((ExpectedCondition<Boolean>) driver1 -> getQtySum(driver1) < qtyBeforeRemove);
    }
}

