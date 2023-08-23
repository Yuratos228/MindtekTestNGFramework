package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.BrowserUtils;
import utilities.TestBaseForProjectDay1;

import java.io.IOException;


public class ProjectDay1 extends TestBaseForProjectDay1 {

    @Test(priority = 3)
    public void faxFieldWith12Characters(){
        String expectedCharacters = "111-111-1111";

        WebElement faxInput = driver.findElement(By.xpath("//*[@id=\"add-company\"]/div[1]/div/div[3]/div[2]/p/span/span/span[1]/input"));
        faxInput.sendKeys(expectedCharacters);

        Assert.assertEquals(expectedCharacters,faxInput.getAttribute("value"));

    }

    @Test(priority = 3)
    public void faxFieldWith11CharactersAndLess(){
        String characters = "111-111-111";
        int counter = characters.length();

        WebElement faxInput = driver.findElement(By.xpath("//*[@id=\"add-company\"]/div[1]/div/div[3]/div[2]/p/span/span/span[1]/input"));
        faxInput.sendKeys(characters);
        Assert.assertEquals("Min length is 12 characters, currently it is " + counter, driver.findElement(By.xpath("//*[@id=\"add-company\"]/div[1]/div/div[3]/div[2]/p/span[2]")).getText());
    }


    @Test
    public void testPhoneFieldWith12Chars() {
        driver.findElement(By.xpath("//*[@id=\"add-company\"]/div[1]/div/div[3]/div[1]/p[1]/span/span/span[1]/input[1]")).sendKeys("773-773-7737");
        String actualResult= driver.findElement(By.xpath("//*[@id=\"add-company\"]/div[1]/div/div[3]/div[1]/p[1]/span/span/span[1]/input[1]")).getAttribute("value");
        String expectedResult="773-773-7737";

        System.out.println(actualResult);

        Assert.assertEquals(actualResult,expectedResult);

    }

    @Test
    public void testPhoneFieldWith11Chars() {

        String number = "773-773-773";
        System.out.println(number.length());
        driver.findElement(By.xpath("//*[@id=\"add-company\"]/div[1]/div/div[3]/div[1]/p[1]/span/span/span[1]/input[1]")).sendKeys(number);

        Assert.assertEquals("Min length is 12 characters, currently it is " + number.length() + ".", driver.findElement(By.className("input-errors")).getText());
    }

    @Test
    public void testPhoneFieldWith13Chars() {
        String number = "773-773-73733";
        WebElement phoneField = driver.findElement(By.xpath("//*[@id=\"add-company\"]/div[1]/div/div[3]/div[1]/p[1]/span/span/span[1]/input[1]"));
        phoneField.sendKeys(number);

        String actualResult=driver.findElement(By.xpath("//*[@id=\"add-company\"]/div[1]/div/div[3]/div[1]/p[1]/span/span/span[1]/input[1]")).getAttribute("value");

        Assert.assertTrue(actualResult.length()==12);

    }

    @Test
    public void testPhoneFieldWithLetters(){
        String input="abc-def-ghi";
        WebElement phoneField = driver.findElement(By.xpath("//*[@id=\"add-company\"]/div[1]/div/div[3]/div[1]/p[1]/span/span/span[1]/input[1]"));
        phoneField.sendKeys(input);

        String actualResult=driver.findElement(By.xpath("//*[@id=\"add-company\"]/div[1]/div/div[3]/div[1]/p[1]/span/span/span[1]/input[1]")).getAttribute("value");
        Assert.assertTrue(actualResult.length()==0);

    }

    @Test
    public void testPhoneFieldWithSpecialChars() {
        String input = "@#$-^&*-_><(";
        WebElement phoneField = driver.findElement(By.xpath("//*[@id=\"add-company\"]/div[1]/div/div[3]/div[1]/p[1]/span/span/span[1]/input[1]"));
        phoneField.sendKeys(input);

        String actualResult = driver.findElement(By.xpath("//*[@id=\"add-company\"]/div[1]/div/div[3]/div[1]/p[1]/span/span/span[1]/input[1]")).getAttribute("value");
        Assert.assertTrue(actualResult.length() == 0);
    }

