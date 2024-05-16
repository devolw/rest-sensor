package ru.popoff.rest.RestSensorProducer.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.popoff.rest.RestSensorProducer.dto.SensorDTO;
import ru.popoff.rest.RestSensorProducer.models.Sensor;
import ru.popoff.rest.RestSensorProducer.services.SensorService;
import ru.popoff.rest.RestSensorProducer.util.SensorValidator;

import java.util.List;


@RestController
@RequestMapping("/sensors")
public class SensorController {

    private final SensorService sensorService;
    private final ModelMapper modelMapper;
    private final SensorValidator sensorValidator;

    @Autowired
    public SensorController(SensorService sensorService, ModelMapper modelMapper, SensorValidator sensorValidator) {
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
        this.sensorValidator = sensorValidator;
    }

    @PostMapping("/registration")
    public ResponseEntity<?> create(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult) {
        sensorValidator.validate(convertToSensor(sensorDTO), bindingResult);

        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for(FieldError error : errors) {
                errorMsg.append(error.getField()).append(" error : ").append(error.getDefaultMessage()).append(";\n");
            }

            return ResponseEntity.badRequest().body(errorMsg.toString());
        }

        sensorService.save(convertToSensor(sensorDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }
}
