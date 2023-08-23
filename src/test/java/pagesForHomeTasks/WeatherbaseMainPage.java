package pagesForHomeTasks;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class WeatherbaseMainPage {

    WebDriver driver;

    public WeatherbaseMainPage(){
        driver = Driver.getDriver();
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//input[@id='query']")
    public WebElement searchInput;

    @FindBy(xpath = "//a[contains(text(), 'North America')]")
    public WebElement NorthAmerica;
}
