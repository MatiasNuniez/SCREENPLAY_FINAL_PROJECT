package org.matiasnuniez.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Clear;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.matiasnuniez.ui.EditEmployeePageUi;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class EditEmployeeName implements Task {

    private final String newName;

    public EditEmployeeName(String newName) {
        this.newName = newName;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(EditEmployeePageUi.NAME_INPUT, isVisible())
                        .forNoMoreThan(10).seconds(),
                Clear.field(EditEmployeePageUi.NAME_INPUT),
                Enter.theValue(newName).into(EditEmployeePageUi.NAME_INPUT)
        );
    }

    public static EditEmployeeName withValue(String newName) {
        return Tasks.instrumented(EditEmployeeName.class, newName);
    }
}
