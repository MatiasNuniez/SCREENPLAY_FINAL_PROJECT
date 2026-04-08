package org.matiasnuniez.ui;

import net.serenitybdd.screenplay.targets.Target;

public class ReviewPageUi {

    public static final Target PAGE_TITLE = Target.the("review page title")
            .locatedBy("//h1[contains(text(),'Final Review')]");

    public static final Target EMPLOYEE_NAME = Target.the("employee name on review page")
            .locatedBy("//p[contains(@class,'text-gray-500') and contains(@class,'italic')]");

    public static final Target VOUCHER_IDENTIFIER = Target.the("payroll voucher identifier")
            .locatedBy("//span[contains(@class,'font-bold') and contains(text(),'Payroll Voucher')]");

    public static final Target STATUS_CONFIRMED = Target.the("status confirmed badge")
            .locatedBy("//span[contains(text(),'STATUS: CONFIRMED')]");

    public static final Target SUCCESS_BANNER = Target.the("payroll successfully calculated banner")
            .locatedBy("//h2[contains(text(),'Payroll Successfully Calculated')]");

    public static final Target DOWNLOAD_PDF_BUTTON = Target.the("download PDF document button")
            .locatedBy("//button[contains(.,'Download PDF Document') or contains(.,'Preparing Document')]");

    public static final Target DOWNLOAD_SUCCESS_NOTIFICATION = Target.the("download successful notification")
            .locatedBy("//div[contains(@class,'animate-pulse') and contains(.,'DOWNLOAD SUCCESSFUL')]");

    public static final Target RETURN_BUTTON = Target.the("finish and return to directory button")
            .locatedBy("//button[contains(text(),'Finish and Return to Directory')]");

    private ReviewPageUi() {
    }
}
