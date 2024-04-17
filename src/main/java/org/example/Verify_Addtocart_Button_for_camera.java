package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.List;

public class Verify_Addtocart_Button_for_camera {

    public static WebDriver driver;

    public static void clickelement(By by){
        driver.findElement(by).click();
    }
    @Test
    public static void Verify_Addtocart_Button_for_camera() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com");
        clickelement(By.linkText("Electronics"));
        clickelement(By.linkText("Camera & photo"));
        List<WebElement> Details_Section = driver.findElements(By.className("details"));
        System.out.println("No of Detail Section: "+Details_Section.size());
        List<WebElement> AddToCartbuttons = driver.findElements(By.xpath("//button[@class='button-2 product-box-add-to-cart-button']"));
        System.out.println("No of ADD TO CART Buttons: "+AddToCartbuttons.size());
        WebElement AddtoCartButton;
        for (int i = 0; i < Details_Section.size(); i++) {
            WebElement product = Details_Section.get(i);
            boolean hasAddToCartButton = AddToCartbuttons.size() > i;
            System.out.println("Product " + (i + 1) + ": " + product.getText() + " - Has 'Add to Cart' button: " + hasAddToCartButton);
        }
    }

}
