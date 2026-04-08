package org.matiasnuniez.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.targets.Target;


public class TheTextOf implements Question<String> {

    private final Target target;

    public TheTextOf(Target target) {
        this.target = target;
    }

    @Override
    public String answeredBy(Actor actor) {
        return Text.of(target).answeredBy(actor);
    }

    public static TheTextOf element(Target target) {
        return new TheTextOf(target);
    }
}
