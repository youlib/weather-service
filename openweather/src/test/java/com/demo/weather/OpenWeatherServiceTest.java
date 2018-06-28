package com.demo.weather;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Georgia Bogdanou
 */
public class OpenWeatherServiceTest {

    private final String OPEN_WEATHER_SERVICE_URL = "http://api.openweathermap.org/data/2.5/";
    private final String OPEN_WEATHER_KEY = "d21e1d02cf293af6aa4355d454066d71";

    @Test
    public void testOpenWeatherGetCurrentWeatherHappyScenario() {
        OpenWeatherReport openWeatherReport = new OpenWeatherService(OPEN_WEATHER_SERVICE_URL, OPEN_WEATHER_KEY, new RestTemplate())
            .getCurrentWeatherInformation("Stockholm", "SE");
        assertTrue(openWeatherReport != null);
        assertTrue(openWeatherReport.id == 2673730);
        assertTrue(openWeatherReport.name.equals("Stockholm"));
        assertTrue(openWeatherReport.dt != null);
        assertTrue(openWeatherReport.visibility != null);

        assertTrue(openWeatherReport.clouds != null);
        assertTrue(openWeatherReport.clouds.all != null);

        assertTrue(openWeatherReport.coord != null);
        assertTrue(openWeatherReport.coord.lon == 18.07f);
        assertTrue(openWeatherReport.coord.lat == 59.33f);

        assertTrue(openWeatherReport.main != null);
        assertTrue(openWeatherReport.main.humidity != null);
        assertTrue(openWeatherReport.main.pressure != null);
        assertTrue(openWeatherReport.main.temp != null);
        assertTrue(openWeatherReport.main.temp_max != null);
        assertTrue(openWeatherReport.main.temp_min != null);

        assertTrue(openWeatherReport.sys != null);
        assertTrue(openWeatherReport.sys.country.equals("SE"));
        assertTrue(openWeatherReport.sys.sunrise != null);
        assertTrue(openWeatherReport.sys.sunset != null);

        assertTrue(openWeatherReport.weather.size() == 1);
        assertFalse(openWeatherReport.weather.get(0).description.isEmpty());
        assertFalse(openWeatherReport.weather.get(0).icon.isEmpty());
        assertFalse(openWeatherReport.weather.get(0).main.isEmpty());
        assertTrue(openWeatherReport.weather.get(0).id != null);

        assertTrue(openWeatherReport.wind != null);
        //can be null from service
//        assertTrue(openWeatherReport.wind.deg != null);
        assertTrue(openWeatherReport.wind.speed != null);
    }

    @Test
    public void testOpenWeatherGetFiveDayForecastHappyScenario() {
        OpenWeatherForecast openWeatherForecast = new OpenWeatherService(OPEN_WEATHER_SERVICE_URL, OPEN_WEATHER_KEY, new RestTemplate())
            .getFiveDayForecast("Stockholm", "SE");

        assertTrue(openWeatherForecast != null);
        assertTrue(openWeatherForecast.city != null);
        assertTrue(openWeatherForecast.city.id == 2673730);
        assertTrue(openWeatherForecast.city.name.equals("Stockholm"));
        assertTrue(openWeatherForecast.city.country.equals("SE"));
        assertTrue(openWeatherForecast.city.population != null);
        assertTrue(openWeatherForecast.city.coord != null);
        assertTrue(openWeatherForecast.city.coord.lon == 18.0711f);
        assertTrue(openWeatherForecast.city.coord.lat == 59.3251f);
        assertTrue(openWeatherForecast.cnt != null);

        assertFalse(openWeatherForecast.list.isEmpty());
        for (OpenWeatherReport report: openWeatherForecast.list){
            assertFalse(report.weather.isEmpty());
            assertTrue(report.weather.get(0).id != null);
            assertFalse(report.weather.get(0).main.isEmpty());
            assertFalse(report.weather.get(0).description.isEmpty());
            assertFalse(report.weather.get(0).icon.isEmpty());
            assertTrue(report.main != null);
            assertTrue(report.main.temp != null);
            assertTrue(report.main.pressure != null);
            assertTrue(report.main.humidity != null);
            assertTrue(report.main.temp_min != null);
            assertTrue(report.main.temp_max != null);
            assertTrue(report.main.sea_level != null);
            assertTrue(report.main.grnd_level != null);
            assertTrue(report.wind != null);
            assertTrue(report.wind.speed != null);
            assertTrue(report.wind.deg != null);
            assertTrue(report.clouds != null);
            assertTrue(report.clouds.all != null);
            assertTrue(report.dt != null);
            assertTrue(report.dt_txt != null);
//            Current property can be passed as null from the service
//            if(report.snow != null){
//                assertTrue(report.snow._3h != null);
//            }
//            if(report.rain != null){
//                assertTrue(report.rain._3h != null);
//            }
        }


    }

}
