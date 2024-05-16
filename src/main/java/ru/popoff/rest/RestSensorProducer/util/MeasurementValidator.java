package ru.popoff.rest.RestSensorProducer.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.popoff.rest.RestSensorProducer.models.Measurement;
import ru.popoff.rest.RestSensorProducer.models.Sensor;
import ru.popoff.rest.RestSensorProducer.services.SensorService;

@Component
public class MeasurementValidator implements Validator {

    @Autowired
    private SensorService sensorService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Measurement.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Measurement measurement = (Measurement) target;

        // Проверка наличия значения сенсора
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sensor.name", "sensor.name.empty", "sensor name must not be empty");

        // Проверка наличия сенсора в базе данных
        Sensor sensor = sensorService.findSensorByName(measurement.getSensor().getName());
        if (sensor == null) {
            errors.rejectValue("sensor.name", "sensor.name.invalid", "sensor with this name does not exist in the database");
        }
    }
}