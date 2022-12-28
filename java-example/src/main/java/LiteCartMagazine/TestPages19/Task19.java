package LiteCartMagazine.TestPages19;


import LiteCartMagazine.TestPages19.App.LiteCartApplication;
import org.junit.Test;

public class Task19 {
    @Test
    public void addGoods(){
        LiteCartApplication application = new LiteCartApplication();
        application.openMainPage();
        application.addProductToCart(3);
        application.checkoutCart();
        application.removeProductFromCart();
        application.quit();
    }
}
