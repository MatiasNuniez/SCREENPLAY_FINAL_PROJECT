package org.matiasnuniez.util;

public final class TestData {

    public static final String BASE_URL = "http://localhost:5173";

    public static final String EMPLOYEES_PATH = "/employees";
    public static final String REGISTER_PATH = "/register";
    public static final String EDIT_PATH = "/edit";
    public static final String EARNINGS_PATH = "/earnings";
    public static final String DEDUCTIONS_PATH = "/deductions";
    public static final String REVIEW_PATH = "/review";

    public static final String DEFAULT_EMPLOYEE_NAME = "Juan Fernando Quintero";
    public static final String EDITED_EMPLOYEE_NAME = "Pedro Gomez";
    public static final String DEFAULT_CONTRACT_TYPE = "FULL_TIME";
    public static final double DEFAULT_GROSS_SALARY = 3000.00;

    public static final String CONFIRM_BUTTON_TEXT = "Confirm & Finalize Cycle";
    public static final String DOWNLOAD_BUTTON_TEXT = "Download PDF Document";
    public static final String DOWNLOAD_SUCCESS_TEXT = "DOWNLOAD SUCCESSFUL";
    public static final String STATUS_CONFIRMED_TEXT = "STATUS: CONFIRMED";
    public static final String EDIT_SUCCESS_MESSAGE = "Employee and contract updated successfully!";

    public static final String ACTOR_NAME = "Administrator";

    public static final String DEFAULT_PAYROLL_ID = "1";
    public static final String DEFAULT_EMPLOYEE_ID = "1";

    public static final int DEFAULT_WAIT_SECONDS = 10;

    private TestData() {
    }

    public static String urlFor(String path) {
        return BASE_URL + path;
    }


    public static String deductionsUrl(String payrollId) {
        return BASE_URL + DEDUCTIONS_PATH + "/" + payrollId;
    }


    public static String reviewUrl(String payrollId) {
        return BASE_URL + REVIEW_PATH + "/" + payrollId;
    }

    public static String editUrl(String employeeId) {
        return BASE_URL + EDIT_PATH + "/" + employeeId;
    }
}
