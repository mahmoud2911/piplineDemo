package common;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

public class main {

    public static void main(String[] args) {

        System.out.println(getCurrentWeekDateRange());

    }
    public static String getCurrentWeekDateRange() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate currentDate = LocalDate.now();
        LocalDate firstDayOfMonth = currentDate.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate firstMonday = firstDayOfMonth;
        while (firstMonday.getDayOfWeek() != DayOfWeek.MONDAY) {
            firstMonday = firstMonday.plusDays(1);
        }
        LocalDate lastDayOfWeek = firstMonday.plusDays(6);  // The last day of the week is 6 days later
        String formattedDateRange = firstMonday.format(formatter) + " - " + lastDayOfWeek.format(formatter);
        return formattedDateRange;
    }


}
