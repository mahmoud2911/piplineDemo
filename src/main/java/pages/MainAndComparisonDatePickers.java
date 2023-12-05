package pages;

import com.shaft.driver.SHAFT.GUI.WebDriver;
import common.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.JsonReader;
import utils.Wait;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MainAndComparisonDatePickers extends PageBase {
    final private By mainDateBackCalenderCursor = By.xpath("(//span[text()='<'])[1]");
    final private By mainDateForwardCalenderCursor = By.xpath("(//span[text()='>'])[1]");
    final private By comparisonDateBackCalenderCursor = By.xpath("(//span[text()='<'])[2]");
    final private By comparisonDateForwardCalenderCursor = By.xpath("(//span[text()='>'])[2]");
    final private By monthYearMainDatePickerText = By.xpath("(//span[@class='px-2 cursor-pointer']/following-sibling::span)[1]");
    final private By monthYearComparisonDatePickerText = By.xpath("(//span[@class='px-2 cursor-pointer']/following-sibling::span)[3]");
    final private By mainDatePicker = By.id("datepicker.trigger");
    final private By comparisonDatePicker = By.xpath("(//button[@id='datepicker.trigger'])[2]");
    final private By mainDateText = By.xpath("(//div[contains(@class,'flex items-center')]//span)[1]");
    final private By comparisonDateText = By.xpath("(//div[contains(@class,'flex items-center')]//span)[3]");
    final private By mainDatePickerCalenderViewed = By.xpath("(//div[contains(@class,'cal p-3')])[1]");
    final private By comparisonDatePickerCalenderViewed = By.xpath("(//div[contains(@class,'cal p-3')])[2]");
    private By datePicker, monthYearText, forwardCursor, backCursor, targetLocator, calenderViewed;

    public MainAndComparisonDatePickers(WebDriver driver) {
        super(driver);
    }

    Wait wait = new Wait(driver);

    public MainAndComparisonDatePickers selectDay(String date, Boolean isComparisonDatePicker) {
        checkMainComparisonPicker(isComparisonDatePicker);
        clickOnElement(datePicker);
        assertDatePickerIsViewed(calenderViewed);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        LocalDate desiredDate = LocalDate.parse(date, dateFormatter);
        DateTimeFormatter monthYearFormatter = DateTimeFormatter.ofPattern("MMM yyyy");
        String desiredMonthYear = desiredDate.format(monthYearFormatter);
        traverseAndSelectTargetDayWeek(desiredMonthYear, String.valueOf(desiredDate.getDayOfMonth()), isComparisonDatePicker);
        return this;
    }

    public MainAndComparisonDatePickers selectTargetWeek(String weekDateRange, Boolean isComparisonDatePicker) {
        String[] dates = weekDateRange.split(" - ");
        LocalDate startDate = LocalDate.parse(dates[0]);
        LocalDate endDate = LocalDate.parse(dates[1]);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMM", Locale.ENGLISH);
        String formattedStartDate = startDate.format(dateFormatter);
        String formattedEndDate = endDate.format(dateFormatter);
        String targetWeekDateRange = formattedStartDate + " - " + formattedEndDate;
        checkMainComparisonPicker(isComparisonDatePicker);
        clickOnElement(datePicker);
        assertDatePickerIsViewed(calenderViewed);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM yyyy", Locale.ENGLISH);
        String desiredMonthYear = startDate.format(formatter);
        traverseAndSelectTargetDayWeek(desiredMonthYear, targetWeekDateRange, isComparisonDatePicker);
        return this;
    }

    public MainAndComparisonDatePickers selectTargetMonth(String monthDateRange, Boolean isComparisonDatePicker) {
        checkMainComparisonPicker(isComparisonDatePicker);
        String[] dates = monthDateRange.split(" - ");
        LocalDate startDate = LocalDate.parse(dates[0]);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM", Locale.ENGLISH);
        String formattedStartDate = startDate.format(dateFormatter);
        String targetMonth = formattedStartDate;
        clickOnElement(datePicker);
        assertDatePickerIsViewed(calenderViewed);
        traverseAndSelectTargetMonth(startDate, targetMonth, isComparisonDatePicker);
        return this;
    }

    /**
     * check whether interacting with main or comparison date picker
     * identify locator for "datePicker" "monthYearText" "forwardCursor" "backCursor" based on:
     * whether we interact with main or comparison date picker
     *
     * @param isComparisonDatePicker flag to check if it's comparison date picker or not
     */
    private void checkMainComparisonPicker(Boolean isComparisonDatePicker) {
        if (isComparisonDatePicker) {
            datePicker = comparisonDatePicker;
            monthYearText = monthYearComparisonDatePickerText;
            forwardCursor = comparisonDateForwardCalenderCursor;
            backCursor = comparisonDateBackCalenderCursor;
            calenderViewed = comparisonDatePickerCalenderViewed;
        } else {
            datePicker = mainDatePicker;
            monthYearText = monthYearMainDatePickerText;
            forwardCursor = mainDateForwardCalenderCursor;
            backCursor = mainDateBackCalenderCursor;
            calenderViewed = mainDatePickerCalenderViewed;
        }
    }

    /**
     * traverse throw the viewed calender back and forward till match MMM yyyy
     * select target day
     *
     * @param desiredMonthYear       month of the passed date
     * @param desiredDate            date to be clicked
     * @param isComparisonDatePicker flag to identify whether we interacting with Comparison date picker or not
     */
    private void traverseAndSelectTargetDayWeek(String desiredMonthYear, String desiredDate, Boolean isComparisonDatePicker) {
        YearMonth desiredMonth = YearMonth.parse(desiredMonthYear, DateTimeFormatter.ofPattern("MMM yyyy"));
        YearMonth currentMonth = YearMonth.now();
        while (true) {
            String currentMonthText = getElementText(monthYearText);
            YearMonth currentDisplayedMonth = YearMonth.parse(currentMonthText, DateTimeFormatter.ofPattern("MMM yyyy"));
            if (currentDisplayedMonth.equals(desiredMonth)) {
                setTargetLocatorAndClick(desiredDate, isComparisonDatePicker);
                break;
            } else {
                if (desiredMonth.isAfter(currentMonth)) {
                    clickOnElement(forwardCursor);
                } else {
                    clickOnElement(backCursor);
                }
            }
        }

    }

    /**
     * traverse throw the viewed calender back and forward till match yyyy
     * select target month
     *
     * @param targetMonth target month date range
     * @param startDate   the first part of the passed date range
     */
    private void traverseAndSelectTargetMonth(LocalDate startDate, String targetMonth, Boolean isComparisonDatePicker) {
        Year currentYear = Year.now();
        Year targetYear = Year.from(startDate);
        while (true) {
            String currentMonthText = getElementText(monthYearText);
            Year currentDisplayedYear = Year.parse(currentMonthText, DateTimeFormatter.ofPattern("yyyy"));
            if (currentDisplayedYear.equals(targetYear)) {
                setTargetLocatorAndClick(targetMonth, isComparisonDatePicker);
                break;
            } else {
                if (targetYear.isAfter(currentYear)) {
                    clickOnElement(forwardCursor);
                } else {
                    clickOnElement(backCursor);
                }
            }
        }
    }

    /**
     * function to set target day, week or month locator dynamically based on whether we are picking from main or comparison date picker
     *
     * @param target                 target day, week or month
     * @param isComparisonDatePicker flag to check whether we pick from comparison date picker or not
     */
    private void setTargetLocatorAndClick(String target, Boolean isComparisonDatePicker) {
        targetLocator = By.xpath("//td[text()='" + target + "']");
        List<WebElement> targetDayWeekElements = driver.getDriver().findElements(targetLocator);
        if (isComparisonDatePicker) {
            if (targetDayWeekElements.size() > 1) {
                targetLocator = By.xpath("(//td[text()='" + target + "'])[2]");
            }
        }
        clickOnElement(targetLocator);
        wait.waitInVisibilityOfProgressBar(50);

    }

    public String getMainDatePickerText() {
        return getElementText(mainDateText);
    }

    public String getComparisonDatePickerText() {
        return getElementText(comparisonDateText);
    }

    private String getDayPreviousDay(String inputDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(inputDate, formatter);
        LocalDate previousDay = date.minusDays(1);
        return previousDay.format(formatter);
    }

    private String getPreviousWeekOrMonth(String dateRange, Boolean isMonth) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String[] parts = dateRange.split(" - ");
        LocalDate startDate = LocalDate.parse(parts[0], formatter);
        LocalDate endDate = LocalDate.parse(parts[1], formatter);
        LocalDate previousStartDate, previousEndDate;
        if (isMonth) {
            previousStartDate = startDate.minusMonths(1);
            previousEndDate = endDate.minusMonths(1);
        } else {
            previousStartDate = startDate.minusDays(7);
            previousEndDate = endDate.minusDays(7);
        }
        String formattedPreviousDaterRangeStart = previousStartDate.format(formatter);
        String formattedPreviousDaterRangeEnd = previousEndDate.format(formatter);
        return formattedPreviousDaterRangeStart + " - " + formattedPreviousDaterRangeEnd;
    }

    private String getToday() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 0);
        return dateFormat.format(cal.getTime());
    }

    private String getCurrentWeekDateRange() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate currentDate = LocalDate.now();
        LocalDate firstDayOfWeek = currentDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate lastDayOfWeek = currentDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        String formattedDateRange = firstDayOfWeek.format(formatter) + " - " + lastDayOfWeek.format(formatter);
        return formattedDateRange;
    }

    private String getCurrentMonthDateRange() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate currentDate = LocalDate.now();
        LocalDate firstDayOfMonth = currentDate.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDayOfMonth = currentDate.with(TemporalAdjusters.lastDayOfMonth());
        String formattedDateRange = firstDayOfMonth.format(formatter) + " - " + lastDayOfMonth.format(formatter);
        return formattedDateRange;
    }
    public MainAndComparisonDatePickers setMainDateInJson(){
        JsonReader.updateJsonKey("Dashboard.json","mainDate",getMainDatePickerText());
        return this;
    }
    public MainAndComparisonDatePickers setComparisonDateInJson(){
        JsonReader.updateJsonKey("Dashboard.json","comparisonDate",getComparisonDatePickerText());
        return this;
    }
    public String getMainDateFromJson(){
        return JsonReader.jsonReader("mainDate","dashboardData","Dashboard.json");
    }
    public String getComparisonDateFromJson(){
        return JsonReader.jsonReader("comparisonDate","dashboardData","Dashboard.json");
    }

    public String formatDateRangeToDDMMDDMMYYYY(String inputRange) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String[] dateParts = inputRange.split(" - ");
        LocalDate startDate = LocalDate.parse(dateParts[0], inputFormatter);
        LocalDate endDate = LocalDate.parse(dateParts[1], inputFormatter);
        String formattedStartDate = startDate.format(DateTimeFormatter.ofPattern("dd MMM"));
        String formattedEndDate = endDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        return formattedStartDate + " - " + formattedEndDate;
    }

    public String formatDateDDMMMYYYY(String inputDate) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        LocalDate date = LocalDate.parse(inputDate, inputFormatter);
        return date.format(outputFormatter);
    }

    public MainAndComparisonDatePickers assertComparisonDateIsDayBeforeMainDate() {
        assertElementText(comparisonDateText, getDayPreviousDay(getElementText(mainDateText)));
        return this;
    }

    public MainAndComparisonDatePickers assertComparisonDateIsWeekBeforeMainDate() {
        assertElementText(comparisonDateText, getPreviousWeekOrMonth(getElementText(mainDateText), false));
        return this;
    }

    public MainAndComparisonDatePickers assertComparisonDateIsMonthBeforeMainDate() {
        assertElementText(comparisonDateText, getPreviousWeekOrMonth(getElementText(mainDateText), true));
        return this;
    }


    public MainAndComparisonDatePickers assertMainDatePickerTextEqualDate(String date) {
        assertElementText(mainDateText, date);
        return this;
    }

    public MainAndComparisonDatePickers assertComparisonDatePickerTextEqualDate(String date) {
        assertElementText(comparisonDateText, date);
        return this;
    }

    public MainAndComparisonDatePickers assertMainDateTextEqualsTodayDate() {
        assertElementText(mainDateText, getToday());
        return this;
    }

    public MainAndComparisonDatePickers assertMainDateTextEqualCurrentWeek() {
        assertElementText(mainDateText, getCurrentWeekDateRange());
        return this;
    }

    public MainAndComparisonDatePickers assertMainDateTextEqualCurrentMonth() {
        assertElementText(mainDateText, getCurrentMonthDateRange());
        return this;
    }

    public MainAndComparisonDatePickers assertThatMainDatePickerIsVisible() {
        assertVisibilityOfElement(mainDatePicker);
        return this;
    }

    private void assertDatePickerIsViewed(By datePicker) {
        assertVisibilityOfElement(datePicker);
    }


    public MainAndComparisonDatePickers assertThatComparisonDatePickerIsVisible() {
        assertVisibilityOfElement(comparisonDatePicker);
        return this;
    }

    public MainAndComparisonDatePickers assertThatComparisonDatePickerDoesNotExist() {
        assertElementDoesNotExist(comparisonDatePicker);
        return this;
    }


}
