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
    private String employeeDesignation;
    private String employeeTeam;
    private String employeeEmail;
    private String employeeLocation;
    private String employeeExtention;

    public Employee(String userId, String employeeImage, String employeeId, String employeeName,
                    String employeeRole, String employeeDesignation, String employeeTeam, String employeeEmail,
                    String employeeLocation, String employeeExtention) {
        this.userId = userId;
        this.employeeImage = employeeImage;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeRole = employeeRole;
        this.employeeDesignation = employeeDesignation;
        this.employeeTeam = employeeTeam;
        this.employeeEmail = employeeEmail;
        this.employeeLocation = employeeLocation;
        this.employeeExtention = employeeExtention;
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

    public String getEmployeeDesignation() {
        return employeeDesignation;
    }

    public void setEmployeeDesignation(String employeeDesignation) {
        this.employeeDesignation = employeeDesignation;
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

    public String getEmployeeExtention() {
        return employeeExtention;
    }

    public void setEmployeeExtention(String employeeExtention) {
        this.employeeExtention = employeeExtention;
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

    public String getEmployeeLocation() {
        return employeeLocation;
    }

    public void setEmployeeLocation(String employeeLocation) {
        this.employeeLocation = employeeLocation;
    }
}
