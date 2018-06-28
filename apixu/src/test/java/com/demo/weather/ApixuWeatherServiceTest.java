package com.demo.weather;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;
import javax.xml.ws.WebServiceException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Georgia Bogdanou
 */
public class ApixuWeatherServiceTest {

    private final String APIXU_WEATHER_SERVICE_URL = "https://api.apixu.com/v1/";
    private final String APIXU_WEATHER_KEY = "128e5e14b4944123b5d110820181601";

    @Test
    public void testApixuGetCurrentWeatherHappyScenario() {
        ApixuWeatherReport apixuWeatherReport = new ApixuWeatherService(APIXU_WEATHER_SERVICE_URL, APIXU_WEATHER_KEY, new RestTemplate())
            .getCurrentWeatherInformation("Stockholm", "SE");
        assertTrue(apixuWeatherReport != null);

        validateLocation(apixuWeatherReport.location);

        validateCurrent(apixuWeatherReport.current);

        validateCondition(apixuWeatherReport.current.condition);

        assertTrue(apixuWeatherReport.forecast == null);

    }

    @Test(expected = WebServiceException.class)
    public void tetApixuWeatherServiceConnectivityIssuesBadURL() {
        ApixuWeatherService apixuWeatherService = new ApixuWeatherService("http://api.something.whatever.org/searchJSON", APIXU_WEATHER_KEY, new RestTemplate());
        apixuWeatherService.getCurrentWeatherInformation("Stockholm", "SE");

    }

    @Test
    public void testApixuGetFiveDayForecastHappyScenario() {
        ApixuWeatherReport apixuWeatherReport = new ApixuWeatherService(APIXU_WEATHER_SERVICE_URL, APIXU_WEATHER_KEY, new RestTemplate())
            .getFiveDayForecast("Stockholm", "SE");
        assertTrue(apixuWeatherReport != null);

        validateLocation(apixuWeatherReport.location);

        validateCurrent(apixuWeatherReport.current);

        validateCondition(apixuWeatherReport.current.condition);

        validateForecast(apixuWeatherReport.forecast);

    }

    private void validateForecast(Forecast forecast) {
        assertFalse(forecast == null);
        assertTrue(forecast.forecastday.size() == 5);
        for (Forecastday forecastday : forecast.forecastday) {
            assertFalse(forecastday.date.isEmpty());
            assertTrue(forecastday.date_epoch != null);
            assertTrue(forecastday.astro != null);
            assertFalse(forecastday.astro.moonrise.isEmpty());
            assertFalse(forecastday.astro.moonset.isEmpty());
            assertFalse(forecastday.astro.sunrise.isEmpty());
            assertFalse(forecastday.astro.sunset.isEmpty());
            assertTrue(forecastday.day != null);
            assertTrue(forecastday.day.avghumidity != null);
            assertTrue(forecastday.day.avgtemp_c != null);
            assertTrue(forecastday.day.avgvis_km != null);
            assertTrue(forecastday.day.maxtemp_c != null);
            assertTrue(forecastday.day.maxwind_kph != null);
            assertTrue(forecastday.day.mintemp_c != null);
            assertTrue(forecastday.day.totalprecip_mm != null);
            assertTrue(forecastday.day.uv != null);
            validateCondition(forecastday.day.condition);
        }
    }

    private void validateCurrent(Current current) {
        assertTrue(current != null);
        assertTrue(current.cloud != null);
        assertTrue(current.feelslike_c != null);
        assertTrue(current.humidity != null);
        assertTrue(current.is_day != null);
        assertFalse(current.last_updated.isEmpty());
        assertTrue(current.last_updated_epoch != null);
        assertTrue(current.precip_mm != null);
        assertTrue(current.pressure_mb != null);
        assertTrue(current.temp_c != null);
        assertTrue(current.vis_km != null);
        assertTrue(current.wind_degree != null);
        assertFalse(current.wind_dir.isEmpty());
        assertTrue(current.wind_kph != null);
    }

    private void validateLocation(Location location) {
        assertTrue(location != null);
        assertTrue(location.country.equals("Sweden"));
        assertTrue(location.name.equals("Stockholm"));
        assertTrue(location.region.equals("Stockholms Lan"));
        assertFalse(location.tz_id.isEmpty());
        assertTrue(location.localtime.startsWith("20"));
        assertTrue(location.lat == 59.33f);
        assertTrue(location.lon == 18.05f);
        assertFalse(location.localtime_epoch == null);
    }

    private void validateCondition(Condition condition) {
        assertTrue(condition != null);
        assertTrue(condition.code != null);
        assertFalse(condition.icon.isEmpty());
        assertFalse(condition.text.isEmpty());
    }

}