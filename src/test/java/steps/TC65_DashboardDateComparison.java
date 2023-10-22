package steps;

import common.Common;
import connector.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.*;


public class TC65_DashboardDateComparison {
    MainDashboardPage mainDashboardPage = new MainDashboardPage(Hooks.driver);
    EnhancedDashboardPage enhancedDashboardPage = new EnhancedDashboardPage(Hooks.driver);
    DashboardPage dashboardPage = new DashboardPage(Hooks.driver);
    CommonNormalAndEnhancedDashboard commonNormalAndEnhancedDashboard = new CommonNormalAndEnhancedDashboard(Hooks.driver);
    Common common = new Common(Hooks.driver);
    MainAndComparisonDatePickers mainAndComparisonDatePickers = new MainAndComparisonDatePickers(Hooks.driver);
    SaleReportPage saleReportPage = new SaleReportPage(Hooks.driver);
    PaymentsReportPage paymentsReportPage = new PaymentsReportPage(Hooks.driver);
    OrderTransactionsPage orderTransactionsPage = new OrderTransactionsPage(Hooks.driver);
    LoginPage loginPage = new LoginPage(Hooks.driver);
    String  textAssertionLanguage = "enTextAssertions";
    int languageIndex = 1;
    @Given("User is logged in with {string} credentials and in console home page")
    public void userIsLoggedInToConsole(String user) {
        loginPage.loginToConsole(user).assertThatUserIsLoggedIn();
    }


    @And("Click on dashboard from Main Menu in {string} localization")
    public void clickOnDashboard(String language) {
        if (language.equals("العربية")) {
            textAssertionLanguage = "arTextAssertions";
        }
        mainDashboardPage.assertDashboardMenuItemText(textAssertionLanguage)
                .clickOnDashboardLink();
    }

    @When("Click Try our new enhanced dashboard")
    public void tryNewEnhancedDashboard() {
        dashboardPage.clickOnTryEnhancedDashboardLink();
        enhancedDashboardPage.assertEnhancedDashboardPageUrl();
        commonNormalAndEnhancedDashboard.assertDashboardPageTitle(textAssertionLanguage);
    }

    @And("Click Filter Button")
    public void clickFilterButton() {
        enhancedDashboardPage.clickOnFilter();
        common.assertModalIsVisible();
    }

    @Then("Filter pop up text and design are correct")
    public void assertFilterPopUpTextAndDesign() {
        common.assertModalTitle(textAssertionLanguage)
                .assertSaveButtonText(textAssertionLanguage)
                .assertCloseButtonText(textAssertionLanguage);
        commonNormalAndEnhancedDashboard.assertBranchFilterLabelText(textAssertionLanguage)
                .assertSelectByFilterLabelText(textAssertionLanguage)
                .assertTagsLinkText(textAssertionLanguage)
                .clickTagsBranchesSwitchLink()
                .assertBranchesLinkText(textAssertionLanguage)
                .assertThatBranchFilterFieldIsVisible();
    }

    @And("Close filter pop up")
    public void closeFilterPopUp() {
        common.clickOnModalCloseButton();
    }

    @Then("Filter pop up is closed successfully")
    public void filterPopUpIsClosedSuccessfully() {
        common.assertPopUpIsNotExist();
    }

    @And("Click Compare Toggle")
    public void clickCompareToggle() {
        mainAndComparisonDatePickers.assertThatMainDatePickerIsVisible();
        enhancedDashboardPage.assertThatCompareToggleIsVisible()
                .clickOnCompareToggle();
        mainAndComparisonDatePickers.setMainDateInJson();
    }

    @Then("Compare Toggle is switched On and Comparison Date is displayed")
    public void assertThatCompareToggleIsCheckedAndComparisonDateIsDisplayed() {
        enhancedDashboardPage.assertThatCompareToggleIsChecked();
        mainAndComparisonDatePickers.assertThatComparisonDatePickerIsVisible()
                .assertThatComparisonDatePickerIsVisible()
                .assertComparisonDateIsDayBeforeMainDate();
    }