    @Test(priority = 1)
    public void testPhoneNumberExtension9Chars(){
        WebElement phoneField=driver.findElement(By.xpath("//*[@id=\"add-company\"]/div[1]/div/div[3]/div[1]/p[1]/span/span/span[1]/input[1]"));
        phoneField.sendKeys("773-773-7737");

        WebElement extensionField= driver.findElement(By.xpath("//*[@id=\"add-company\"]/div[1]/div/div[3]/div[1]/p[1]/span/span/span[1]/input[2]"));
        String extensionData="123456789";
        extensionField.sendKeys(extensionData);

        String actualResult= extensionField.getAttribute("value");

        Assert.assertEquals(extensionData,actualResult);

    }
    @Test(priority = 1)
    public void testPhoneNumberExtension10Chars(){
        WebElement phoneField=driver.findElement(By.xpath("//*[@id=\"add-company\"]/div[1]/div/div[3]/div[1]/p[1]/span/span/span[1]/input[1]"));
        phoneField.sendKeys("773-773-7737");

        WebElement extensionField= driver.findElement(By.xpath("//*[@id=\"add-company\"]/div[1]/div/div[3]/div[1]/p[1]/span/span/span[1]/input[2]"));
        String extensionData="1234567891";
        extensionField.sendKeys(extensionData);

        String actualResult= extensionField.getAttribute("value");

        Assert.assertEquals(extensionData,actualResult);

    }
    @Test(priority = 1)
    public void testPhoneNumberExtension11Chars(){
        WebElement phoneField=driver.findElement(By.xpath("//*[@id=\"add-company\"]/div[1]/div/div[3]/div[1]/p[1]/span/span/span[1]/input[1]"));
        phoneField.sendKeys("773-773-7737");

        WebElement extensionField= driver.findElement(By.xpath("//*[@id=\"add-company\"]/div[1]/div/div[3]/div[1]/p[1]/span/span/span[1]/input[2]"));
        String extensionData="12345678912";
        extensionField.sendKeys(extensionData);

        String actualResult= extensionField.getAttribute("value");

        Assert.assertTrue(actualResult.length()==10);

    }
    @Test(priority = 1)
    public void testPhoneNumberExtensionWithLetters(){
        WebElement phoneField=driver.findElement(By.xpath("//*[@id=\"add-company\"]/div[1]/div/div[3]/div[1]/p[1]/span/span/span[1]/input[1]"));
        phoneField.sendKeys("773-773-7737");

        WebElement extensionField= driver.findElement(By.xpath("//*[@id=\"add-company\"]/div[1]/div/div[3]/div[1]/p[1]/span/span/span[1]/input[2]"));
        String extensionData="abcdefgh";
        extensionField.sendKeys(extensionData);

        String actual= driver.findElement(By.xpath("//span[contains(text(),'Enter only digits.')]")).getText();
        String expected= "Enter only digits.";

        Assert.assertEquals(actual,expected);

    }
    @Test(priority = 1)
    public void testPhoneNumberExtensionWithSpecialChars(){
        WebElement phoneField=driver.findElement(By.xpath("//*[@id=\"add-company\"]/div[1]/div/div[3]/div[1]/p[1]/span/span/span[1]/input[1]"));
        phoneField.sendKeys("773-773-7737");

        WebElement extensionField= driver.findElement(By.xpath("//*[@id=\"add-company\"]/div[1]/div/div[3]/div[1]/p[1]/span/span/span[1]/input[2]"));
        String extensionData="!@#><_)(";
        extensionField.sendKeys(extensionData);

        String actual= driver.findElement(By.xpath("//span[contains(text(),'Enter only digits.')]")).getText();
        String expected= "Enter only digits.";

        Assert.assertEquals(actual,expected);

    }

    @Test(priority = 2)
    public void testContactNameField35Chars(){
        driver.findElement(By.xpath("//*[@id=\"add-company\"]/div[1]/div/div[3]/div[1]/p[1]/span/span/span[1]/input[1]")).sendKeys("773-773-7737");

        WebElement extensionField= driver.findElement(By.xpath("//*[@id=\"add-company\"]/div[1]/div/div[3]/div[1]/p[1]/span/span/span[1]/input[2]"));
        String extensionData="1234567891";
        extensionField.sendKeys(extensionData);

        WebElement contactNameInput = driver.findElement(By.xpath("//*[@id=\"add-company\"]/div[1]/div/div[3]/div[1]/p[2]/span[1]/span[2]/input"));
        contactNameInput.sendKeys("Udvbadveriojdkvjarebdrovjnreofjv 2-");

        String expectedCharacters = "Udvbadveriojdkvjarebdrovjnreofjv 2-";

        String actualCharacters = contactNameInput.getAttribute("value");

        Assert.assertEquals(actualCharacters,expectedCharacters);


    }

