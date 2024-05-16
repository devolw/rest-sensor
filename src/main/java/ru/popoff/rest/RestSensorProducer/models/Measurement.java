package ru.popoff.rest.RestSensorProducer.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "measurement")
public class Measurement {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int measurementId;

    @Column(name = "value")
    @Min(-100)
    @Max(100)
    @NotNull
    private double value;

    @Column(name = "raining")
    @NotNull
    private boolean raining;

    @Column(name = "created_at")
    @NotNull
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "sensor", referencedColumnName = "name")
    private Sensor sensor;

    public Measurement() {

    }

    public Measurement(double value, boolean raining, Sensor sensor) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
    }

    public int getMeasurementId() {
        return measurementId;
    }

    public void setMeasurementId(int measurementId) {
        this.measurementId = measurementId;
    }

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "measurementId=" + measurementId +
                ", value=" + value +
                ", raining=" + raining +
                '}';
    }
}