    @Then("Compare Toggle is switched off and Comparison Date is removed")
    public void assertThatCompareToggleIsUnCheckedAndComparisonDateIsRemoved() {
        enhancedDashboardPage.assertThatCompareToggleIsUnChecked();
        mainAndComparisonDatePickers.assertThatMainDatePickerIsVisible()
                .assertThatComparisonDatePickerDoesNotExist();
    }

    @Then("Enhanced Dashboard Page text and design are correct")
    public void assertDashboardTextAndDesign() {
        mainDashboardPage.assertThatSearchFieldIsVisible()
                .assertThatMyAppsIconIsVisible()
                .assertThatProfileIconIsVisible()
                .assertThatNotificationIconIsVisible()
                .assertThatWelcomeMessageIsVisible();
        enhancedDashboardPage.assertEnhancedDashboardPageUrl()
                .assertThatOrderCardIsVisible()
                .assertThatNetSalesCardIsVisible()
                .assertThatNetPaymentsCardIsVisible()
                .assertThatReturnAmountCardIsVisible()
                .assertThatDiscountAmountCardIsVisible()
                .assertThatFilterIconIsVisible()
                .assertCompareToggleLabelText(textAssertionLanguage)
                .assertOrdersCardLabelText(textAssertionLanguage)
                .assertNetSalesCardLabelText(textAssertionLanguage)
                .assertNetPaymentsCardLabelText(textAssertionLanguage);
        commonNormalAndEnhancedDashboard.assertThatDashboardIsOpened()
                .assertThatOrderCanvasChartIsVisible()
                .assertThatNetSalesCanvasChardIsVisible()
                .assertThatNetPaymentsCanvasChartIsVisible()
                .assertThatReturnAmountCanvasChartIsVisible()
                .assertThatDiscountAmountCanvasChartIsVisible()
                .assertThatHourlySalesCardIsVisible()
                .assertThatHourlySaleCanvasChartIsVisible()
                .assertThatOrderTypesCardIsVisible()
                .assertThatOrderTypesCanvasChartIsVisible()
                .assertThatTopProductByNetSalesCardIsVisible()
                .assertThatTopPaymentsCardsVisible()
                .assertThatTopBranchesByNetSalesCardIsVisible()
                .assertDashboardPageTitle(textAssertionLanguage)
                .assertDayViewText(textAssertionLanguage)
                .assertWeekViewText(textAssertionLanguage)
                .assertMonthViewText(textAssertionLanguage)
                .assertReturnAmountCardLabelText(textAssertionLanguage)
                .assertDiscountAmountCardLabelText(textAssertionLanguage)
                .assertOrderTypesCardLabelText(textAssertionLanguage)
                .assertViewOrderTypesReportText(textAssertionLanguage)
                .assertViewOrdersReportText(textAssertionLanguage)
                .assertViewNetSalesReportText(textAssertionLanguage)
                .assertViewNetPaymentsReportText(textAssertionLanguage)
                .assertViewReturnReportText(textAssertionLanguage)
                .assertViewDiscountReportText(textAssertionLanguage)
                .assertHourlySalesCardLabelText(textAssertionLanguage)
                .assertTopProductByNetSalesCardLabelText(textAssertionLanguage)
                .assertTopPaymentCardLabelText(textAssertionLanguage)
                .assertTopBranchesByNetSalesLabelText(textAssertionLanguage)
                .assertViewTopProductByNetSalesReportText(textAssertionLanguage)
                .assertViewTopPaymentsReportText(textAssertionLanguage)
                .assertViewTopBranchesByNetSalesReportText(textAssertionLanguage);
        if (Integer.parseInt(enhancedDashboardPage.getOrdersNumber()) == 0) {
            commonNormalAndEnhancedDashboard.assertTopProductsByNetSalesDescriptionText(textAssertionLanguage)
                    .assertTopPaymentsCardDescriptionText(textAssertionLanguage)
                    .assertTopBranchesByNetSalesDescriptionText(textAssertionLanguage);
        } else if (Integer.parseInt(enhancedDashboardPage.getOrdersNumber()) > 0 && Double.parseDouble(enhancedDashboardPage.getNetSalesAmountWithoutCurrency()) == 0) {
            commonNormalAndEnhancedDashboard.assertTopProductsByNetSalesDescriptionText(textAssertionLanguage)
                    .assertTopPaymentsCardDescriptionText(textAssertionLanguage)
                    .assertTopBranchesByNetSalesDescriptionText(textAssertionLanguage);
        } else if (Integer.parseInt(enhancedDashboardPage.getOrdersNumber()) > 0 && Double.parseDouble(enhancedDashboardPage.getNetSalesAmountWithoutCurrency()) > 0) {
            commonNormalAndEnhancedDashboard.assertVisibilityOfTopProductsByNetSalesCanvas()
                    .assertVisibilityOfTopPaymentsCardCanvas()
                    .assertVisibilityOfTopBranchesByNetSalesCanvas();
        }
    }

