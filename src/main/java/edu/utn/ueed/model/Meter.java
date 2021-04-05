package edu.utn.ueed.model;


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.UUID;

public class Meter {
    private UUID serialId;
    private String brand;
    private String model;
    private LocalDateTime lastMeasuredDate;
    private double accumulatedMeasurement;
    private double consumptionPerMinute;

    /*
    Modificar SECONDS en linea 31 de Meter.java y en linea 60 de Transmitter.java
     */

    public Meter(String brand, String model){
        this.serialId = UUID.randomUUID();
        this.brand = brand;
        this.model = model;
        this.lastMeasuredDate = LocalDateTime.now();
        this.accumulatedMeasurement = 0.0;
        // Simulates the house consumption per minute with a random value.
        Random r = new Random();
        this.consumptionPerMinute = 0.01 + r.nextDouble() * (0.3 - 0.01);
    }

    public double getAccumulatedMeasurement(LocalDateTime actualDate) {
        long unmeasuredMinutes = this.lastMeasuredDate.until(actualDate, ChronoUnit.SECONDS);
        double unmeasuredConsumption = unmeasuredMinutes * this.consumptionPerMinute;
        this.accumulatedMeasurement += unmeasuredConsumption;
        this.lastMeasuredDate = actualDate;
        System.out.println("minutos sin medir" + unmeasuredMinutes + " consumo sin medir " + unmeasuredConsumption);
        return accumulatedMeasurement;
    }

    public UUID getSerialId() {
        return serialId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public LocalDateTime getLastMeasuredDate() {
        return lastMeasuredDate;
    }
    public double getConsumptionPerMinute() {
        return consumptionPerMinute;
    }
}
