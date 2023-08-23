package homeTasks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SaucedemoLoginPage;
import pages.SaucedemoMainPage;
import utilities.ConfigReader;
import utilities.TestBase;

public class SausedemoPractice extends TestBase {


    @Test(priority = 0)
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
    @Test (priority = 1)
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

    @Test
    public void sausedemoVerifyPriceLowHigh(){

        driver.get(ConfigReader.getProperty("SaucedemoURL"));
        SaucedemoLoginPage saucedemoLoginPage = new SaucedemoLoginPage();

        saucedemoLoginPage.usernameInput.sendKeys(ConfigReader.getProperty("SaucedemoUsernamePositive"));

        saucedemoLoginPage.passwordInput.sendKeys(ConfigReader.getProperty("SaucedemoPassword"));

        saucedemoLoginPage.loginButton.click();

        SaucedemoMainPage saucedemoMainPage = new SaucedemoMainPage();

        saucedemoMainPage.shoppingCard.click();

        driver.navigate().back();

        Select sortSelect = new Select(saucedemoMainPage.sortDropDown);
        sortSelect.selectByValue("lohi");


    }

}
