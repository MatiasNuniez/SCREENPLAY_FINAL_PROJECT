package org.matiasnuniez.ui;

import net.serenitybdd.screenplay.targets.Target;

public class EditEmployeePageUi {

    public static final Target PAGE_TITLE = Target.the("edit profile page title")
            .locatedBy("//h1[contains(text(),'Edit Profile')]");

    public static final Target NAME_INPUT = Target.the("full name input field")
            .locatedBy("//label[contains(text(),'Full Name')]/following-sibling::input");

    public static final Target GROSS_SALARY_INPUT = Target.the("gross salary input field")
            .locatedBy("//label[contains(text(),'Monthly Gross Salary')]/following-sibling::div//input[@type='number']");

    public static final Target CONTRACT_TYPE_SELECT = Target.the("contract type select dropdown")
            .locatedBy("//label[contains(text(),'Contract Model')]/following-sibling::select");

    public static final Target START_DATE_INPUT = Target.the("start date input field")
            .locatedBy("//input[@type='date']");

    public static final Target SAVE_BUTTON = Target.the("save all changes button")
            .locatedBy("//button[@type='submit' and contains(.,'Save All Changes')]");

    public static final Target SUCCESS_MESSAGE = Target.the("success notification message")
            .locatedBy("//div[contains(@class,'bg-emerald-50')]");

    public static final Target ERROR_MESSAGE = Target.the("error notification message")
            .locatedBy("//div[contains(@class,'bg-red-50') and contains(@class,'text-red-700')]");

    private EditEmployeePageUi() {
    }
}
