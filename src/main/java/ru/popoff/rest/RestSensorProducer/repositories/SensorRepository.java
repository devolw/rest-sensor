package ru.popoff.rest.RestSensorProducer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.popoff.rest.RestSensorProducer.models.Sensor;

public interface SensorRepository extends JpaRepository<Sensor, Integer> {
    Sensor findSensorByName(String name);
}