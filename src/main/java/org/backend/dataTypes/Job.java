package org.backend.dataTypes;

public class Job {
    int jobId, carId, clientId, employeeId;
    String description, repairInfo;
    float price;

    public Job(int jobId, int carId, int clientId, int employeeId, String description, String repairInfo, float price) {
        this.jobId = jobId;
        this.carId = carId;
        this.clientId = clientId;
        this.employeeId = employeeId;
        this.description = description;
        this.repairInfo = repairInfo;
        this.price = price;
    }

    public int getJobId() {
        return jobId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRepairInfo() {
        return repairInfo;
    }

    public void setRepairInfo(String repairInfo) {
        this.repairInfo = repairInfo;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
