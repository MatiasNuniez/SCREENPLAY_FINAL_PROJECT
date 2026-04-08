package org.matiasnuniez.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import org.matiasnuniez.ui.ReviewPageUi;

public class DownloadNotificationText implements Question<String> {

    @Override
    public String answeredBy(Actor actor) {
        return Text.of(ReviewPageUi.DOWNLOAD_SUCCESS_NOTIFICATION).answeredBy(actor);
    }

    public static DownloadNotificationText displayed() {
        return new DownloadNotificationText();
    }
}
