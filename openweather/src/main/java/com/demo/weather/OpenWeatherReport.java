package com.demo.weather;

import java.util.List;

/**
 * @author Georgia Bogdanou
 */
class OpenWeatherReport {

    //TODO public or encapsulate?
    public Coordinations coord;

    public List<Weather> weather;

    public MainInformation main;
    public Integer visibility;
    public Wind wind;
    public Clouds clouds;

    //Time of data calculation, unix, UTC
    public Long dt;
    public Sys sys;
    public Long id;
    public String name;

    //Data/time of calculation, UTC
    public String dt_txt;

    public Perspiration rain;
    public Perspiration snow;
}
