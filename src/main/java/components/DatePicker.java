package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

import static java.lang.String.format;

public class DatePicker {
    private WebDriver driver;

    public DatePicker(WebDriver driver) {
        this.driver = driver;
    }

    private By openCalenderButton = By.xpath("//i[@class='gj-icon']");
    private By calendar = By.xpath("//div[@role='calendar']");
    private By period = By.xpath("//div[@role='period']");
    private By leftArrow = By.xpath("//i[contains(@class,'left')]");
    private By rightArrow = By.xpath("//i[contains(@class,'right')]");
    private String date_FORMAT = "//td[@class='current-month gj-cursor-pointer']//div[text()='%d']";

    public LocalDate chooseDate(LocalDate date) {
        open();
        chooseMonth(date);
        chooseDay(date.getDayOfMonth());
        return getSelectedDate();
    }

    public void chooseDay(int dayOfMonth) {
        By Locator = By.xpath(format(date_FORMAT, dayOfMonth));
        driver.findElement(Locator).click();

    }

    public LocalDate getSelectedDate(){
        var fields = driver.findElement(calendar).getAttribute("selectedday").split("-");
        return LocalDate.of(
                Integer.parseInt(fields[0]),
                Integer.parseInt(fields[1]) + 1,
                Integer.parseInt(fields[2]));
    }

    private void chooseMonth(LocalDate date) {
        var currentPeriod = getCurrentPeriod();
        long monthsAway = ChronoUnit.MONTHS.between(currentPeriod,date.withDayOfMonth(1));
        System.out.println("Months away value is " + monthsAway);
        By arrow = monthsAway < 0 ? leftArrow : rightArrow;

        for (int i = 0; i < Math.abs(monthsAway); i++){
            driver.findElement(arrow).click();
        }
    }

    public LocalDate getCurrentPeriod() {
        var currentPeriod = driver.findElement(period).getText().split(" ");
        return LocalDate.of(
                Integer.parseInt(currentPeriod[1]),
                Month.valueOf(currentPeriod[0].toUpperCase()),
                1);
    }

    public void open()  {
        if(!isCalenderOpen()){
            driver.findElement(openCalenderButton).click();
        }
    }

    public boolean isCalenderOpen() {
        return driver.findElement(calendar).isDisplayed();
    }
}