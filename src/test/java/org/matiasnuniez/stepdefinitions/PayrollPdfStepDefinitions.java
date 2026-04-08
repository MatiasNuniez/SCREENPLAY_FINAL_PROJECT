package org.matiasnuniez.stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.matiasnuniez.questions.DownloadButtonIsEnabled;
import org.matiasnuniez.questions.DownloadNotificationText;
import org.matiasnuniez.questions.ElementIsVisible;
import org.matiasnuniez.questions.SuccessMessageText;
import org.matiasnuniez.questions.TheTextOf;
import org.matiasnuniez.tasks.ConfirmPayrollSummary;
import org.matiasnuniez.tasks.DownloadPdf;
import org.matiasnuniez.tasks.EditEmployeeName;
import org.matiasnuniez.tasks.NavigateTo;
import org.matiasnuniez.tasks.PauseForObservation;
import org.matiasnuniez.tasks.SaveEmployeeChanges;
import org.matiasnuniez.ui.DeductionsPageUi;
import org.matiasnuniez.ui.EarningsPageUi;
import org.matiasnuniez.ui.EditEmployeePageUi;
import org.matiasnuniez.ui.ReviewPageUi;
import org.matiasnuniez.util.TestData;
import org.matiasnuniez.util.TestDataSeeder;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class PayrollPdfStepDefinitions {

    private Actor administrator;
    private TestDataSeeder seeder;

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
        seeder = new TestDataSeeder();
    }


    @Given("the administrator is on the payroll application")
    public void theAdministratorIsOnThePayrollApplication() {
        administrator = OnStage.theActorCalled(TestData.ACTOR_NAME);
    }


    @Given("the administrator navigates to the payroll summary page for a calculated payroll")
    public void theAdministratorNavigatesToThePayrollSummaryPage() {
        seeder.createEmployee(TestData.DEFAULT_EMPLOYEE_NAME, TestData.DEFAULT_CONTRACT_TYPE, TestData.DEFAULT_GROSS_SALARY)
              .calculatePayroll();

        administrator.attemptsTo(
                NavigateTo.theUrl(TestData.deductionsUrl(String.valueOf(seeder.getPayrollId())))
        );
        administrator.attemptsTo(
                WaitUntil.the(DeductionsPageUi.CONFIRM_BUTTON, isVisible())
                        .forNoMoreThan(TestData.DEFAULT_WAIT_SECONDS).seconds()
        );
        administrator.attemptsTo(PauseForObservation.forSeconds(3));
    }

    @When("the administrator clicks on {string}")
    public void theAdministratorClicksOn(String buttonText) {
        switch (buttonText) {
            case "Confirm & Finalize Cycle":
                administrator.attemptsTo(ConfirmPayrollSummary.onTheSummaryPage());
                break;
            case "Download PDF Document":
                administrator.attemptsTo(DownloadPdf.fromReviewPage());
                break;
            default:
                throw new IllegalArgumentException("Unknown button: " + buttonText);
        }
    }

    @Then("the system should navigate to the review page")
    public void theSystemShouldNavigateToTheReviewPage() {
        administrator.attemptsTo(
                WaitUntil.the(ReviewPageUi.PAGE_TITLE, isVisible())
                        .forNoMoreThan(TestData.DEFAULT_WAIT_SECONDS).seconds()
        );
        administrator.should(
                seeThat(ElementIsVisible.of(ReviewPageUi.PAGE_TITLE), is(true))
        );
        administrator.attemptsTo(PauseForObservation.forSeconds(3));
    }

    @And("the {string} button should be enabled")
    public void theButtonShouldBeEnabled(String buttonText) {
        administrator.should(
                seeThat(DownloadButtonIsEnabled.value(), is(true))
        );
        administrator.attemptsTo(PauseForObservation.forSeconds(3));
    }

    @Given("the administrator navigates to the edit page for an existing employee")
    public void theAdministratorNavigatesToTheEditPage() {
        seeder.createEmployee(TestData.DEFAULT_EMPLOYEE_NAME, TestData.DEFAULT_CONTRACT_TYPE, TestData.DEFAULT_GROSS_SALARY);

        administrator.attemptsTo(
                NavigateTo.theUrl(TestData.editUrl(String.valueOf(seeder.getEmployeeId())))
        );
        administrator.attemptsTo(
                WaitUntil.the(EditEmployeePageUi.NAME_INPUT, isVisible())
                        .forNoMoreThan(TestData.DEFAULT_WAIT_SECONDS).seconds()
        );
        administrator.attemptsTo(PauseForObservation.forSeconds(3));
    }

    @When("the administrator updates the name field with {string}")
    public void theAdministratorUpdatesTheNameFieldWith(String newName) {
        administrator.attemptsTo(EditEmployeeName.withValue(newName));
    }

    @And("the administrator saves the changes")
    public void theAdministratorSavesTheChanges() {
        administrator.attemptsTo(SaveEmployeeChanges.onEditForm());
    }

    @Then("the system should display the success message {string}")
    public void theSystemShouldDisplayTheSuccessMessage(String expectedMessage) {
        administrator.attemptsTo(
                WaitUntil.the(EditEmployeePageUi.SUCCESS_MESSAGE, isVisible())
                        .forNoMoreThan(TestData.DEFAULT_WAIT_SECONDS).seconds()
        );
        administrator.should(
                seeThat(SuccessMessageText.displayed(), containsString(expectedMessage))
        );
        administrator.attemptsTo(PauseForObservation.forSeconds(3));
    }

    @And("the system should navigate to the earnings page")
    public void theSystemShouldNavigateToTheEarningsPage() {
        administrator.attemptsTo(
                WaitUntil.the(EarningsPageUi.PAGE_TITLE, isVisible())
                        .forNoMoreThan(TestData.DEFAULT_WAIT_SECONDS).seconds()
        );
        administrator.should(
                seeThat(ElementIsVisible.of(EarningsPageUi.PAGE_TITLE), is(true))
        );
    }

    @And("the employee name displayed should be {string}")
    public void theEmployeeNameDisplayedShouldBe(String expectedName) {
        administrator.attemptsTo(
                WaitUntil.the(EarningsPageUi.EMPLOYEE_NAME, isVisible())
                        .forNoMoreThan(TestData.DEFAULT_WAIT_SECONDS).seconds()
        );
        administrator.should(
                seeThat(TheTextOf.element(EarningsPageUi.EMPLOYEE_NAME), equalTo(expectedName))
        );
    }


    @Given("the administrator has confirmed the payroll summary")
    public void theAdministratorHasConfirmedThePayrollSummary() {
        seeder.createEmployee(TestData.DEFAULT_EMPLOYEE_NAME, TestData.DEFAULT_CONTRACT_TYPE, TestData.DEFAULT_GROSS_SALARY)
              .calculatePayroll();

        administrator.attemptsTo(
                NavigateTo.theUrl(TestData.deductionsUrl(String.valueOf(seeder.getPayrollId())))
        );
        administrator.attemptsTo(
                WaitUntil.the(DeductionsPageUi.CONFIRM_BUTTON, isVisible())
                        .forNoMoreThan(TestData.DEFAULT_WAIT_SECONDS).seconds()
        );
        administrator.attemptsTo(PauseForObservation.forSeconds(3));
        administrator.attemptsTo(ConfirmPayrollSummary.onTheSummaryPage());
        administrator.attemptsTo(PauseForObservation.forSeconds(3));
    }

    @And("the administrator is on the review page")
    public void theAdministratorIsOnTheReviewPage() {
        administrator.attemptsTo(
                WaitUntil.the(ReviewPageUi.PAGE_TITLE, isVisible())
                        .forNoMoreThan(TestData.DEFAULT_WAIT_SECONDS).seconds()
        );
        administrator.attemptsTo(PauseForObservation.forSeconds(3));
    }

    @Then("the review page should display the employee name")
    public void theReviewPageShouldDisplayTheEmployeeName() {
        administrator.should(
                seeThat(ElementIsVisible.of(ReviewPageUi.EMPLOYEE_NAME), is(true))
        );
        administrator.attemptsTo(PauseForObservation.forSeconds(3));
    }

    @And("the review page should display the voucher identifier")
    public void theReviewPageShouldDisplayTheVoucherIdentifier() {
        administrator.should(
                seeThat(TheTextOf.element(ReviewPageUi.VOUCHER_IDENTIFIER), containsString("Payroll Voucher"))
        );
        administrator.attemptsTo(PauseForObservation.forSeconds(3));
    }

    @And("the review page should display the status {string}")
    public void theReviewPageShouldDisplayTheStatus(String expectedStatus) {
        administrator.should(
                seeThat(TheTextOf.element(ReviewPageUi.STATUS_CONFIRMED), equalTo(expectedStatus))
        );
        administrator.attemptsTo(PauseForObservation.forSeconds(3));
    }


    @Then("the system should display the notification {string}")
    public void theSystemShouldDisplayTheNotification(String expectedNotification) {
        administrator.attemptsTo(
                WaitUntil.the(ReviewPageUi.DOWNLOAD_SUCCESS_NOTIFICATION, isVisible())
                        .forNoMoreThan(TestData.DEFAULT_WAIT_SECONDS).seconds()
        );
        administrator.should(
                seeThat(DownloadNotificationText.displayed(), containsString(expectedNotification))
        );
    }
}
