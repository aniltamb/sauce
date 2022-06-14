package Utility;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;


public class BaseClass {

    public static WebDriver driver;

    public static void launchBrowser(String browser){
       String bro=  browser.toLowerCase();

       switch(bro){
           case "chrome":
               WebDriverManager.chromedriver().setup();
               driver= new ChromeDriver();
               break;
           case "edge":
               WebDriverManager.edgedriver().setup();
               driver= new EdgeDriver();
           default:
               WebDriverManager.chromedriver().setup();
               driver= new ChromeDriver();
       }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.saucedemo.com/");
    }

    public static void tearDown(){
        driver.quit();
    }

    public static void login(String[] credentials){
        driver.findElement(By.xpath("//input[@id='user-name']")).clear();
        driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys(credentials[0]);
        driver.findElement(By.xpath("//input[@id='password']")).clear();
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(credentials[1]);
        driver.findElement(By.xpath("//input[@id='login-button']")).click();
    }

    public static void logout(){
        driver.findElement(By.xpath("//button[@id='react-burger-menu-btn']")).click();
        WebElement logoutButton=driver.findElement(By.xpath("//a[@id='logout_sidebar_link']"));
        explicitWaitForElement(logoutButton,10);
        logoutButton.click();
    }

    public static void explicitWaitForElement(WebElement element, int seconds){
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void screenshotOfPage(String path) throws IOException {
        TakesScreenshot scr = (TakesScreenshot) driver;
        File file = scr.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File(".//screenshot//"+path+".png"));
    }

    public static String[] credentials(int userNumber){
        String str=driver.findElement(By.xpath("//div[@class='login_credentials']")).getText();
        String[] userArray=str.split("\n");
        String username=userArray[userNumber];

        String strr=driver.findElement(By.xpath("//div[@class='login_password']")).getText();
        String[] psw=strr.split("\n");
        String password=psw[1];

        return new String[]{username,password};
    }

    public static void checkout(){
        driver.findElement(By.xpath("//input[@id='first-name']")).sendKeys(Utils.randomString(10));
        driver.findElement(By.xpath("//input[@id='last-name']")).sendKeys(Utils.randomString(10));
        driver.findElement(By.xpath("//input[@id='postal-code']")).sendKeys(Utils.randomNumber(6));
        driver.findElement(By.xpath("//input[@id='continue']")).click();
    }

    public static void finalFinish(){
        driver.findElement(By.xpath("//button[@id='finish']")).click();
    }


    public static void addToCart(){
        String productActual=driver.findElement(By.xpath("//div[@class='inventory_item_name']")).getText();
        driver.findElement(By.xpath("//button[text()='Add to cart']")).click();
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
        String productInCart=driver.findElement(By.xpath("//div[@class='inventory_item_name']")).getText();
        driver.findElement(By.xpath("//button[@id='checkout']")).click();
    }

}