    @Test(priority = 2)
    public void testContactNameField36Chars(){
        driver.findElement(By.xpath("//*[@id=\"add-company\"]/div[1]/div/div[3]/div[1]/p[1]/span/span/span[1]/input[1]")).sendKeys("773-773-7737");


        WebElement extensionField= driver.findElement(By.xpath("//*[@id=\"add-company\"]/div[1]/div/div[3]/div[1]/p[1]/span/span/span[1]/input[2]"));
        String extensionData="1234567891";
        extensionField.sendKeys(extensionData);

        WebElement contactNameInput = driver.findElement(By.xpath("//*[@id=\"add-company\"]/div[1]/div/div[3]/div[1]/p[2]/span[1]/span[2]/input"));
        contactNameInput.sendKeys("Udvbadveriojdkvjarebdrovjnreofjv 2--");

        String actualCharacters = contactNameInput.getAttribute("value");

        Assert.assertTrue(actualCharacters.length()==35);

    }

    @Test(priority = 2)
    public void testContactNameField35CharsWithInvalidSpecialChar(){
        driver.findElement(By.xpath("//*[@id=\"add-company\"]/div[1]/div/div[3]/div[1]/p[1]/span/span/span[1]/input[1]")).sendKeys("773-773-7737");

        WebElement extensionField= driver.findElement(By.xpath("//*[@id=\"add-company\"]/div[1]/div/div[3]/div[1]/p[1]/span/span/span[1]/input[2]"));
        String extensionData="1234567891";
        extensionField.sendKeys(extensionData);

        WebElement contactNameInput = driver.findElement(By.xpath("//*[@id=\"add-company\"]/div[1]/div/div[3]/div[1]/p[2]/span[1]/span[2]/input"));
        contactNameInput.sendKeys("Udvbadveriojdkvjarebdrovjnreofjv 2!");

        WebElement actualMessage = driver.findElement(By.xpath("//span[contains(text(),'Invalid input')]"));

       Assert.assertTrue(actualMessage.isDisplayed());

    }

    @Test(priority = 4)
    public void testStreetField35Chars(){

        WebElement streetInput = driver.findElement(By.id("id_address"));
        streetInput.sendKeys("2567 West Dundee Road West Dundeeee");

        Assert.assertTrue(streetInput.getAttribute("value").length()==35);

        String actualCharacters = streetInput.getAttribute("value");

        String expectedCharacters = "2567 West Dundee Road West Dundeeee";

        Assert.assertEquals(actualCharacters,expectedCharacters);
    }

    @Test(priority = 4)
    public void testStreetField36Chars(){

        WebElement streetInput = driver.findElement(By.id("id_address"));
        String textWith36Chars = "2567 West Dundee Road West Dundeeeee";

        Assert.assertTrue(textWith36Chars.length()==36);

        streetInput.sendKeys(textWith36Chars);

        String actualCharacters = streetInput.getAttribute("value");

        Assert.assertTrue(actualCharacters.length()==35);
    }

    @Test(priority = 4)
    public void testStreetFieldWith0Chars(){

        WebElement streetInput = driver.findElement(By.id("id_address"));
        String textWith0Chars = "";

        Assert.assertTrue(textWith0Chars.isEmpty());

        streetInput.sendKeys(textWith0Chars);

        WebElement addCompanyBtn = driver.findElement(By.xpath("//button[@class='confirm-save']"));
        addCompanyBtn.click();

        WebElement ErrorMessage = driver.findElement(By.xpath("//*[@id=\"add-company\"]/div[1]/div/div[3]/div[3]/p[1]/span[contains(text(),'This field is required.')]"));

        Assert.assertTrue(ErrorMessage.isDisplayed());

    }

    @Test(priority = 5)
    public void testAprSuiteCompanyFieldWith36Chars(){

        WebElement aprSuiteCompanyField = driver.findElement(By.id("id_apt_suite_company_co"));

        String textWith36Chars = "Apt.35 suite3 Excellent Team Worksss";

        Assert.assertTrue(textWith36Chars.length()==36);

        aprSuiteCompanyField.sendKeys(textWith36Chars);

        Assert.assertTrue(aprSuiteCompanyField.getAttribute("value").length()==35);

    }

    @Test(priority = 5)
    public void testAprSuiteCompanyFieldWithInvalidChar(){

        WebElement aprSuiteCompanyField = driver.findElement(By.id("id_apt_suite_company_co"));
        //invalid special char is underscore:
        String textWith36Chars = "Apt.35,suite3/Excellent Team_%";

        aprSuiteCompanyField.sendKeys(textWith36Chars);

        WebElement actualMessage = driver.findElement(By.xpath("//span[contains(text(),'Invalid input')]"));

        Assert.assertTrue(actualMessage.isDisplayed());

    }

