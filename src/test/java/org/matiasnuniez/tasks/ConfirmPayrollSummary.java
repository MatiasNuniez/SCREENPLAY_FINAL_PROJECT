package org.matiasnuniez.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.matiasnuniez.ui.DeductionsPageUi;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;

public class ConfirmPayrollSummary implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(DeductionsPageUi.CONFIRM_BUTTON, isClickable())
                        .forNoMoreThan(10).seconds(),
                Click.on(DeductionsPageUi.CONFIRM_BUTTON)
        );
    }

    public static ConfirmPayrollSummary onTheSummaryPage() {
        return Tasks.instrumented(ConfirmPayrollSummary.class);
    }
}
