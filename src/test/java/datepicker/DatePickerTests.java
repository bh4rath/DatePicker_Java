package datepicker;

import base.BaseTests;
import components.DatePicker;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOError;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;

import static org.testng.Assert.*;

public class DatePickerTests extends BaseTests {

    private WebDriver driver;

    @Test
    public void pastDate () {
        var dateToSelect = LocalDate.of(2018, Month.DECEMBER,20);
        var selecteDate = datePicker.chooseDate(dateToSelect);
        Assert.assertEquals(selecteDate,dateToSelect);
    }

    @Test
    public void futureDate(){
        var dateToSelect = LocalDate.now().plusMonths(7);
        var selecteDate = datePicker.chooseDate(dateToSelect);
        Assert.assertEquals(selecteDate,dateToSelect);
    }

    @Test
    public void currentDate(){
        var dateToSelect = LocalDate.now().withDayOfMonth(24);
        var selecteDate = datePicker.chooseDate(dateToSelect);
        Assert.assertEquals(selecteDate,dateToSelect);
    }


    @Test
    public void trickyDate(){
        var dateToSelect = LocalDate.of(2020,Month.MARCH,31);
        var selecteDate = datePicker.chooseDate(dateToSelect);
        Assert.assertEquals(selecteDate,dateToSelect);
    }
}