    @And("Comparison toggle is on")
    public void comparisonToggleIsOn() {
        enhancedDashboardPage.assertThatCompareToggleIsUnChecked()
                .clickOnCompareToggle()
                .assertThatCompareToggleIsChecked();
        mainAndComparisonDatePickers.assertThatMainDatePickerIsVisible()
                .assertThatComparisonDatePickerIsVisible();
        mainAndComparisonDatePickers.setComparisonDateInJson();
        mainAndComparisonDatePickers.setMainDateInJson();
    }

    @Then("Comparison date picked isn't changed")
    public void comparisonDateIsNoChanged() {
        mainAndComparisonDatePickers.assertComparisonDatePickerTextEqualDate(mainAndComparisonDatePickers.getComparisonDateFromJson());
    }


    @Then("Main date picked isn't changed")
    public void mainDateIsNoChanged() {
        mainAndComparisonDatePickers.assertMainDatePickerTextEqualDate(mainAndComparisonDatePickers.getMainDateFromJson());
    }

    @And("Click On Save")
    public void clickOnSaveButton() {
        common.clickOnModalSaveButton();
    }

    @And("Click on {string} view")
    public void clickOnDayWeekMonth(String view) {
        commonNormalAndEnhancedDashboard.setViewInJson(view);
        if (view.equals("Day")) {
            commonNormalAndEnhancedDashboard.clickOnWeekView();
            enhancedDashboardPage.clickOnCompareToggle();
            commonNormalAndEnhancedDashboard.clickOnDayView()
                    .assertDayViewIsHighlighted();
        } else if (view.equals("Week")) {
            commonNormalAndEnhancedDashboard.clickOnWeekView().assertWeekViewIsHighlighted();
        } else if (view.equals("Month")) {
            commonNormalAndEnhancedDashboard.clickOnMonthView().assertMonthViewIsHighlighted();
        }
        mainAndComparisonDatePickers.setMainDateInJson();
    }

    @And("Click {string} view")
    public void clickDayWeekMonth(String view) {
        commonNormalAndEnhancedDashboard.setViewInJson(view);
        if (view.equals("Day")) {
            commonNormalAndEnhancedDashboard.assertDayViewIsHighlighted();
        } else if (view.equals("Week")) {
            commonNormalAndEnhancedDashboard.clickOnWeekView().assertWeekViewIsHighlighted();
        } else if (view.equals("Month")) {
            commonNormalAndEnhancedDashboard.clickOnMonthView().assertMonthViewIsHighlighted();
        }
        mainAndComparisonDatePickers.setMainDateInJson();
    }

    @When("User pick date range: {string} from the Main date picker")
    public void pickDateRangeFromMainDatePicker(String dateRange) {
        String currentView = commonNormalAndEnhancedDashboard.getViewFromJson();
        if (currentView.equals("Day")) {
            mainAndComparisonDatePickers.selectDay(dateRange, false);
        } else if (currentView.equals("Week")) {
            mainAndComparisonDatePickers.selectTargetWeek(dateRange, false);
        } else if (currentView.equals("Month")) {
            mainAndComparisonDatePickers.selectTargetMonth(dateRange, false);
        }
        mainAndComparisonDatePickers.assertMainDatePickerTextEqualDate(dateRange);
        mainAndComparisonDatePickers.setMainDateInJson();
    }

