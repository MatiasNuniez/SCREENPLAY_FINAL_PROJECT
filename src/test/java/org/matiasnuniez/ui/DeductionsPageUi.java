package org.matiasnuniez.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class DeductionsPageUi {

    public static final Target PAGE_TITLE = Target.the("payroll summary page title")
            .locatedBy("//h1[contains(text(),'Payroll Summary')]");

    public static final Target EMPLOYEE_NAME = Target.the("employee name on summary")
            .locatedBy("//h2[contains(@class,'text-xl') and contains(@class,'font-bold')]");

    public static final Target CONTRACT_TYPE = Target.the("contract type on summary")
            .locatedBy("//span[contains(@class,'text-xs') and contains(@class,'text-gray-500') and contains(@class,'font-medium')]");

    public static final Target GROSS_SALARY = Target.the("gross salary value")
            .locatedBy("//span[contains(text(),'Gross Salary')]/ancestor::div[contains(@class,'flex justify-between')]//span[contains(@class,'font-bold') and contains(@class,'text-lg')]");

    public static final Target DEDUCTION_AMOUNT = Target.the("deduction amount value")
            .locatedBy("//span[contains(@class,'text-red-500') and contains(@class,'font-bold')]");

    public static final Target DEDUCTION_PERCENTAGE = Target.the("deduction percentage badge")
            .locatedBy("//span[contains(@class,'bg-red-50') and contains(@class,'text-red-600')]");

    public static final Target BONUS_AMOUNT = Target.the("bonus amount value")
            .locatedBy("//span[contains(@class,'text-[#059669]') and contains(@class,'font-bold') and contains(@class,'text-lg')]");

    public static final Target BONUS_PERCENTAGE = Target.the("bonus percentage badge")
            .locatedBy("//span[contains(@class,'bg-\\[#E6F9F0\\]') and contains(@class,'text-[#059669]') and contains(@class,'font-bold')]");

    public static final Target NET_SALARY = Target.the("net salary value")
            .locatedBy("//span[contains(@class,'text-[40px]') and contains(@class,'font-bold')]");

    public static final Target CONFIRM_BUTTON = Target.the("confirm and finalize cycle button")
            .locatedBy("//button[contains(.,'Confirm') and contains(.,'Finalize')]");

    private DeductionsPageUi() {
    }
}
