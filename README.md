# Проект `RestSensor` делится на 2 части:
###
## Часть 1: Создание REST API приложения с помощью Spring REST

Всего в приложении 4 адреса:
1. **Регистрация сенсора**
   - `POST /sensors/registration`
   ```json
   {
   "name" : "sensor_name"
   }

2. **Добавление измерения от сенсора**
   - `POST /measurements/add`
    ```json
    {
    "name" : "sensor_name",
    "raining" : "true_or_false",
    "sensor" : {
        "name" : "sensor_name"
     }
   }

3. **Получить все измерения**
   - `GET /measurements`

4. **Получить количество "дождливых" дней**
   - `GET /measurements/rainyDaysCount`

##   
## Часть 2: Создание Java клиента

Состоит из 5 методово. 2 `POST` и 3 `GET`:
####
- `POST`
1. **Метод `addSensor()` - регистрирует сенсор:**
   <img width="809" alt="Screenshot 2024-05-16 at 15 21 07" src="https://github.com/devolw/rest-sensor/assets/104515806/3562b68f-2108-4c2a-9eb3-e9e0ebf59d87">

   <img width="366" alt="Screenshot 2024-05-16 at 15 24 39" src="https://github.com/devolw/rest-sensor/assets/104515806/73205bc1-ae2e-4ac5-a5dd-776fda5bc36b">

2. **Метод `addRandom1000ValuesForMeasurement()` - отправляет 1000 запросов со случайными температурами и "дождями":**

   <img width="787" alt="Screenshot 2024-05-16 at 15 22 00" src="https://github.com/devolw/rest-sensor/assets/104515806/c7617f93-9505-4c3c-9f51-c930a09f58e7">
   <img width="997" alt="Screenshot 2024-05-16 at 15 24 17" src="https://github.com/devolw/rest-sensor/assets/104515806/5e7da95b-7a02-4f96-af42-571dcf31f376">
###
- `GET`
1. **Метод `getMeasurements()` - получить все температуры и "дожди":**

   <img width="1329" alt="Screenshot 2024-05-16 at 15 25 38" src="https://github.com/devolw/rest-sensor/assets/104515806/d9176f5f-8b32-45e1-987f-732cd097cb0a">

2. **Метод `getRainyDaysCount()` - получить количество "дождливых" дней:**

   <img width="792" alt="Screenshot 2024-05-16 at 15 26 03" src="https://github.com/devolw/rest-sensor/assets/104515806/ad8e3ff2-a946-412f-b1a5-dc4b1a333e9b">

3. **Метод `plot()` - получить температуры с сервера и построить график:**

   <img width="1365" alt="Screenshot 2024-05-16 at 15 29 33" src="https://github.com/devolw/rest-sensor/assets/104515806/409549bd-3254-4e74-a015-9632f2393905">