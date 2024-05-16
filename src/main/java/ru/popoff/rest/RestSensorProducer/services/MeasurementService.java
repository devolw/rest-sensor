package ru.popoff.rest.RestSensorProducer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.popoff.rest.RestSensorProducer.models.Measurement;
import ru.popoff.rest.RestSensorProducer.models.Sensor;
import ru.popoff.rest.RestSensorProducer.repositories.MeasurementRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementService {

    private final MeasurementRepository measurementRepository;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    @Transactional
    public void save(Measurement measurement, Sensor sensor) {
        measurement.setSensor(sensor);
        enrichMeasurement(measurement);
        measurementRepository.save(measurement);
    }

    private void enrichMeasurement(Measurement measurement) {
        measurement.setCreatedAt(LocalDateTime.now());
    }

    public List<Measurement> findAll() {
        return measurementRepository.findAll();
    }

    public List<Measurement> findByRaining(Boolean raining) {
        return measurementRepository.findByRaining(raining);
    }
}