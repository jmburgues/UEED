package edu.utn.ueed.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.UUID;

/*
In charge of electric consumption measurement.
Modificar SECONDS en linea 31 de Meter.java y en linea 60 de Transmitter.java
 */

public class Meter {
    private UUID serialId;
    private String brand;
    private String model;
    private LocalDateTime lastMeasurementDate;
    private double accumulatedMeasurement;

    public Meter(String brand, String model){
        this.serialId = UUID.randomUUID();
        this.brand = brand;
        this.model = model;
        this.lastMeasurementDate = LocalDateTime.now();
        this.accumulatedMeasurement = 0.0;
    }

    public double takeMeasurement(LocalDateTime actualDate) {
        // Simulates the location consumption per minute with a random value.
        Random r = new Random();
        double consumptionPerMinute = 0.01 + r.nextDouble() * (0.3 - 0.01);

        long unmeasuredMinutes = this.lastMeasurementDate.until(actualDate, ChronoUnit.SECONDS);
        double unmeasuredConsumption = unmeasuredMinutes * consumptionPerMinute;
        this.accumulatedMeasurement += unmeasuredConsumption;
        this.lastMeasurementDate = actualDate;
        return accumulatedMeasurement;
    }

    public UUID getSerialId() {
        return serialId;
    }

    public void setSerialId(UUID serialId) {
        this.serialId = serialId;
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

    public LocalDateTime getLastMeasurementDate() {
        return lastMeasurementDate;
    }

}
