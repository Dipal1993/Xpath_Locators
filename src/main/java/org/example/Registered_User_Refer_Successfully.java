package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Registered_User_Refer_Successfully {
    //Declaration of object
    public static WebDriver driver;
    //make variable to store email id
    public static String Email = "anu" + Date() + "@gmail.com";
    //Make variable to store password
    public static String Password = "anu@" + Date() + "10012";
    static String Expected = "Your registration completed";
    public static String Actual;


    @BeforeMethod
    public static void Open_Browser() {
        //initialization of object
        driver = new ChromeDriver();
        //maximize the window
        driver.manage().window().maximize();
        //open the browser
        driver.get("https://demo.nopcommerce.com/");
    }

    @AfterMethod
    public static void close_Browser(){
        driver.quit();
    }
//timestamp
    public static String Date() {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    //click method
    public static void Click_Element(By by)
    {
        driver.findElement(by).click();
    }
    //sendkey method
    public static String SendKey_Element(By by, String Text)
    {
        driver.findElement(by).sendKeys(Text);
        return Text;
    }
    public static String Gettext(By by) {
        return driver.findElement(by).getText();
    }
    @Test
    public static boolean User_Should_Register_Successfully() {
        //click kon register button
        Click_Element(By.linkText("Register"));
        //use (tag#id) selector
        Click_Element(By.cssSelector("input#gender-female"));
        //enter first name
        SendKey_Element(By.id("FirstName"), "anu");
        //enter last name
        SendKey_Element(By.name("LastName"), "Patel");
        //send the email to method
        SendKey_Element(By.id("Email"), Email);
        //enter company name
        SendKey_Element(By.id("Company"), "amazon");
        //password
        SendKey_Element(By.xpath("//input[@type='password' and @id='Password']"), Password);
        //confirm password
        SendKey_Element(By.xpath("//input[@id='ConfirmPassword' or @data-val-equalto-other='*.Password']"), Password);
        //click on register button
        Click_Element(By.name("register-button"));
        Actual = Gettext(By.className("result"));
        //compare the actual and expected value
       Assert.assertEquals(Actual, Expected, "your Registration is fail.");
        //click on continue button
        Click_Element(By.xpath("/html/body/div[6]/div[3]/div/div/div/div[2]/div[2]/a"));
        return Actual.equals(Expected);
    }

    @Test
    public static boolean User_login_successfully() {
        boolean loginSuccessful = false; // Assume login fails initially
        if (User_Should_Register_Successfully()) {
            //click on login button
            Click_Element(By.xpath("//a[@class='ico-login']"));
            //enter email id
            SendKey_Element(By.xpath("//input[@id='Email' and @class='email']"),Email);
            //enter Password
            SendKey_Element(By.xpath("//input[@name='Password' and @class='password']"), Password);
            //check the Remember me checkbox
            Click_Element(By.xpath("//input[@id='RememberMe' and @name='RememberMe']"));
            //Click on login button
            Click_Element(By.xpath("/html/body/div[6]/div[3]/div/div/div/div[2]/div[1]/div[2]/form/div[3]/button"));
            loginSuccessful = true; // Set login status to true if login is successful
        } else {
            Assert.fail("Registration failed. Cannot proceed with login.");
        }
        return loginSuccessful; // Return login status
    }
    @Test
    public static void Refer_the_Product_to_Friend() {
        if (User_login_successfully()) {
            //click on Electronics category
            Click_Element(By.linkText("Electronics"));
            //click on camera and photoes category
            Click_Element(By.xpath("/html/body/div[6]/div[3]/div/div[3]/div/div[2]/div[1]/div/div[1]/div/div/a/img"));
            //Click on Nikon D5500 DSLR product
            Click_Element(By.xpath("//img[@title='Show details for Nikon D5500 DSLR']"));
            //click on email a friend button
            Click_Element(By.xpath("//*[@id='product-details-form']/div[2]/div[1]/div[2]/div[4]/div[2]/button"));
            //Enter the email id of friend
            SendKey_Element(By.xpath("//input[@name='FriendEmail']"), "mahima123@gmail.com");
            //Enter your email address
            SendKey_Element(By.xpath("//*[@id='YourEmailAddress']"),"");
            //Enter the Personal message
            SendKey_Element(By.xpath("//textarea[@class='your-email']"), "It is nice product.");
            //Click on send Email button
            Click_Element(By.xpath("//button[@name='send-email']"));
        } else {
            Assert.fail("Login failed. Cannot proceed with referring the product to a friend.");
        }
    }

}
