package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.*;
import org.testng.annotations.*;
import java.lang.String;
import java.text.*;
import java.util.*;

public class User_Registered_Successfully {
    public static WebDriver Driver;
    static String Expected = "Your registration completed";
    @BeforeMethod
    //open the browser
    public static void openbrowser() {
        // Initialize a new instance of ChromeDriver
        Driver = new ChromeDriver();
        //For maximize the window
        Driver.manage().window().maximize();
        //Open the URL
        Driver.get("https://demo.nopcommerce.com/");
    }


    //click Event
    public static void clickelement(By by)
    {
        Driver.findElement(by).click();
    }
    //Method for sendkeyelement
    public static void sendkeyelement(By by, String Text)
    {
        Driver.findElement(by).sendKeys(Text);
    }
    //method for taking timestamp
    public static String Date() {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        Date date = new Date();
        return dateFormat.format(date);
    }
    //Method for GetElement
    public static String GetElement(By by)
    {
        return Driver.findElement(by).getText();
    }
    @Test
    public static void Verifing_the_user_Register_sucessfully(){
        // click on Register button
        clickelement(By.linkText("Register"));
        //call the method for firstname
        sendkeyelement((By.id("FirstName")), "Janvi");
        //call the method for Last Name
        sendkeyelement(By.id("LastName"), "Parikha");
        //call the method for email ID
        sendkeyelement(By.id("Email"), "Janv1" + Date() + "@gmail.com");
        //call the method for company name
        sendkeyelement(By.name("Company"), "E-commarce");
        //call the method for Enter Password
        sendkeyelement(By.name("Password"), "Janvi@1993");
        //call the method for ConfirmPassword
        sendkeyelement(By.name("ConfirmPassword"), "Janvi@1993");
        //Click on Register button
        clickelement(By.name("register-button"));
        //Calling Get_element method
        String Actual = GetElement(By.className("result"));
        //comparing the expected and actual value
        Assert.assertEquals(Actual,Expected,"Your Testcase is fail");
    }
    @AfterMethod
    //close browser method
    public static void closebrowser() {
        Driver.quit();
    }
}