    @Then("Comparison date is set to the {string} before the picked date in main date")
    public void comparisonIsSetToDayWeekMonthBefore(String view) {
        if (view.equals("Day")) {
            mainAndComparisonDatePickers.assertComparisonDateIsDayBeforeMainDate();
        } else if (view.equals("Week")) {
            mainAndComparisonDatePickers.assertComparisonDateIsWeekBeforeMainDate();
        } else if (view.equals("Month")) {
            mainAndComparisonDatePickers.assertComparisonDateIsMonthBeforeMainDate();
        }
    }

    @When("User pick date range: {string} from the Comparison date picker")
    public void pickDateRangeFromComparisonDatePicker(String dateRange) {
        String currentView = commonNormalAndEnhancedDashboard.getViewFromJson();
        if (currentView.equals("Day")) {
            mainAndComparisonDatePickers.selectDay(dateRange, true);
        } else if (currentView.equals("Week")) {
            mainAndComparisonDatePickers.selectTargetWeek(dateRange, true);
        } else if (currentView.equals("Month")) {
            mainAndComparisonDatePickers.selectTargetMonth(dateRange, true);
        }
        mainAndComparisonDatePickers.assertComparisonDatePickerTextEqualDate(dateRange);
        mainAndComparisonDatePickers.setComparisonDateInJson();
    }

    @Then("Main date picker equals current day, week or month")
    public void mainDatePickerEqualsCurrentDayMonthYear() {
        String currentView = commonNormalAndEnhancedDashboard.getViewFromJson();
        if (currentView.equals("Day")) {
            mainAndComparisonDatePickers.assertMainDateTextEqualsTodayDate();
        } else if (currentView.equals("Week")) {
            mainAndComparisonDatePickers.assertMainDateTextEqualCurrentWeek();
        } else if (currentView.equals("Month")) {
            mainAndComparisonDatePickers.assertMainDateTextEqualCurrentMonth();
        }
    }

    @Then("Percentage and totals for main comparison dates are displayed in all views for top 5 charts")
    public void percentageAndTotalsAreDisplayedInAllViews() {
        enhancedDashboardPage.assertThatOrdersPercentageIsVisible()
                .assertThatNetSalesPercentageIsVisible()
                .assertThatNetPaymentsPercentageIsVisible()
                .assertThatReturnAmountPercentageIsVisible()
                .assertThatDiscountAmountPercentageIsVisible()
                .assertOrdersMainComparisonDatesAndTotalsAreVisible()
                .assertNetSalesMainComparisonDatesAndTotalsAreVisible()
                .assertNetPaymentsMainComparisonDatesAndTotalsAreVisible()
                .assertReturnAmountMainComparisonDatesAndTotalsAreVisible()
                .assertDiscountAmountMainComparisonDatesAndTotalsAreVisible();
    }

    @Then("Compare Toggle is switched on and Comparison Date is displayed")
    public void compareToggleIsSwitchedOnAndComparisonDateIsDisplayed() {
        enhancedDashboardPage.assertThatCompareToggleIsChecked();
        mainAndComparisonDatePickers.assertThatComparisonDatePickerIsVisible();
    }

    @And("All charts are fully loaded")
    public void allChartsAreFullyLoaded() {
        commonNormalAndEnhancedDashboard.assertInVisibilityOfChartsLoadingIcon();
    }

