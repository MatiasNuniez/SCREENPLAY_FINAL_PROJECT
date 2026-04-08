package org.matiasnuniez.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.matiasnuniez.ui.ReviewPageUi;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;

public class DownloadPdf implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(ReviewPageUi.DOWNLOAD_PDF_BUTTON, isClickable())
                        .forNoMoreThan(10).seconds()
        );

        WebElement button = BrowseTheWeb.as(actor).getDriver()
                .findElement(By.xpath("//button[contains(.,'Download PDF Document') or contains(.,'Preparing Document')]"));
        ((JavascriptExecutor) BrowseTheWeb.as(actor).getDriver())
                .executeScript("arguments[0].click()", button);
    }

    public static DownloadPdf fromReviewPage() {
        return Tasks.instrumented(DownloadPdf.class);
    }
}
