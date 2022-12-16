package LiteCartMagazine;

import com.beust.jcommander.Parameters;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class CheckPage {
    private WebDriver driver;

    @Before()
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\vladislav.nazar\\Documents\\GitHub\\test-repository-Selenide-vn\\drivers\\chromedriver.exe");
//        System.setProperty("webdriver.gecko.driver", "C:\\Users\\vladislav.nazar\\Documents\\GitHub\\test-repository-Selenide-vn\\drivers\\geckodriver.exe");
//        System.setProperty("webdriver.ie.driver","driver path\\IEDriverServer.exe");

        driver = new ChromeDriver();
//       driver = new InternetExplorerDriver();
//        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @Test
    public void checkTitlesMainPage (){// Проверка совпадает текст названия товара на странице и внутри
        driver.get("http://localhost/litecart/en/");
        driver.manage().window().maximize();
        WebElement duck = driver.findElement(By.xpath("//div[@id='box-campaigns']//ul[@class='listing-wrapper products']/li[@class='product column shadow hover-light']//img"));
        WebElement yellowDuckMainName = driver.findElement(By.xpath("//div[@id='box-campaigns']//div[@class='name']"));
        String checkName = yellowDuckMainName.getAttribute("textContent");
        duck.click();
        WebElement yellowDuckPage = driver.findElement(By.xpath("//h1[@class='title']"));
        String checkNamePage = yellowDuckPage.getAttribute("textContent");
         if (checkName.equals(checkNamePage)){
            System.out.println("The titles are the same on the pages are the same");
        } else {
             System.out.println("The titles don't match, the pages match");
         }
    }
    @Test
    public void checkPricesMainPage() {// проверка цен обычная и акция
        driver.get("http://localhost/litecart/en/");
        driver.manage().window().maximize();
        WebElement duck = driver.findElement(By.xpath("//div[@id='box-campaigns']//ul[@class='listing-wrapper products']/li[@class='product column shadow hover-light']//img"));
        WebElement priceRegular = driver.findElement(By.xpath("//div[@id='box-campaigns']//s[@class='regular-price']"));//обычная цена
        WebElement priceСampaign = driver.findElement(By.xpath("//div[@id='box-campaigns']//strong[@class='campaign-price']"));//цена по скидке
        String checkPriceRegularMain = priceRegular.getAttribute("textContent");
        String checkPriceCampaignMain = priceСampaign.getAttribute("textContent");
        duck.click();
        WebElement priceRegularPage = driver.findElement(By.xpath("//div[@class='content']//s[@class='regular-price']"));
        WebElement priceCampaignPage = driver.findElement(By.xpath("//div[@class='content']//strong[@class='campaign-price']"));
        String checkPriceRegularPage = priceRegularPage.getAttribute("textContent");
        String checkPriceCampaignPage = priceCampaignPage.getAttribute("textContent");
        if (checkPriceRegularMain.equals(checkPriceRegularPage)){
            System.out.println("The prices regular on the second page are the same");
        } else if (checkPriceCampaignMain.equals(checkPriceCampaignPage)) {
            System.out.println("The prices Campaign on the second page are the same");
        } else {
            System.out.println("Different prices");
        }
    }
    @Test
    public void checkCrossedGray() {//проверка на зачеркнутость и цвета rgb
        driver.get("http://localhost/litecart/en/");
        driver.manage().window().maximize();
        WebElement duck = driver.findElement(By.xpath("//div[@id='box-campaigns']//ul[@class='listing-wrapper products']/li[@class='product column shadow hover-light']//img"));
        WebElement priceRegular = driver.findElement(By.xpath("//div[@id='box-campaigns']//s[@class='regular-price']"));//обычная цена
        String priceRegularColor = priceRegular.getCssValue("color");
        System.out.println(priceRegularColor);
        String[] colors;
        if (priceRegularColor.contains("rgba")) colors = priceRegularColor.replace("rgba(", "").replace(")", "").split(",");
        else colors = priceRegularColor.replace("rgb(", "").replace(")", "").split(",");
        int r = Integer.parseInt(colors[0].trim());
        int g = Integer.parseInt(colors[1].trim());
        int b = Integer.parseInt(colors[2].trim());
        int a = Integer.parseInt(colors[3].trim());
        String regularColorStyle = priceRegular.getCssValue("text-decoration-style");
        duck.click();
        WebElement regularPricePage = driver.findElement(By.xpath("//div[@class='information']//s[@class='regular-price']"));
        String priceRegularColorPage = regularPricePage.getCssValue("color");//цвет обычной цены в окне продукта
        String[] colorsPage = priceRegularColorPage.replace("rgba(", "").replace(")", "").split(", ");
        int r2 = Integer.parseInt(colorsPage[0]);
        int g2 = Integer.parseInt(colorsPage[1]);
        int b2 = Integer.parseInt(colorsPage[2]);
        int a2 = Integer.parseInt(colorsPage[3]);
        String regularColorStylePage = regularPricePage.getCssValue("text-decoration-style");

        if (r==g||g==b){
            System.out.println("The regular price color is gray");
        } else {
            System.out.println("Color isnt gray");
        }
        if (regularColorStyle.equals("solid")){
            System.out.println("The regular price font on the product page is strikethrough");
        } else {
            System.out.println("The regular price font on the product page isn't strikethrough");
        }
        if (r2==g2||g2==b2) {
            System.out.println("The regular price color on page is gray");
        } else {
            System.out.println("Color on page isn't gray");
        }
        if (regularColorStylePage.equals("solid")){
            System.out.println("The regular price font on second page is strikethrough");
        } else {
            System.out.println("The regular price font on second page isn't strikethrough");
        }
    }

    @Test
    public void checkFatRedColor() {//Проверка на жирный шрифт и красный
        driver.get("http://localhost/litecart/en/");
        driver.manage().window().maximize();
        WebElement duck = driver.findElement(By.xpath("//div[@id='box-campaigns']//ul[@class='listing-wrapper products']/li[@class='product column shadow hover-light']//img"));
        WebElement priceСampaign = driver.findElement(By.xpath("//div[@id='box-campaigns']//strong[@class='campaign-price']"));//цена по скидке
        String elementCampaignMainColor = priceСampaign.getCssValue("color");
        String[] colors = elementCampaignMainColor.replace("rgba(", "").replace(")", "").split(", ");
        int g = Integer.parseInt(colors[1]);
        int b = Integer.parseInt(colors[2]);
        String checkFontFat = priceСampaign.getCssValue("font-weight");
        duck.click();
        WebElement priceCampaignPage = driver.findElement(By.xpath("//div[@class='content']//strong[@class='campaign-price']"));
        String elementCampaignPageColor = priceCampaignPage.getCssValue("color");
        String[] colorsPage = elementCampaignPageColor.replace("rgba(", "").replace(")", "").split(", ");
        int g2 = Integer.parseInt(colorsPage[1]);
        int b2 = Integer.parseInt(colorsPage[2]);
        String checkFontFatPage = priceCampaignPage.getCssValue("font-weight");
        if (g==0||b==0){
            System.out.println("The campaign price color is red");
        } else {
            System.out.println("Color isn't red!");
        }
        if (Integer.parseInt(checkFontFat)>=700) {
            System.out.println("Promotional price font is bold");
        }else {
            System.out.println("Promotional price font is bold isn't bold!");
        }
        if (g2==0||b2==0){
            System.out.println("The campaign price color on second page is red");
        } else {
            System.out.println("The campaign price color on second page isn't red!");
        }
        if (Integer.parseInt(checkFontFatPage)>=700) {
            System.out.println("Promotional price font on second page is bold");
        } else {
            System.out.println("Promotional price font on second page isn't bold!");
        }
    }
    @Test
    public void checkSizePromoLargerRegular() { // акционная цена крупнее, чем обычная
        driver.get("http://localhost/litecart/en/");
        driver.manage().window().maximize();
        WebElement duck = driver.findElement(By.xpath("//div[@id='box-campaigns']//ul[@class='listing-wrapper products']/li[@class='product column shadow hover-light']//img"));
        WebElement regularPrice = driver.findElement(By.xpath("//div[@id='box-campaigns']//s[@class='regular-price']"));//обычная цена
        String sizeRegularPrice = regularPrice.getCssValue("font-size");//размер обычной цены на главной странице
        String ChangeFont = sizeRegularPrice.replaceAll("px", " ");
        double mainPrice = Double.valueOf(ChangeFont);
        WebElement promoPrice = driver.findElement(By.xpath("//div[@id='box-campaigns']//strong[@class='campaign-price']"));//акционная цена
        String sizePromoPrice = promoPrice.getCssValue("font-size");//размер цены по акции
        String ChangeFontPromo = sizePromoPrice.replaceAll("px", " ");//убираем px
        double PromoPrice = Double.valueOf(ChangeFontPromo);//получили размер фона
        duck.click();
        String sizeRegularPricePage = sizePromoPrice.replaceAll("px", " ");//размер обычной цены на экране продукта
        double mainPricePage = Double.valueOf(sizeRegularPricePage);
        WebElement size_product_sale_price = driver.findElement(By.xpath("//strong[@class='campaign-price']"));//акционная цена на второй странице
        String sizePromoPricePage = size_product_sale_price.getCssValue("font-size");//размер акционной цены на второй странице
        String ChangeFontPromoPage = sizePromoPricePage.replaceAll("px", " ");//убрали px на второй стр
        double PromoPricePage = Double.valueOf(ChangeFontPromoPage);

        if (mainPrice > PromoPrice) {
            System.out.println("Promo price is bigger than regular");
        } else {
            System.out.println("Promo price is less than regular");
        if (mainPricePage > PromoPricePage) {
            System.out.println("Promo price is higher than regular on second page");
        } else {
            System.out.println("Promo price is less than regular on the second page");
            }
        }
    }

    @After
    public void close(){
        driver.quit();
        driver = null;
    }
}
