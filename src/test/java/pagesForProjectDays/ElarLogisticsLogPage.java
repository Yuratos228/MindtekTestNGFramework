package pagesForProjectDays;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class ElarLogisticsLogPage {
    WebDriver driver;

    public ElarLogisticsLogPage(){
        driver = Driver.getDriver();
        PageFactory.initElements(driver,this);
    }

    @FindBy(className = "input-login")
    public WebElement inputUsername;

    @FindBy(id = "id_input_pass")
    public WebElement inputPassword;

    @FindBy(className = "btn-login")
    public WebElement loginBtn;
}
