package ru.popoff.rest.RestSensorConsumer;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Post {

    private static final RestTemplate restTemplate = new RestTemplate();
    private static final String SENSOR_NAME = "test_for_github";
    private static final Random RANDOM = new Random();

    public static void addSensor(String name) {
        Map<String, String> jsonToSend = new HashMap<>();
        jsonToSend.put("name", name);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(jsonToSend);

        String url = "http://localhost:8080/sensors/registration";
        String response = restTemplate.postForObject(url, request, String.class);

        System.out.println(response);
    }

    public static void addRandom1000ValuesForMeasurement() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        for (int i = 0; i < 1000; i++) {
            double value = RANDOM.nextDouble() * 30; // Случайное значение value от 0 до 30
            boolean raining = RANDOM.nextBoolean(); // Случайное значение raining (true или false)

            Map<String, Object> jsonToSend = new HashMap<>();
            jsonToSend.put("value", value);
            jsonToSend.put("raining", raining);

            Map<String, String> sensorData = new HashMap<>();
            sensorData.put("name", SENSOR_NAME);

            jsonToSend.put("sensor", sensorData);

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(jsonToSend, headers);
            restTemplate.postForObject("http://localhost:8080/measurements/add", request, String.class);
        }
    }

    public static void main(String[] args) {
//        addSensor(SENSOR_NAME);
//        addRandom1000ValuesForMeasurement();
    }
}