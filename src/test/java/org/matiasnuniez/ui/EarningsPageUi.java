package org.matiasnuniez.ui;

import net.serenitybdd.screenplay.targets.Target;

public class EarningsPageUi {

    public static final Target PAGE_TITLE = Target.the("earnings page title")
            .locatedBy("//h1[contains(text(),'Review')]");

    public static final Target EMPLOYEE_NAME = Target.the("employee name on earnings page")
            .locatedBy("//h2[contains(@class,'text-xl') and contains(@class,'font-bold') and contains(@class,'text-gray-900')]");

    private EarningsPageUi() {
    }
}
