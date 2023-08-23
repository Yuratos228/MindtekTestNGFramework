package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.BlazedemoFlightsPage;
import pages.BlazedemoInfoPage;
import pages.BlazedemoNamePage;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.TestBase;

import java.util.concurrent.TimeUnit;

public class BlazeDemoTests extends TestBase {
    String fromCity = "San Diego";
    String toCity = "Cairo";

//    WebDriver driver;
//
//    @BeforeMethod
//    public void setUp(){
//
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//
//        driver.manage().window().maximize();
//
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//    }
//
//    @AfterMethod
//    public void tearDown() throws InterruptedException {
//
//        Thread.sleep(2000);
//        driver.quit();
//    }

    @Test(groups = {"regression", "blazedemo"})
    public void verifyFindFlights(){
//      driver.get("https://blazedemo.com/");

        driver.get(ConfigReader.getProperty("BlazedemoURL"));

        WebElement departureDropDown = driver.findElement(By.xpath("//select[@name='fromPort']"));

        BrowserUtils.selectDropDownByValue(departureDropDown, fromCity);

//        Select departureSelect = new Select(departureDropDown);
//
//        departureSelect.selectByValue(fromCity);

        WebElement destinationDropDown = driver.findElement(By.xpath("//select[@name='toPort']"));

        BrowserUtils.selectDropDownByValue(destinationDropDown, toCity);

//        Select destinationSelect = new Select(destinationDropDown);
//
//        destinationSelect.selectByValue(toCity);

        driver.findElement(By.xpath("//input[@type='submit']")).click();

        WebElement actualText = driver.findElement(By.xpath("//h3"));

        String expectedText = "Flights from " + fromCity + " to " + toCity + ":" ;

        Assert.assertEquals(actualText.getText(), expectedText);


    }

    @Test(groups = {"regression", "blazedemo", "smoke"})
    public void verifyDestinationOfTheWeek(){
        driver.get(ConfigReader.getProperty("BlazedemoURL"));

        driver.findElement(By.xpath("//a[@href='vacation.html']")).click();

        String actualDestinationText = driver.findElement(By.xpath("//div[contains(text(), 'Destination')]")).getText();

        Assert.assertEquals(actualDestinationText, "Destination of the week: Hawaii !");
    }

    @Test(groups = {"regression", "blazedemo"})
    public void verifyFlightInfo(){
        driver.get(ConfigReader.getProperty("BlazedemoURL"));
        BlazedemoNamePage blazedemoNamePage = new BlazedemoNamePage();

//        Select departureSelect = new Select(blazedemoNamePage.departureDropDown);
//        departureSelect.selectByValue(fromCity);
//
//        Select destinationSelect = new Select(blazedemoNamePage.destinationDropDown);
//        destinationSelect.selectByValue(toCity);
        BrowserUtils.selectDropDownByValue(blazedemoNamePage.departureDropDown, fromCity);
        BrowserUtils.selectDropDownByValue(blazedemoNamePage.destinationDropDown, toCity);
        blazedemoNamePage.findFlightsBtn.click();

        blazedemoNamePage.findFlightsBtn.click();

        BlazedemoFlightsPage blazedemoFlightsPage = new BlazedemoFlightsPage();

        String flightNumberStr = "Flight Number: " + blazedemoFlightsPage.flightNumber.getText();
        String airlineNameStr = "Airline: " + blazedemoFlightsPage.airlineName.getText();
        String priceStr = "Price: " + blazedemoFlightsPage.price.getText().substring(1);

        blazedemoFlightsPage.chooseFlightBtn.click();

        SoftAssert softAssert = new SoftAssert();


        BlazedemoInfoPage blazedemoInfoPage = new BlazedemoInfoPage();
        softAssert.assertEquals(blazedemoInfoPage.flightNumber.getText(), flightNumberStr);
        softAssert.assertEquals(blazedemoInfoPage.airlineName.getText(), airlineNameStr);
        softAssert.assertEquals(blazedemoInfoPage.price.getText(), priceStr);

        softAssert.assertAll();

        


    }
}
