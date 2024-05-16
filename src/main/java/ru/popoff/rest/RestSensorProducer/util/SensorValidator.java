package ru.popoff.rest.RestSensorProducer.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.popoff.rest.RestSensorProducer.models.Sensor;
import ru.popoff.rest.RestSensorProducer.services.SensorService;

@Component
public class SensorValidator implements Validator {

    private final SensorService sensorService;

    @Autowired
    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Sensor sensor = (Sensor) target;

        Sensor existingSensor = sensorService.findSensorByName(sensor.getName());
        if (existingSensor != null) {
            errors.rejectValue("name", "duplicate.name", "sensor with this name already exists");
        }
    }
}
