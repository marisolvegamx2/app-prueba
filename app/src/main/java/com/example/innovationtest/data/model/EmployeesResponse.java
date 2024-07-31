package com.example.innovationtest.data.model;

import java.util.List;

/**
 * Data class that captures employee information
 */
public class EmployeesResponse {

    private String status;
    private String message;
    private List<Employee> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Employee> getData() {
        return data;
    }

    public void setData(List<Employee> data) {
        this.data = data;
    }
}