package com.demo.weather;

import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import javax.xml.ws.WebServiceException;

/**
 * @author Georgia Bogdanou
 */
@Service
public class GeoLocatorService {


    //TODO externalise properties
    private final String GEO_LOCATOR_SERVICE_URL;
    private final String GEO_LOCATOR_USERNAME;
    private final RestTemplate restTemplate;

    public GeoLocatorService(String GEO_LOCATOR_SERVICE_URL, String GEO_LOCATOR_USERNAME, RestTemplate restTemplate) {
        this.GEO_LOCATOR_SERVICE_URL = GEO_LOCATOR_SERVICE_URL;
        this.GEO_LOCATOR_USERNAME = GEO_LOCATOR_USERNAME;
        this.restTemplate = restTemplate;
    }

    public GeoLocation getGeoLocation(String city, String countryCode) {
        try {
            return this.restTemplate.getForObject(GEO_LOCATOR_SERVICE_URL
                    + "?country={countryCode}&name={city}&maxRows=1&username={username}",
                GeoLocation.class, countryCode, city, GEO_LOCATOR_USERNAME);
        }
        catch (ResourceAccessException e){
            throw new WebServiceException("Communication to the Geolocation web service was not possible.");
        }

    }
}
