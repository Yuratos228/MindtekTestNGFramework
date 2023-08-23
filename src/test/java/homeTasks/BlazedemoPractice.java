package homeTasks;
;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.TestBase;



public class BlazedemoPractice extends TestBase {


    @Test
    public void verifyFindFlights(){
        driver.get(ConfigReader.getProperty("BlazedemoURL"));

        String fromCity = "San Diego";
        String toCity = "Cairo";

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


    }

    @Test
    public void verifyDestinationOfTheWeek(){
        driver.get(ConfigReader.getProperty("BlazedemoURL"));

        driver.findElement(By.xpath("//a[@href='vacation.html']")).click();

        String actualDestinationText = driver.findElement(By.xpath("//div[contains(text(), 'Destination')]")).getText();

        Assert.assertEquals(actualDestinationText, "Destination of the week: Hawaii !");
    }
}
