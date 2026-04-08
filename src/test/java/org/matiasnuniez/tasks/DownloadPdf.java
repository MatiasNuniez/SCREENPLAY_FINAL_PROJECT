package org.matiasnuniez.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.matiasnuniez.ui.ReviewPageUi;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;

public class DownloadPdf implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(ReviewPageUi.DOWNLOAD_PDF_BUTTON, isClickable())
                        .forNoMoreThan(10).seconds(),
                Click.on(ReviewPageUi.DOWNLOAD_PDF_BUTTON)
        );
    }

    public static DownloadPdf fromReviewPage() {
        return Tasks.instrumented(DownloadPdf.class);
    }
}
