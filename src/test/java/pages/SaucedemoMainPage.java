package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class SaucedemoMainPage {
    WebDriver driver;

    public SaucedemoMainPage(){
        driver = Driver.getDriver();
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//select[@class='product_sort_container']")
    public WebElement sortDropDown;

    @FindBy(xpath = "//a[@class='shopping_cart_link']")
    public WebElement shoppingCard;

    @FindBy(xpath = "//*[@class='inventory_item_price']")
    public List<WebElement> itemPrices;

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    public WebElement sauceLabsBackpackItem;

    @FindBy(id = "remove-sauce-labs-backpack")
    public WebElement addedSauceLabsBackpackItem;

    @FindBy(xpath = "//span[@class='shopping_cart_badge']")
    public WebElement shoppingCartBadge;
}
