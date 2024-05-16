package ru.popoff.rest.RestSensorProducer.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "sensor")
public class Sensor {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sensorId;

    @Column(name = "name")
    @NotEmpty(message = "name should not be empty")
    @Size(min = 3, max = 30, message = "name should be between 3 and 30 characters")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "sensor")
    private List<Measurement> measurements;

    public Sensor() {

    }

    public Sensor(String name, List<Measurement> measurements) {
        this.name = name;
    }

    public int getSensorId() {
        return sensorId;
    }

    public void setSensorId(int sensorId) {
        this.sensorId = sensorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Measurement> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<Measurement> measurements) {
        this.measurements = measurements;
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "sensorId=" + sensorId +
                ", name='" + name + '}';
    }
}