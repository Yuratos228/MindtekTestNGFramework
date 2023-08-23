package homeTasks;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.SaucedemoLoginPage;
import pages.SaucedemoMainPage;
import utilities.ConfigReader;
import utilities.TestBase;

public class SaucedemoTask extends TestBase {

    @Test
    public void saucedemoLoginTestCase1(){
        driver.get(ConfigReader.getProperty("SaucedemoURL"));

        SaucedemoLoginPage saucedemoLoginPage = new SaucedemoLoginPage();

        saucedemoLoginPage.usernameInput.sendKeys("standard_user");

        saucedemoLoginPage.passwordInput.sendKeys("invalid_password");

        saucedemoLoginPage.loginButton.click();

        Assert.assertTrue(saucedemoLoginPage.errorMsg.isDisplayed());

        String expectedMsg = "Epic sadface: Username and password do not match any user in this service";

        String actualMsg = saucedemoLoginPage.errorMsg.getText();

        Assert.assertEquals(actualMsg, expectedMsg);

    }

    @Test
    public void testCase2(){
        driver.get(ConfigReader.getProperty("SaucedemoURL"));

        SaucedemoLoginPage saucedemoLoginPage = new SaucedemoLoginPage();

        saucedemoLoginPage.usernameInput.sendKeys("standard_user");

        saucedemoLoginPage.passwordInput.sendKeys("secret_sauce");

        saucedemoLoginPage.loginButton.click();

        SaucedemoMainPage saucedemoMainPage = new SaucedemoMainPage();

        saucedemoMainPage.sauceLabsBackpackItem.click();

        String expectedTextOnBtn = "Remove";

        String actualTextOnBtn = saucedemoMainPage.addedSauceLabsBackpackItem.getText();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(actualTextOnBtn, expectedTextOnBtn);

        softAssert.assertTrue(saucedemoMainPage.shoppingCard.isDisplayed());

        softAssert.assertEquals(saucedemoMainPage.shoppingCard.getText(),"1");

        softAssert.assertAll();

    }

    @Test
    public void testCase3(){

        driver.get(ConfigReader.getProperty("SaucedemoURL"));

        SaucedemoLoginPage saucedemoLoginPage = new SaucedemoLoginPage();

        saucedemoLoginPage.usernameInput.sendKeys("standard_user");

        saucedemoLoginPage.passwordInput.sendKeys("secret_sauce");

        saucedemoLoginPage.loginButton.click();

        SaucedemoMainPage saucedemoMainPage = new SaucedemoMainPage();

        saucedemoMainPage.sauceLabsBackpackItem.click();

        String expectedTextOnBtn = "Remove";

        String actualTextOnBtn = saucedemoMainPage.addedSauceLabsBackpackItem.getText();

        Assert.assertEquals(actualTextOnBtn, expectedTextOnBtn);

        saucedemoMainPage.shoppingCard.click();

        Assert.assertEquals(driver.findElement(By.id("item_4_title_link")).getText(), "Sauce Labs Backpack");

        saucedemoMainPage.addedSauceLabsBackpackItem.click();

        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='removed_cart_item']")).isEnabled());

    }
}
