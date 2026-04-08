package org.matiasnuniez.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Enabled;
import org.matiasnuniez.ui.ReviewPageUi;

public class DownloadButtonIsEnabled implements Question<Boolean> {

    @Override
    public Boolean answeredBy(Actor actor) {
        return Enabled.of(ReviewPageUi.DOWNLOAD_PDF_BUTTON).answeredBy(actor);
    }

    public static DownloadButtonIsEnabled value() {
        return new DownloadButtonIsEnabled();
    }
}
