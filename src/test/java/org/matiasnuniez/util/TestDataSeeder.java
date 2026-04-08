package org.matiasnuniez.util;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestDataSeeder {

    private static final String EMPLOYEE_API = "http://localhost:8081/api/v1";
    private static final String PAYROLL_API = "http://localhost:8082/api/v1";
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final AtomicInteger COUNTER = new AtomicInteger((int) (System.currentTimeMillis() % 100000));

    private int employeeId;
    private int payrollId;
    private String employeeName;

    public TestDataSeeder createEmployee(String baseName, String contractType, double grossSalary) {
        this.employeeName = baseName + " " + COUNTER.incrementAndGet();
        String body = String.format(Locale.US,
                "{\"name\":\"%s\",\"contractType\":\"%s\",\"grossSalary\":%.2f}",
                employeeName, contractType, grossSalary
        );

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(EMPLOYEE_API + "/employees"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        String response = send(request);
        this.employeeId = extractInt(response, "\"id\"\\s*:\\s*(\\d+)");
        return this;
    }

    public TestDataSeeder calculatePayroll() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(PAYROLL_API + "/payroll/calculate/" + employeeId))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        int maxRetries = 10;
        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                String response = send(request);
                this.payrollId = extractInt(response, "\"id\"\\s*:\\s*(\\d+)");
                return this;
            } catch (RuntimeException e) {
                if (attempt == maxRetries || !e.getMessage().contains("404")) {
                    throw e;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Interrupted while waiting for employee sync", ie);
                }
            }
        }
        throw new RuntimeException("Failed to calculate payroll after " + maxRetries + " retries");
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public int getPayrollId() {
        return payrollId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    private String send(HttpRequest request) {
        try {
            HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() >= 400) {
                throw new RuntimeException(
                        "API " + request.method() + " " + request.uri()
                        + " failed [" + response.statusCode() + "]: " + response.body());
            }
            return response.body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("API call to " + request.uri() + " failed: " + e.getMessage(), e);
        }
    }

    private int extractInt(String json, String regex) {
        Matcher matcher = Pattern.compile(regex).matcher(json);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        throw new RuntimeException("Could not extract ID from response: " + json);
    }
}
