package base;

import components.DatePicker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class BaseTests {
    private WebDriver driver;
    protected DatePicker datePicker;

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("file:/C:/Users/hp/Downloads/datepicker.html");
        datePicker = new DatePicker(driver);

    }

    @AfterMethod
    public void quitDriver(){
        driver.quit();
    }
}
