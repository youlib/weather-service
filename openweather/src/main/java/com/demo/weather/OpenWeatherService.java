package com.demo.weather;

import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import javax.xml.ws.WebServiceException;

/**
 * @author Georgia Bogdanou
 */
@Service
public class OpenWeatherService {

    private final String OPEN_WEATHER_SERVICE_URL;
    private final String OPEN_WEATHER_KEY;
    private final RestTemplate restTemplate;

    public OpenWeatherService(String OPEN_WEATHER_SERVICE_URL, String OPEN_WEATHER_KEY, RestTemplate restTemplate) {
        //TODO validate parameter validity
        this.OPEN_WEATHER_SERVICE_URL = OPEN_WEATHER_SERVICE_URL;
        this.OPEN_WEATHER_KEY = OPEN_WEATHER_KEY;
        this.restTemplate = restTemplate;
    }

    public OpenWeatherReport getCurrentWeatherInformation(String city, String countryCode) {
        try {
            return this.restTemplate.getForObject(OPEN_WEATHER_SERVICE_URL
                    + "weather?q={city},{countryCode}&units=metric&APPID={key}",
                OpenWeatherReport.class, city, countryCode, OPEN_WEATHER_KEY);
        } catch (ResourceAccessException e) {
            throw new WebServiceException("Communication to the OpenWeather web service was not possible.");
        }

    }

    public OpenWeatherForecast getFiveDayForecast(String city, String countryCode) {
        try {
            return this.restTemplate.getForObject(OPEN_WEATHER_SERVICE_URL
                    + "forecast?q={city},{countryCode}&units=metric&APPID={key}",
                OpenWeatherForecast.class, city, countryCode, OPEN_WEATHER_KEY);
        } catch (ResourceAccessException e) {
            throw new WebServiceException("Communication to the OpenWeather web service was not possible.");
        }

    }
}