    @Then("Charts main and comparison dates are correct")
    public void chartsMainAndComparisonDatesAreCorrect() {
        String currentMainDate = mainAndComparisonDatePickers.getMainDateFromJson();
        String currentComparisonDate = mainAndComparisonDatePickers.getComparisonDateFromJson();
        String currentView = commonNormalAndEnhancedDashboard.getViewFromJson();
        enhancedDashboardPage.assertOrderChartMainDateText(currentMainDate)
                .assertOrderChartComparisonDateText(currentComparisonDate)
                .assertNetSalesChartMainDateText(currentMainDate)
                .assertNetSalesChartComparisonDateText(currentComparisonDate)
                .assertNetPaymentsChartMainDateText(currentMainDate)
                .assertNetPaymentsChartComparisonDateText(currentComparisonDate)
                .assertReturnAmountChartMainDateText(currentMainDate)
                .assertReturnAmountChartComparisonDateText(currentComparisonDate)
                .assertDiscountAmountChartMainDateText(currentMainDate)
                .assertDiscountAmountChartComparisonDateText(currentComparisonDate)
                .assertOrderTypesChartMainDateText(currentMainDate)
                .assertOrderTypesChartComparisonDateText(currentComparisonDate);
        if (Integer.parseInt(enhancedDashboardPage.getOrdersNumber()) > 0 && Double.parseDouble(enhancedDashboardPage.getNetSalesAmountWithoutCurrency()) > 0) {
            String formattedMainDateText, formattedComparisonDateText;
            if (currentView.equals("Day")) {
                formattedMainDateText = mainAndComparisonDatePickers.formatDateDDMMMYYYY(currentMainDate);
                formattedComparisonDateText = mainAndComparisonDatePickers.formatDateDDMMMYYYY(currentComparisonDate);
            } else {
                formattedMainDateText = mainAndComparisonDatePickers.formatDateRangeToDDMMDDMMYYYY(currentMainDate);
                formattedComparisonDateText = mainAndComparisonDatePickers.formatDateRangeToDDMMDDMMYYYY(currentComparisonDate);
            }
            enhancedDashboardPage.assertTopProductsByNetSalesChartMainDateText(formattedMainDateText)
                    .assertTopProductByNetSalesChartComparisonDateText(formattedComparisonDateText)
                    .assertTopPaymentsChartMainDateText(formattedMainDateText)
                    .assertTopPaymentsChartComparisonDateText(formattedComparisonDateText)
                    .assertTopBranchesByNetSalesChartMainDateText(formattedMainDateText)
                    .assertTopBranchesByNetSalesChartComparisonDateText(formattedComparisonDateText);
        }

    }

    @And("Click main date {string}")
    public void clickMainDateOrderType(String orderType) {
        if (orderType.equals("Delivery")) {
            enhancedDashboardPage.assertMainDateOrderTypeText(orderType)
                    .clickOnMainDateOrderType(orderType);
        } else if (orderType.equals("Dine In")) {
            enhancedDashboardPage.assertMainDateOrderTypeText(orderType)
                    .clickOnMainDateOrderType(orderType);
        } else if (orderType.equals("Pick Up")) {
            enhancedDashboardPage.assertMainDateOrderTypeText(orderType)
                    .clickOnMainDateOrderType(orderType);
        } else if (orderType.equals("Drive Thru")) {
            enhancedDashboardPage.assertMainDateOrderTypeText(orderType)
                    .clickOnMainDateOrderType(orderType);
        }
    }

    @And("Click comparison date {string}")
    public void clickComparisonDateOrderType(String orderType) {
        if (orderType.equals("Delivery")) {
            enhancedDashboardPage.assertComparisonDateOrderTypeText(orderType)
                    .clickOnComparisonDateOrderType(orderType);
        } else if (orderType.equals("Dine In")) {
            enhancedDashboardPage.assertComparisonDateOrderTypeText(orderType)
                    .clickOnComparisonDateOrderType(orderType);
        } else if (orderType.equals("Pick Up")) {
            enhancedDashboardPage.assertComparisonDateOrderTypeText(orderType)
                    .clickOnComparisonDateOrderType(orderType);
        } else if (orderType.equals("Drive Thru")) {
            enhancedDashboardPage.assertComparisonDateOrderTypeText(orderType)
                    .clickOnComparisonDateOrderType(orderType);
        }
    }

    @Then("The {string} is struck through")
    public void assertThatOrderTypeIsStruckThrough(String orderType) {
        if (orderType.equals("Delivery")) {
            enhancedDashboardPage.assertThatOrderTypeIsStruckThrough(orderType);
        } else if (orderType.equals("Dine In")) {
            enhancedDashboardPage.assertThatOrderTypeIsStruckThrough(orderType);
        } else if (orderType.equals("Pick Up")) {
            enhancedDashboardPage.assertThatOrderTypeIsStruckThrough(orderType);
        } else if (orderType.equals("Drive Thru")) {
            enhancedDashboardPage.assertThatOrderTypeIsStruckThrough(orderType);
        }
    }

