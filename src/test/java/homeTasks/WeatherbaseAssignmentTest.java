package homeTasks;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pagesForHomeTasks.WeatherbaseMainPage;
import pagesForHomeTasks.WeatherbaseNorthAmericaPage;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.TestBase;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WeatherbaseAssignmentTest extends TestBase {

    String textToSearchInput;


    @Test
    public void testCase1(){

        driver.get(ConfigReader.getProperty("WeatherbaseURL"));

        WeatherbaseMainPage weatherbaseMainPage = new WeatherbaseMainPage();

        textToSearchInput = "60656";

        weatherbaseMainPage.searchInput.sendKeys(textToSearchInput);

        Assert.assertEquals(weatherbaseMainPage.searchInput.getAttribute("value"), textToSearchInput);

        weatherbaseMainPage.searchInput.sendKeys(Keys.ENTER);

        String expectedResultOnPage = "Chicago, Illinois";
        String actualResultOnPage = driver.findElement(By.xpath("//div[@id='cell320left']//a[contains(text(), 'Chicago, Illinois')]")).getText();

        Assert.assertEquals(actualResultOnPage, expectedResultOnPage);
    }


    @Test
    public void testCase2(){

        driver.get(ConfigReader.getProperty("WeatherbaseURL"));

        WeatherbaseMainPage weatherbaseMainPage = new WeatherbaseMainPage();

        textToSearchInput = "1234";

        BrowserUtils.waitForElementToBeClickable(weatherbaseMainPage.searchInput);

        weatherbaseMainPage.searchInput.sendKeys(textToSearchInput);

        weatherbaseMainPage.searchInput.sendKeys(Keys.ENTER);

        BrowserUtils.waitForTextToBePresentInElement(driver.findElement(By.xpath("//div[@id='left-content']//p[contains(text(), 'We')]")),
                "We're sorry. Your search for 1234 returned no results. Please try the following:");

        String expectedResultOnPage = "We're sorry. Your search for 1234 returned no results. Please try the following:";
        String actualResultOnPage = driver.findElement(By.xpath("//div[@id='left-content']//p[contains(text(), 'We')]")).getText();

        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='left-content']//p[contains(text(), 'We')]")).isDisplayed());
        Assert.assertEquals(actualResultOnPage, expectedResultOnPage);
    }


    @Test
    public void testCase3() {

        driver.get(ConfigReader.getProperty("WeatherbaseURL"));

        WeatherbaseMainPage weatherbaseMainPage = new WeatherbaseMainPage();

        BrowserUtils.waitForElementToBeClickable(weatherbaseMainPage.NorthAmerica);

        weatherbaseMainPage.NorthAmerica.click();

        WeatherbaseNorthAmericaPage weatherbaseNorthAmericaPage = new WeatherbaseNorthAmericaPage();

        BrowserUtils.waitForElementToBeClickable(weatherbaseNorthAmericaPage.UnitedStatesOfAmerica);

        weatherbaseNorthAmericaPage.UnitedStatesOfAmerica.click();

        driver.navigate().to("https://www.infoplease.com/us/states");

        List<WebElement> all50States = driver.findElements(By.xpath("//div[@class='stateList']/ul/li"));

        /**
         *  I created Set to be sure that there is all 50 states and no duplicates there
         *  and after assertion I will use List of Array to use indexes for webpage of Weatherbase
         */
        List<String> all50StatesInArr = new ArrayList<>();

        Set<String> all50StatesInSet = new HashSet<>();

        for (int i = 0; i < all50States.size(); i++) {
            all50StatesInArr.add(all50States.get(i).getText());
            all50StatesInSet.add(all50States.get(i).getText());
        }
        Assert.assertTrue(all50StatesInArr.size() == 50);
        System.out.println(all50StatesInArr);

        Assert.assertTrue(all50StatesInSet.size() == 50);


        driver.navigate().back();

        List<WebElement> statesAndMoreOnWeatherbaseWebPage = driver.findElements(By.xpath("//ul/li"));

        for (int i = 0; i < all50StatesInArr.size(); i++) {

            for (int j = 0; j < statesAndMoreOnWeatherbaseWebPage.size(); j++) {

                if (all50StatesInArr.get(i).equalsIgnoreCase(statesAndMoreOnWeatherbaseWebPage.get(j).getText())) {
                    break;
                } else if (j == statesAndMoreOnWeatherbaseWebPage.size() - 1) {
                    Assert.assertTrue(false, "Some state is missing on weatherbase page!");
                }

            }
        }

        System.out.println("============================================");

        System.out.println("All states on weatherbase page are checked!!!");
    }


    @Test
    public void testCase4(){

        driver.get(ConfigReader.getProperty("WeatherbaseURL"));

        WeatherbaseMainPage weatherbaseMainPage = new WeatherbaseMainPage();

        BrowserUtils.waitForElementToBeClickable(weatherbaseMainPage.NorthAmerica);

        weatherbaseMainPage.NorthAmerica.click();

        WeatherbaseNorthAmericaPage weatherbaseNorthAmericaPage = new WeatherbaseNorthAmericaPage();

        BrowserUtils.waitForElementToBeClickable(weatherbaseNorthAmericaPage.UnitedStatesOfAmerica);

        weatherbaseNorthAmericaPage.UnitedStatesOfAmerica.click();

        WebElement californiaField = driver.findElement(By.xpath("//ul/li/a[contains(text(), 'California')]"));
        californiaField.click();

        WebElement sanJoseField = driver.findElement(By.xpath("//ul/li/a[contains(text(), 'San Jose')]"));
        sanJoseField.click();

        WebElement celsiusBtn = driver.findElement(By.xpath("//img[@name='fc']"));
        celsiusBtn.click();

        JavascriptExecutor jse = ((JavascriptExecutor) driver);
        jse.executeScript("window.scrollBy(0,200)");

        WebElement displayedCelsius = driver.findElement(By.xpath("(//tbody/tr[@bgcolor='white']/td[@class='dataunit' and contains(text(), 'C')])[1]"));

        String expectedRes = "C";
        String actualRes = displayedCelsius.getText();

        Assert.assertTrue(displayedCelsius.isDisplayed());
        Assert.assertEquals(actualRes, expectedRes);

    }


    @Test
    public void testCase5(){

        driver.get(ConfigReader.getProperty("WeatherbaseURL"));

        WeatherbaseMainPage weatherbaseMainPage = new WeatherbaseMainPage();

        BrowserUtils.waitForElementToBeClickable(weatherbaseMainPage.NorthAmerica);

        weatherbaseMainPage.NorthAmerica.click();

        WeatherbaseNorthAmericaPage weatherbaseNorthAmericaPage = new WeatherbaseNorthAmericaPage();

        BrowserUtils.waitForElementToBeClickable(weatherbaseNorthAmericaPage.UnitedStatesOfAmerica);

        weatherbaseNorthAmericaPage.UnitedStatesOfAmerica.click();

        WebElement californiaField = driver.findElement(By.xpath("//ul/li/a[contains(text(), 'California')]"));
        californiaField.click();

        WebElement sanJoseField = driver.findElement(By.xpath("//ul/li/a[contains(text(), 'San Jose')]"));
        sanJoseField.click();

        WebElement temperatureInF = driver.findElement(By.xpath("//td[normalize-space()='60.6']"));

        Assert.assertEquals(temperatureInF.getText(), "60.6");

        double fahrenheitNum = Double.parseDouble(temperatureInF.getText());


        double celsius = (fahrenheitNum - 32) * 5 / 9;
        int check = (int) celsius;
        double forCheck = celsius - check;
        double expectedCelsiusDegrees = 0;
        if (forCheck >= 0.5) {
            expectedCelsiusDegrees = Math.round((check + forCheck) * 10.0) / 10.0;
        }

        WebElement celsiusBtn = driver.findElement(By.xpath("//img[@name='fc']"));
        celsiusBtn.click();

        JavascriptExecutor jse = ((JavascriptExecutor) driver);
        jse.executeScript("window.scrollBy(0,200)");

        String actualCelsiusDegreesStr = driver.findElement(By.xpath("//td[normalize-space()='15.9']")).getText();
        double actualCelsiusDegrees = Double.parseDouble(actualCelsiusDegreesStr);

        Assert.assertTrue(expectedCelsiusDegrees==actualCelsiusDegrees);

    }

}

