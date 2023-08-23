package homeTasks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.TestBase;



public class BlazeDemoAssignment extends TestBase {


    @Test
    public void case1InvalidDataTest(){
        driver.get(ConfigReader.getProperty("BlazedemoURL"));

        String fromCity = "San Diego";
        String toCity = "New York";

        WebElement dropDownBox = driver.findElement(By.xpath("//select[@name='fromPort']"));

        Select departureSelect = new Select(dropDownBox);

        departureSelect.selectByValue(fromCity);

        WebElement destinationDropDown = driver.findElement(By.xpath("//select[@name='toPort']"));

        Select destinationSelect = new Select(destinationDropDown);

        destinationSelect.selectByValue(toCity);

        driver.findElement(By.xpath("//input[@type='submit']")).click();

        WebElement actualText = driver.findElement(By.xpath("//h3"));

        String expectedText = "Flights from " + fromCity + " to " + toCity + ":" ;

        Assert.assertEquals(actualText.getText(), expectedText);

        WebElement choosingFlightButton = driver.findElement(By.xpath("(//input[@type='submit'])[1]"));
        choosingFlightButton.click();

        WebElement nameInput = driver.findElement(By.xpath("//input[@id='inputName']"));
        nameInput.sendKeys("David Clark");

        WebElement addressInput = driver.findElement(By.xpath("//input[@id='address']"));
        addressInput.sendKeys("123 Washington ave.");

        WebElement cityInput = driver.findElement(By.xpath("//input[@id='city']"));
        cityInput.sendKeys("Austin");

        WebElement stateInput = driver.findElement(By.xpath("//input[@id='state']"));
        stateInput.sendKeys("TX");

        WebElement zipCodeInput = driver.findElement(By.xpath("//input[@id='zipCode']"));
        zipCodeInput.sendKeys("12345");

        WebElement cardTypeDropDownBox = driver.findElement(By.xpath("//select[@id='cardType']"));
        Select selectCardType = new Select(cardTypeDropDownBox);
        selectCardType.selectByIndex(1);

        WebElement creditCardNumberInput = driver.findElement(By.xpath("//input[@id='creditCardNumber']"));
        creditCardNumberInput.sendKeys("mycreditcardnumber");

        WebElement yearInput = driver.findElement(By.xpath("//input[@id='creditCardYear']"));
        yearInput.clear();
        yearInput.sendKeys("2025");

        WebElement nameOnCardInput = driver.findElement(By.xpath("//input[@id='nameOnCard']"));
        nameOnCardInput.sendKeys("David Clark");

        WebElement purchaseFlightButton = driver.findElement(By.xpath("//input[@value='Purchase Flight']"));
        purchaseFlightButton.click();

    }
}
