package saucelab;

import Utility.BaseClass;
import Utility.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;

public class Test extends BaseClass {

    public static void main(String[] args) throws IOException, InterruptedException {
        launchBrowser("chrome");
        driver.get("https://www.saucedemo.com/");

        testUser1();
        testUser2();
        testUser3();
        testUser4();
        tearDown();
    }

    public static void testUser1() throws InterruptedException {
        login(credentials(1));
        System.out.println("user1");
        verifySorting();
        addToCart();
        checkout();
        finalFinish();
        try{
            screenshotOfPage("user1");
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        logout();
    }

    public static void testUser2(){
        System.out.println("User2");
        login(credentials(2));
        String message=driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
        System.out.println(message);
        try{
            screenshotOfPage("user2");
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void testUser3() throws InterruptedException {
        login(credentials(3));
        System.out.println("user3");
        verifySorting();
        addToCart();
        checkout();
        String message=driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
        System.out.println(message);
        try{
            screenshotOfPage("user3");
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        driver.findElement(By.xpath("//button[@id='cancel']")).click();
        driver.findElement(By.xpath("//button[text()='Remove']")).click();
        logout();
    }

    public static void testUser4() throws InterruptedException {
        login(credentials(4));
        System.out.println("user4");
        verifySorting();
        addToCart();
        checkout();
        finalFinish();
        try{
            screenshotOfPage("user4");
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        logout();
    }


    public static void productSort(String text){
        WebElement productSortContainer = driver.findElement(By.xpath("//select[@class='product_sort_container']"));
        Select select= new Select(productSortContainer);
        select.selectByVisibleText(text);
    }

    public static String[] getProductList(){
        List<WebElement> productList = driver.findElements(By.xpath("//div[@class='inventory_item_name']"));
        String[] arr= new String[productList.size()];
        int i=0;
        Iterator<WebElement> productItr = productList.iterator();

        while (productItr.hasNext()){
            arr[i]=productItr.next().getText();
            i++;
        }
        return arr;
    }

    public static double[] getProductPrizeList(){
        List<WebElement> productList = driver.findElements(By.xpath("//div[@class='inventory_item_price']"));
        double[] arr= new double[productList.size()];
        int i=0;
        Iterator<WebElement> productItr = productList.iterator();
        while (productItr.hasNext()){
            String str=productItr.next().getText().substring(1);
            arr[i]= Double.parseDouble(str);
            i++;
        }
        return arr;
    }


    public static void verifySorting() throws InterruptedException {
        productSort("Name (A to Z)");
        String[] productList1 = getProductList();
        System.out.println("Is A to Z filter is working :"+ Utils.isStringArrayAsce(productList1));
        Thread.sleep(2000);

        productSort("Name (Z to A)");
        String[] productList2 = getProductList();
        System.out.println("Is Z to A filter is working :"+ Utils.isStringArrayDesc(productList2));
        Thread.sleep(2000);

        productSort("Price (low to high)");
        double[] productPrize1 = getProductPrizeList();
        System.out.println("Is prize Low to High working :"+ Utils.isDoubleArrayAsc(productPrize1));
        Thread.sleep(2000);

        productSort("Price (high to low)");
        double[] productPrize2 = getProductPrizeList();
        System.out.println("Is prize High to Low working :"+ Utils.isDoubleArrayDesc(productPrize2));
    }
}
