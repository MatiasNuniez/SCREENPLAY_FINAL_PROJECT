package org.matiasnuniez.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import org.matiasnuniez.ui.EditEmployeePageUi;

public class SuccessMessageText implements Question<String> {

    @Override
    public String answeredBy(Actor actor) {
        return Text.of(EditEmployeePageUi.SUCCESS_MESSAGE).answeredBy(actor);
    }

    public static SuccessMessageText displayed() {
        return new SuccessMessageText();
    }
}
