package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pagesForProjectDays.ElarLogisticsLogPage;
import pagesForProjectDays.ElarLogisticsTransferPage;

public class TestBaseForProjectDay1 {
    protected WebDriver driver;

    @BeforeMethod(groups = {"regression", "smoke"})
    public void setUp(){
        driver = Driver.getDriver();

        driver.get(ConfigReader.getProperty("ElarLogistics"));

        ElarLogisticsLogPage elarLogisticsLogPage = new ElarLogisticsLogPage();

        elarLogisticsLogPage.inputUsername.sendKeys("student@mindtekbootcamp.com");

        elarLogisticsLogPage.inputPassword.sendKeys("ilovejava");

        elarLogisticsLogPage.loginBtn.click();

        ElarLogisticsTransferPage elarLogisticsTransferPage = new ElarLogisticsTransferPage();

        elarLogisticsTransferPage.chestIcon.click();

        elarLogisticsTransferPage.addCompanyBtn.click();

        JavascriptExecutor jse=((JavascriptExecutor) driver);
        jse.executeScript("window.scrollBy(0,200)");
    }

    @AfterMethod(groups = {"regression", "smoke"})
    public void tearDown() throws InterruptedException {

        Thread.sleep(3000);
        driver.quit();
    }
}