    @And("Get Charts Values from Enhanced Dashboard")
    public void getEnhancedDashboardChartsValues() {
        mainAndComparisonDatePickers.setMainDateInJson();
        enhancedDashboardPage.setOrdersNumberInJson()
                .setNetSalesAmountInJson()
                .setNetSalesAmountWithoutCurrencyInJson()
                .setNetPaymentsAmountInJson();
        commonNormalAndEnhancedDashboard
                .setDiscountAmountInJson()
                .setReturnAmountInJson();
    }

    @And("Click on chart {string}")
    public void clickOnChartViewReport(String viewReport) {
        if (viewReport.equals("View Net Sales Report")) {
            commonNormalAndEnhancedDashboard.assertViewNetSalesReportText(textAssertionLanguage)
                    .viewNetSaleReport()
                    .switchToNewOpenedTab();
        } else if (viewReport.equals("View Net Payment Report")) {
            commonNormalAndEnhancedDashboard.assertViewNetPaymentsReportText(textAssertionLanguage)
                    .viewNetPaymentsReport()
                    .switchToNewOpenedTab();
        }
    }

    @Then("{string} page is opened")
    public void viewReportPageIsOpened(String viewedReport) {
        String currentMainDate = mainAndComparisonDatePickers.getMainDateFromJson();
        common.assertInvisibilityOfProgressBar()
                .assertVisibilityOfLoadingIcon()
                .assertInvisibilityOfLoadingIcon()
                .assertInvisibilityOfProgressBar();
        if (viewedReport.equals("View Net Sales Report")) {
            saleReportPage.assertSalesByBranchReportPageTitle(textAssertionLanguage)
                    .assertSalesByBranchReportPageUrl()
                    .assertSalesReportDatePickerTextEqualsValue(currentMainDate);
        } else if (viewedReport.equals("View Net Payment Report")) {
            paymentsReportPage.assertPaymentsReportPageTitle(textAssertionLanguage)
                    .assertPaymentReportPageUrl()
                    .assertPaymentsReportDatePickerTextEqualsValue(currentMainDate);
        }
    }

    @And("Opened report has {string}")
    public void chartsValuesEqualsReportValue(String result) {
        if (result.equals("Orders,Net Sales, Discount Amount and Return Amount charts values correct")) {
            if (Integer.parseInt(commonNormalAndEnhancedDashboard.getOrdersCountFromJson()) == 0) {
                saleReportPage.assertSalesReportEmptyMessageText(textAssertionLanguage);
            } else {
                saleReportPage.assertTotalOrderCountEqualsValue(commonNormalAndEnhancedDashboard.getOrdersCountFromJson())
                        .assertTotalNetSalesEqualsValue(commonNormalAndEnhancedDashboard.getNetSalesAmountFromJson())
                        .assertTotalReturnAmountEqualsValue(commonNormalAndEnhancedDashboard.getReturnAmountFromJson())
                        .assertTotalDiscountAmountEqualsValue(commonNormalAndEnhancedDashboard.getDiscountAmountFromJson());
            }

        } else if (result.equals("Net Payment chart value correct")) {
            if (Integer.parseInt(commonNormalAndEnhancedDashboard.getOrdersCountFromJson()) == 0) {
                paymentsReportPage.assertPaymentsReportEmptyMessageText(textAssertionLanguage);
            } else {
                paymentsReportPage.assertTotalNetPaymentsEqualsValue(commonNormalAndEnhancedDashboard.getNetPaymentsAmountFromJson());
            }
        }
    }

