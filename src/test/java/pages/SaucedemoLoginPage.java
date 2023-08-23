package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.ConfigReader;
import utilities.Driver;

public class SaucedemoLoginPage {

    WebDriver driver;

    public SaucedemoLoginPage() {
        driver = Driver.getDriver();
        PageFactory.initElements(driver, this);

    }

    @FindBy(id = "user-name")
    public WebElement usernameInput;

    @FindBy(xpath = "//input[@id='password']")
    public  WebElement passwordInput;

    @FindBy(id = "login-button")
    public WebElement loginButton;

    @FindBy(xpath = "//h3[@data-test='error']")
    public WebElement errorMsg;

    public void login(){

        driver.get(ConfigReader.getProperty("SaucedemoURL"));

        usernameInput.sendKeys(ConfigReader.getProperty("SaucedemoUsernamePositive"));

        passwordInput.sendKeys(ConfigReader.getProperty("SaucedemoPassword"));

        loginButton.click();
    }
}
