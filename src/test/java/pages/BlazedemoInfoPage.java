package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class BlazedemoInfoPage {

    WebDriver driver;

    public BlazedemoInfoPage(){
        driver = Driver.getDriver();
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//p[contains(text(), 'Airline: United')]")
    public WebElement airlineName;

    @FindBy(xpath = "//p[contains(text(), 'Flight Number: UA954')]")
    public WebElement flightNumber;

    @FindBy(xpath = "//p[contains(text(), 'Price: 400')]")
    public WebElement price;
}