    @Test(priority = 6)
    public void testCityFieldWith28Chars(){

        WebElement cityInput = driver.findElement(By.id("id_city"));
        cityInput.sendKeys("ChicagoChicagoChicagoChicago");

        String actualInputMessage = cityInput.getAttribute("value");
        String expectedInputMessage = "ChicagoChicagoChicagoChicago";

        Assert.assertTrue(cityInput.getAttribute("value").length()==28);
        Assert.assertEquals(actualInputMessage, expectedInputMessage);

    }

    @Test(priority = 7)
    public void testStatesDropDownBoxNames() throws IOException {

        WebElement statesDropDown = driver.findElement(By.id("id_state"));
        Select select = new Select(statesDropDown);

        JavascriptExecutor jse = ((JavascriptExecutor) driver);

        jse.executeScript("window.scrollBy(0,150)");

        statesDropDown.click();

        BrowserUtils.takeScreenshot("NamesOfStates");

        for (WebElement el: select.getOptions()){

            System.out.println(el.getText());
        }

        Assert.assertTrue(select.getOptions().size()==49);

    }

    @Test(priority = 7)
    public void testStateDropDownBoxChooseValue(){

        WebElement statesDropDown = driver.findElement(By.id("id_state"));
        Select select = new Select(statesDropDown);

        select.selectByValue("IL");

        String expectedDropDownValue = "IL";
        String actualDropDownValue = statesDropDown.getAttribute("value");

        Assert.assertEquals(actualDropDownValue, expectedDropDownValue);
    }

    @Test(priority = 8)
    public void testZipCodeFieldWith4Figures(){
        String textToZipCodeInput = "1234";

        Assert.assertTrue(textToZipCodeInput.length()==4);

        WebElement zipCodeField = driver.findElement(By.id("id_zip_code"));
        zipCodeField.sendKeys(textToZipCodeInput);

        int amountOfNums = zipCodeField.getAttribute("value").length();

        Assert.assertTrue(amountOfNums<=4);

        String expectedErrorMsg = "Min length is 5 characters, currently it is " + amountOfNums;
        String actualErrorMsg = driver.findElement(By.xpath("//span[@class='input-errors']")).getText();

        Assert.assertEquals(actualErrorMsg, expectedErrorMsg);
    }

    @Test(priority = 8)
    public void testZipCodeFieldWith5Figures(){

        String textToZipCodeInput = "12345";
        Assert.assertTrue(textToZipCodeInput.length()==5);

        WebElement zipCodeField = driver.findElement(By.id("id_zip_code"));
        zipCodeField.sendKeys(textToZipCodeInput);

        int amountOfNums = zipCodeField.getAttribute("value").length();

        Assert.assertTrue(amountOfNums==5);

        String expectedInputValue = textToZipCodeInput;
        String actualInputValue = zipCodeField.getAttribute("value");

        Assert.assertEquals(actualInputValue, expectedInputValue);
    }

    @Test(priority = 8)
    public void testZipCodeFieldWith6Figures(){

        String textToZipCodeInput = "123456";

        Assert.assertTrue(textToZipCodeInput.length()==6);

        WebElement zipCodeField = driver.findElement(By.id("id_zip_code"));
        zipCodeField.sendKeys(textToZipCodeInput);

        int amountOfNums = zipCodeField.getAttribute("value").length();

        Assert.assertTrue(amountOfNums==5);

        String expectedInputValue = "12345";
        String actualInputValue = zipCodeField.getAttribute("value");

        Assert.assertEquals(actualInputValue, expectedInputValue);
    }

    @Test(priority = 8)
    public void testZipCodeFieldWith4FiguresAnd1Letter(){

        String textToZipCodeInput = "1234a";

        Assert.assertTrue(textToZipCodeInput.length()==5);

        WebElement zipCodeField = driver.findElement(By.id("id_zip_code"));
        zipCodeField.sendKeys(textToZipCodeInput);

        int amountOfNums = zipCodeField.getAttribute("value").length();

        Assert.assertTrue(amountOfNums==5);

        String expectedInputValue = "Enter only digits.";
        String actualInputValue = driver.findElement(By.xpath("//*[@id=\"add-company\"]/div[1]/div/div[3]/div[3]/p[5]/span[3]")).getText();

        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"add-company\"]/div[1]/div/div[3]/div[3]/p[5]/span[3]")).isDisplayed());
        Assert.assertEquals(actualInputValue, expectedInputValue);
    }



}
