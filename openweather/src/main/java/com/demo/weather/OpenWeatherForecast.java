package com.demo.weather;

import java.util.List;

/**
 * @author Georgia Bogdanou
 */
class OpenWeatherForecast {

    //Number of lines returned by this API call
    public Integer cnt;
    public City city;
    public List<OpenWeatherReport> list;
}
