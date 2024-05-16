package ru.popoff.rest.RestSensorConsumer;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.springframework.web.client.RestTemplate;
import ru.popoff.rest.RestSensorConsumer.dto.MeasurementDTO;

import java.util.ArrayList;
import java.util.List;

public class Get {

    private static final RestTemplate restTemplate = new RestTemplate();
    private static final String MEASUREMENTS_URL = "http://localhost:8080/measurements";
    private static final String RAINY_DAYS_COUNT_URL = "http://localhost:8080/measurements/rainyDaysCount";

    public static void getMeasurement() {
        System.out.println(restTemplate.getForObject(MEASUREMENTS_URL, String.class));
    }

    public static void getRainyDaysCount() {
        System.out.println(restTemplate.getForObject(RAINY_DAYS_COUNT_URL, String.class));
    }

    public static void plot() {
        MeasurementDTO[] measurementDTOS = restTemplate.getForObject(MEASUREMENTS_URL, MeasurementDTO[].class);

        List<Double> xData = new ArrayList<>();
        List<Double> yData = new ArrayList<>();

        if (measurementDTOS != null) {
            for (int i = 0; i < measurementDTOS.length; i++) {
                xData.add((double) (i + 1)); // Incrementing index starts from 1
                yData.add(measurementDTOS[i].getValue());
            }

            XYChart chart = QuickChart.getChart("Sample Chart", "X", "Y", "y(x)", xData, yData);
            new SwingWrapper(chart).displayChart();
        } else {
            System.out.println("No measurements found.");
        }
    }

    public static void main(String[] args) {
//        getMeasurement();
//        getRainyDaysCount();
//        plot();
    }
}