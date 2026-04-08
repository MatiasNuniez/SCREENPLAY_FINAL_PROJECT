# language: en
@PayrollPdfManagement
Feature: Payroll PDF Management and Data Confirmation
  As an administrator
  I want to confirm payroll data and generate PDF vouchers
  So that employees receive accurate documentation of their salary breakdown

  Background:
    Given the administrator is on the payroll application

  @TC-031 @HU-04 @E2E @Critical
  Scenario: Confirmation of summary enables PDF download
    Given the administrator navigates to the payroll summary page for a calculated payroll
    When the administrator clicks on "Confirm & Finalize Cycle"
    Then the system should navigate to the review page
    And the "Download PDF Document" button should be enabled

  @TC-045 @HU-02 @E2E @Medium
  Scenario: Success message after a valid employee edit
    Given the administrator navigates to the edit page for an existing employee
    When the administrator updates the name field with "Pedro Gomez"
    And the administrator saves the changes
    Then the system should display the success message "Employee and contract updated successfully!"
    And the system should navigate to the earnings page
    And the employee name displayed should be "Pedro Gomez"

  @TC-050 @HU-05 @E2E @High
  Scenario: PDF preview displays all correct fields
    Given the administrator has confirmed the payroll summary
    And the administrator is on the review page
    Then the review page should display the employee name
    And the review page should display the voucher identifier
    And the review page should display the status "STATUS: CONFIRMED"

  @TC-051 @HU-05 @E2E @High
  Scenario: Successful download notification after PDF generation
    Given the administrator has confirmed the payroll summary
    And the administrator is on the review page
    When the administrator clicks on "Download PDF Document"
    Then the system should display the notification "DOWNLOAD SUCCESSFUL"
