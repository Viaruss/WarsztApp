package org.backend.dataTypes;

public class Employee {
    int employeeId, specializationId, accountId;
    String name, lastName, phoneNumber, emailAddress;

    public Employee(int employeeId, int specializationId, int accountId, String name, String lastName, String phoneNumber, String emailAddress) {
        this.employeeId = employeeId;
        this.specializationId = specializationId;
        this.accountId = accountId;
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public int getSpecializationId() {
        return specializationId;
    }

    public void setSpecializationId(int specializationId) {
        this.specializationId = specializationId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
