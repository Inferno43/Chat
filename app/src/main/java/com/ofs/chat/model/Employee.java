package com.ofs.chat.model;

/**
 * Created by saravana.subramanian on 3/6/17.
 */

public class Employee {

    private String userId;
    private String employeeImage;
    private String employeeId;
    private String employeeName;
    private String employeeRole;
    private String employeeDepartment;
    private String employeeTeam;
    private String employeeEmail;
    private String employeePhase;
    private String employeeExtension;

    public Employee(String userId, String employeeImage, String employeeId, String employeeName,
                    String employeeRole, String employeeDepartment, String employeeTeam, String employeeEmail,
                    String employeePhase, String employeeExtension) {
        this.userId = userId;
        this.employeeImage = employeeImage;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeRole = employeeRole;
        this.employeeDepartment = employeeDepartment;
        this.employeeTeam = employeeTeam;
        this.employeeEmail = employeeEmail;
        this.employeePhase = employeePhase;
        this.employeeExtension = employeeExtension;
    }

    public Employee() {
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(String employeeRole) {
        this.employeeRole = employeeRole;
    }


    public String getEmployeeTeam() {
        return employeeTeam;
    }

    public void setEmployeeTeam(String employeeTeam) {
        this.employeeTeam = employeeTeam;
    }

    public String getEmployeeImage() {
        return employeeImage;
    }

    public void setEmployeeImage(String employeeImage) {
        this.employeeImage = employeeImage;
    }

    public String getEmployeeExtension() {
        return employeeExtension;
    }

    public void setEmployeeExtension(String employeeExtension) {
        this.employeeExtension = employeeExtension;
    }

    public void setEmployeeDomain(String employeeImage) {
        this.employeeImage = employeeImage;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getEmployeePhase() {
        return employeePhase;
    }

    public void setEmployeePhase(String employeePhase) {
        this.employeePhase = employeePhase;
    }

    public String getEmployeeDepartment() {
        return employeeDepartment;
    }

    public void setEmployeeDepartment(String employeeDepartment) {
        this.employeeDepartment = employeeDepartment;
    }
}
