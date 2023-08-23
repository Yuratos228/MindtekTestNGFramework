package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SaucedemoLoginPage;
import pages.SaucedemoMainPage;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.TestBase;

import java.io.IOException;


public class SausedemoTests extends TestBase {


    @Test (priority = 0, groups = {"regression", "smoke", "saucedemo", "login"})
    public void saucedemoLoginPositive(){
       driver.get(ConfigReader.getProperty("SaucedemoURL"));
        WebElement userInput = driver.findElement(By.id("user-name"));
        userInput.sendKeys(ConfigReader.getProperty("SaucedemoUsernamePositive"));

        WebElement passwordInput = driver.findElement(By.xpath("//input[@id='password']"));
        passwordInput.sendKeys(ConfigReader.getProperty("SaucedemoPassword"));

        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        WebElement shoppingCard = driver.findElement(By.id("shopping_cart_container"));

        Assert.assertTrue(shoppingCard.isDisplayed());


    }

    // @Ignore - ignores the @Test method below
    @Test (priority = 1, groups = {"regression", "saucedemo", "login"})
    public void saucedemoLoginNegative(){
        driver.get(ConfigReader.getProperty("SaucedemoURL"));
        WebElement userInput = driver.findElement(By.id("user-name"));
        userInput.sendKeys(ConfigReader.getProperty("SaucedemoUsernameNegative"));

        WebElement passwordInput = driver.findElement(By.xpath("//input[@id='password']"));
        passwordInput.sendKeys(ConfigReader.getProperty("SaucedemoPassword"));

        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        String expectedResult = "Epic sadface: Sorry, this user has been locked out.";
        String actualResult = driver.findElement(By.xpath("//h3[@data-test='error']")).getText();

        // debugging - finding and fixing bugs or failures
        // good practice to add messages to assertion lines, especially
        // when asserting true or false
        Assert.assertEquals(actualResult,expectedResult, "Wrong error message");
    }

    @Test(priority = 2, groups = {"regression", "smoke", "saucedemo",})
    public void sausedemoVerifyPriceLowHigh() throws IOException {

        SaucedemoLoginPage saucedemoLoginPage = new SaucedemoLoginPage();
        saucedemoLoginPage.login();

        //The same implementation as above ^(It stored in SaucedemoLoginPage class like a method)
//        driver.get(ConfigReader.getProperty("SaucedemoURL"));
//        SaucedemoLoginPage saucedemoLoginPage = new SaucedemoLoginPage();
//
//        saucedemoLoginPage.usernameInput.sendKeys(ConfigReader.getProperty("SaucedemoUsernamePositive"));
//
//        saucedemoLoginPage.passwordInput.sendKeys(ConfigReader.getProperty("SaucedemoPassword"));
//
//        saucedemoLoginPage.loginButton.click();

        BrowserUtils.takeScreenshot("Try");

        SaucedemoMainPage saucedemoMainPage = new SaucedemoMainPage();

        Select sortSelect = new Select(saucedemoMainPage.sortDropDown);
        sortSelect.selectByValue("lohi");

        for (WebElement el: saucedemoMainPage.itemPrices){
            System.out.println(el.getText());
        }
        System.out.println("==============================");
        for (int i = 1; i < saucedemoMainPage.itemPrices.size(); i++){

            double item1 = Double.parseDouble(saucedemoMainPage.itemPrices.get(i-1).getText().substring(1));
            double item2 = Double.parseDouble(saucedemoMainPage.itemPrices.get(i).getText().substring(1));

            Assert.assertTrue(item1<=item2);
            System.out.println(item1 + " = " + item2);

        }


    }



}
