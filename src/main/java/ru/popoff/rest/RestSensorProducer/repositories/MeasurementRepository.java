package ru.popoff.rest.RestSensorProducer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.popoff.rest.RestSensorProducer.models.Measurement;

import java.util.List;

public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
    List<Measurement> findByRaining(Boolean raining);
}