    @And("Click {string} view report")
    public void clickChartViewReport(String chart) {
        mainAndComparisonDatePickers.setMainDateInJson();
        if (chart.equals("Orders")) {
            commonNormalAndEnhancedDashboard.viewOrdersReport()
                    .switchToNewOpenedTab();
        } else if (chart.equals("Net Sales")) {
            commonNormalAndEnhancedDashboard.viewNetSaleReport()
                    .switchToNewOpenedTab();
        } else if (chart.equals("Net Payments")) {
            commonNormalAndEnhancedDashboard.viewNetPaymentsReport()
                    .switchToNewOpenedTab();
        } else if (chart.equals("Return Amount")) {
            commonNormalAndEnhancedDashboard.viewReturnAmountReport()
                    .switchToNewOpenedTab();
        } else if (chart.equals("Discount Amount")) {
            commonNormalAndEnhancedDashboard.viewDiscountAmountReport()
                    .switchToNewOpenedTab();
        } else if (chart.equals("Order Types")) {
            commonNormalAndEnhancedDashboard.viewOrderTypesReport()
                    .switchToNewOpenedTab();
        } else if (chart.equals("Top Product By Net Sales")) {
            commonNormalAndEnhancedDashboard.viewTopProductByNetSalesReport()
                    .switchToNewOpenedTab();
        } else if (chart.equals("Top Payments")) {
            commonNormalAndEnhancedDashboard.viewTopPaymentsReport()
                    .switchToNewOpenedTab();
        } else if (chart.equals("Top Branches By Net Sales")) {
            commonNormalAndEnhancedDashboard.viewTopBranchesByNetSalesReport()
                    .switchToNewOpenedTab();
        }
    }

    @Then("The correct {string} linked report are opened in a new tab")
    public void correctReportViewedInNewTab(String chart) {
        String currentMainDate = mainAndComparisonDatePickers.getMainDateFromJson();
        String currentView = commonNormalAndEnhancedDashboard.getViewFromJson();
        common.assertInvisibilityOfProgressBar()
                .assertVisibilityOfLoadingIcon()
                .assertInvisibilityOfLoadingIcon()
                .assertVisibilityOfCardHeader();
        if (chart.equals("Orders") || chart.equals("Order Types")) {
            orderTransactionsPage.assertOrdersPageUrl()
                    .assertOrdersPageHeaderText(textAssertionLanguage)
                    .assertBusinessDateFilterText(currentMainDate, currentView)
                    .assertOrdersPageTitle(textAssertionLanguage);
        } else if (chart.equals("Net Sales") || chart.equals("Top Branches By Net Sales") || chart.equals("Return Amount")) {
            saleReportPage.assertSalesByBranchReportPageUrl()
                    .assertSalesByBranchReportPageTitle(textAssertionLanguage)
                    .assertSalesByBranchReportPageHeader(textAssertionLanguage)
                    .assertSalesReportDatePickerTextEqualsValue(currentMainDate);
        } else if (chart.equals("Net Payments") || chart.equals("Top Payments")) {
            paymentsReportPage.assertPaymentReportPageUrl()
                    .assertPaymentsReportPageTitle(textAssertionLanguage)
                    .assertPaymentsReportPageHeaderText(textAssertionLanguage)
                    .assertPaymentsReportDatePickerTextEqualsValue(currentMainDate);
        } else if (chart.equals("Discount Amount")) {
            saleReportPage.assertSalesByDiscountReportPageUrl()
                    .assertSalesByDiscountReportPageTitle(textAssertionLanguage)
                    .assertSalesByDiscountReportPageHeader(textAssertionLanguage)
                    .assertSalesReportDatePickerTextEqualsValue(currentMainDate);
        } else if (chart.equals("Top Product By Net Sales")) {
            saleReportPage.assertSalesByProductReportPageUrl()
                    .assertSalesByProductReportPageTitle(textAssertionLanguage)
                    .assertSalesByProductReportPageHeader(textAssertionLanguage)
                    .assertSalesReportDatePickerTextEqualsValue(currentMainDate);
        }
    }
    @Then("Main date picker total equals chart value")
    public void mainDatePickerTotalEqualsChartValue(){
      enhancedDashboardPage.assertOrderMainDateTotalEqualsChartOrderCount()
              .assertNetSalesMainDateTotalEqualsChartNetSales()
              .assertNetPaymentMainDateTotalEqualsChartNetPayment()
              .assertReturnAmountMainDateTotalEqualsChartReturnAmount()
              .assertDiscountAmountMainDateTotalEqualsChartDiscountAmount();
    }
    @Then("Charts percentage is correct")
    public void chartPercentageIsCorrect(){
      enhancedDashboardPage.assertOrderChartPercentageIsCorrect()
              .assertNetSalesChartPercentageIsCorrect()
              .assertNetPaymentChartPercentageIsCorrect()
              .assertReturnAmountChartPercentageIsCorrect()
              .assertDiscountAmountChartPercentageIsCorrect();
    }
}

