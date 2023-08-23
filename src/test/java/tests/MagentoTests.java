package tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.MagentoMainPage;
import pages.MagentoSignInPage;
import pages.MagentoSignupPage;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.TestBase;
;

public class MagentoTests extends TestBase {

//   DDT - Data-Driven Testing - Testing with multiple sets of data

    @DataProvider(name = "createAccountData")
    public static Object[][] testData(){
        return new Object[][]{                               // multidimensional array of Object
                {"Srivanth", "Kasireddy", "qQ123456!"},      // The test will run once for every array in the DataProvider
//                {"Mo", "King", "qQ123456!"},
//                {"MaryBeth", "Clark", "qQ123456!"}
        };
    }

    String userEmail;
    String userPassword;
    String firstName;
    String lastName;

    @Test(groups = {"regression", "smoke", "magento", "login"}, dataProvider = "createAccountData")
    public void verifyCreateAccount(String firstName, String lastName, String userPwd){
        driver.get(ConfigReader.getProperty("MagentoURL"));

        MagentoMainPage magentoMainPage = new MagentoMainPage();
        magentoMainPage.createAccountLink.click();

        MagentoSignupPage magentoSignupPage = new MagentoSignupPage();

        magentoSignupPage.firstNameInput.sendKeys(firstName);
        magentoSignupPage.lastnameInput.sendKeys(lastName);

//        Random random = new Random();
//        int num = random.nextInt(10);
//        String email = "Harsh" + num + "@gmail.com";

//        UUID uuid = UUID.randomUUID();
//        userEmail = "user" + uuid + "@gmail.com";      ->    this method is now in BrowserUtils class

        userEmail = BrowserUtils.getRandomEmail();

        System.out.println(userEmail);

        this.userPassword = userPwd;
        this.firstName = firstName;
        this.lastName = lastName;

        magentoSignupPage.emailInput.sendKeys(userEmail);
        magentoSignupPage.passwordInput.sendKeys(userPwd);
        magentoSignupPage.passwordConfirmInput.sendKeys(userPwd);
        magentoSignupPage.createAnAccountBtn.click();

        Assert.assertEquals(driver.getTitle(), "My Account");



    }

    @Test(groups = {"regression", "smoke", "magento", "login"}, dependsOnMethods = {"verifyCreateAccount"})
    public void verifySignIn(){
     driver.get(ConfigReader.getProperty("MagentoURL"));
     MagentoMainPage magentoMainPage = new MagentoMainPage();

     magentoMainPage.signInLink.click();

        MagentoSignInPage magentoSignInPage = new MagentoSignInPage();

        magentoSignInPage.emailInput.sendKeys(userEmail);
        magentoSignInPage.passwordInput.sendKeys(userPassword);
        magentoSignInPage.signInBtn.click();

        String expectedName = "Welcome, " + firstName + " " + lastName + "!";

//        WebDriverWait wait = new WebDriverWait(driver,10);
//        wait.until(ExpectedConditions.textToBePresentInElement(magentoSignInPage.welcomeMessage, expectedName));
        BrowserUtils.waitForTextToBePresentInElement(magentoSignInPage.welcomeMessage, expectedName);

        Assert.assertEquals(magentoSignInPage.welcomeMessage.getText(), expectedName);
    }

}
