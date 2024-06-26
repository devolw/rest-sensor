package ru.popoff.rest.RestSensorConsumer.dto;


public class MeasurementDTO {

    private double value;
    private boolean raining;
    private SensorDTO sensor;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensorDTO) {
        this.sensor = sensorDTO;
    }
}