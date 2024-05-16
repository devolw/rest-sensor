package ru.popoff.rest.RestSensorProducer.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.popoff.rest.RestSensorProducer.dto.MeasurementDTO;
import ru.popoff.rest.RestSensorProducer.models.Measurement;
import ru.popoff.rest.RestSensorProducer.models.Sensor;
import ru.popoff.rest.RestSensorProducer.services.MeasurementService;
import ru.popoff.rest.RestSensorProducer.services.SensorService;
import ru.popoff.rest.RestSensorProducer.util.MeasurementValidator;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {

    private final MeasurementService measurementService;
    private final ModelMapper modelMapper;
    private final SensorService sensorService;
    private final MeasurementValidator measurementValidator;

    @Autowired
    public MeasurementController(MeasurementService measurementService, ModelMapper modelMapper,
                                 SensorService sensorService, MeasurementValidator measurementValidator) {
        this.measurementService = measurementService;
        this.modelMapper = modelMapper;
        this.sensorService = sensorService;
        this.measurementValidator = measurementValidator;
    }

    @GetMapping()
    public List<MeasurementDTO> getMeasurements() {
        return measurementService.findAll().stream().map(this::convertToMeasurementDTO).collect(Collectors.toList());
    }

    @GetMapping("/rainyDaysCount")
    public Integer getRainyDays() {
        List<MeasurementDTO> list = measurementService.findByRaining(true).stream().map(this::convertToMeasurementDTO).toList();
        return list.size();
    }

    @PostMapping("/add")
    public ResponseEntity<?> create(@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult) {
        measurementValidator.validate(convertToMeasurement(measurementDTO), bindingResult);

        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for(FieldError error : errors) {
                errorMsg.append(error.getField()).append(" error : ").append(error.getDefaultMessage()).append(";\n");
            }

            return ResponseEntity.badRequest().body(errorMsg.toString());
        }

        Sensor sensor = sensorService.findSensorByName(measurementDTO.getSensor().getName());
        measurementService.save(convertToMeasurement(measurementDTO), sensor);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    public Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    public MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }
}