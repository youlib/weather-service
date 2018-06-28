package com.demo.weather;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;
import javax.xml.ws.WebServiceException;

import static org.junit.Assert.*;

/**
 * @author Georgia Bogdanou
 */
public class GeoLocatorServiceIntegrationTest {

    private static final String GEO_LOCATOR_SERVICE_URL = "http://api.geonames.org/searchJSON";
    private static final String GEO_LOCATOR_USERNAME = "youlib";

    @Test
    public void testGeoLocatorServiceHappyScenario(){
        GeoLocatorService geoLocatorService = new GeoLocatorService(GEO_LOCATOR_SERVICE_URL, GEO_LOCATOR_USERNAME, new RestTemplate());
        GeoLocation geoLocation = geoLocatorService.getGeoLocation("Stockholm", "SE");
        assertTrue(geoLocation.totalResultsCount > 0);
        assertTrue(geoLocation.geonames.size() == 1);
        Geonames geonames = geoLocation.geonames.get(0);
        assertTrue(geonames.lng == 18.0649f);
        assertTrue(geonames.geonameId == 2673730);
        assertTrue(geonames.countryId == 2661886);
        assertTrue(geonames.countryCode.equals("SE"));
        assertTrue(geonames.name.equals("Stockholm"));
        assertTrue(geonames.countryName.equals("Sweden"));
        assertTrue(geonames.adminName1.equals("Stockholm"));
        assertTrue(geonames.lat == 59.33258f);
    }

    @Test
    public void testGeoLocatorServiceResultNotFound(){
        GeoLocatorService geoLocatorService = new GeoLocatorService(GEO_LOCATOR_SERVICE_URL, GEO_LOCATOR_USERNAME, new RestTemplate());
        GeoLocation geoLocation = geoLocatorService.getGeoLocation("Lalaland", "LE");
        assertTrue(geoLocation.totalResultsCount == 0);

    }

    @Test
    public void testGeoLocatorServiceConnectivityIssuesBadUsername(){
        GeoLocatorService geoLocatorService = new GeoLocatorService(GEO_LOCATOR_SERVICE_URL, "nobodyknowsme", new RestTemplate());
        GeoLocation geoLocation = geoLocatorService.getGeoLocation("Stockholm", "SE");
        //TODO handle error
        //returns 200 and response is
//        {
//            "status": {
//            "message": "user does not exist.",
//                "value": 10
//        }
//        }
    }

    @Test(expected = WebServiceException.class)
    public void testGeoLocatorServiceConnectivityIssuesBadURL(){
        GeoLocatorService geoLocatorService = new GeoLocatorService("http://api.something.whatever.org/searchJSON", GEO_LOCATOR_USERNAME, new RestTemplate());
        geoLocatorService.getGeoLocation("Stockholm", "SE");

    }
}
