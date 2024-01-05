package org.backend.dataTypes;

public class Car {
    int id, year, mileage;
    String registrationNumber, VIN;

    public Car(int id, int year, int mileage, String registrationNumber, String VIN) {
        this.id = id;
        this.year = year;
        this.mileage = mileage;
        this.registrationNumber = registrationNumber;
        this.VIN = VIN;
    }

    public int getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }
}
