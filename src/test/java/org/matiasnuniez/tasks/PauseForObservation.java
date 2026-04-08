package org.matiasnuniez.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.annotations.Step;

public class PauseForObservation implements Performable {

    private final long milliseconds;

    public PauseForObservation(long milliseconds) {
        this.milliseconds = milliseconds;
    }

    public static PauseForObservation forSeconds(int seconds) {
        return new PauseForObservation(seconds * 1000L);
    }

    @Override
    @Step("{0} pauses for observation")
    public <T extends Actor> void performAs(T actor) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
