package org.matiasnuniez.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Visibility;
import net.serenitybdd.screenplay.targets.Target;

public class ElementIsVisible implements Question<Boolean> {

    private final Target target;

    public ElementIsVisible(Target target) {
        this.target = target;
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        return Visibility.of(target).answeredBy(actor);
    }

    public static ElementIsVisible of(Target target) {
        return new ElementIsVisible(target);
    }
}
