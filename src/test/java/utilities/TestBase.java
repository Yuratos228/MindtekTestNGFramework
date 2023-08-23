package utilities;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class TestBase {

   protected WebDriver driver;

    @BeforeMethod(groups = {"regression", "smoke"})
    public void setUp(){
       driver = Driver.getDriver();
//             Everything below this line is already implemented in the Driver class.
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterMethod(groups = {"regression", "smoke"})
    public void tearDown() throws InterruptedException {

        Thread.sleep(3000);
        driver.quit();
    }
}
