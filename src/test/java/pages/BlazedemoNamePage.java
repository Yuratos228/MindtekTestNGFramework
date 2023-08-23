package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class BlazedemoNamePage {
    WebDriver driver;

    public BlazedemoNamePage(){
        driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "fromPort")
    public WebElement departureDropDown;

    @FindBy(name = "toPort")
    public WebElement destinationDropDown;

    @FindBy(xpath = "//input[@type='submit']")
    public WebElement findFlightsBtn;
}
