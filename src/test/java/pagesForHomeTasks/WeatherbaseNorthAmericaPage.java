package pagesForHomeTasks;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class WeatherbaseNorthAmericaPage {

    WebDriver driver;

    public WeatherbaseNorthAmericaPage(){
        driver = Driver.getDriver();
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//div[@id='cell300left']//a[contains(text(), 'United States of America')]")
    public WebElement UnitedStatesOfAmerica;
}
