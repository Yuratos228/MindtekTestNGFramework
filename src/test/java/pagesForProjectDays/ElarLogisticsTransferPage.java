package pagesForProjectDays;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class ElarLogisticsTransferPage {
    WebDriver driver;

    public ElarLogisticsTransferPage(){
        driver = Driver.getDriver();
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//*[@id=\"app\"]/aside/nav/ul[3]/li[2]/a")
    public WebElement chestIcon;

    @FindBy(className = "link-btm-menu")
    public WebElement addCompanyBtn;
}
