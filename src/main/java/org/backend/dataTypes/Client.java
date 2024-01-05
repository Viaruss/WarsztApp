package org.backend.dataTypes;

public class Client {
    int id;
    String name, lastName, address, phoneNumber;
    boolean testDrivePermission;

    public Client(int id, String name, String lastName, String address, String phoneNumber, boolean testDrivePermission) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.testDrivePermission = testDrivePermission;
    }

    public int getId() {
        return id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isTestDrivePermission() {
        return testDrivePermission;
    }

    public void setTestDrivePermission(boolean testDrivePermission) {
        this.testDrivePermission = testDrivePermission;
    }
}
