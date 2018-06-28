package com.demo.weather;

import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import javax.xml.ws.WebServiceException;

/**
 * @author Georgia Bogdanou
 */
@Service
public class ApixuWeatherService {


    private final String APIXU_WEATHER_SERVICE_URL;
    private final String APIXU_WEATHER_KEY;
    private final RestTemplate restTemplate;

    public ApixuWeatherService(String APIXU_WEATHER_SERVICE_URL, String APIXU_WEATHER_KEY, RestTemplate restTemplate) {
        this.APIXU_WEATHER_SERVICE_URL = APIXU_WEATHER_SERVICE_URL;
        this.APIXU_WEATHER_KEY = APIXU_WEATHER_KEY;
        this.restTemplate = restTemplate;
    }



    public ApixuWeatherReport getCurrentWeatherInformation(String city, String countryCode) {
        try {
            return this.restTemplate.getForObject(APIXU_WEATHER_SERVICE_URL
                    + "current.json?key={key}&q={city},{countryCode}",
                ApixuWeatherReport.class,  APIXU_WEATHER_KEY, city, countryCode);
        }
        catch (ResourceAccessException e){
            throw new WebServiceException("Communication to the Apixu web service was not possible.");
        }

    }

    public ApixuWeatherReport getFiveDayForecast(String city, String countryCode) {
        try {
            return this.restTemplate.getForObject(APIXU_WEATHER_SERVICE_URL
                    + "forecast.json?key={key}&q={city},{countryCode}&days=5",
                ApixuWeatherReport.class,  APIXU_WEATHER_KEY, city, countryCode);
        }
        catch (ResourceAccessException e){
            throw new WebServiceException("Communication to the Apixu web service was not possible.");
        }

    }
}
