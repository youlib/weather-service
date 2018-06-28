package com.demo.weather;

/**
 * @author Georgia Bogdanou
 */
class Current {

    public Long last_updated_epoch;
    public String last_updated;
    public Float temp_c;
    //TODO this boolean etc
    public Boolean is_day;

    public Condition condition;
    public Float wind_kph;
    public Float wind_degree;
    public String wind_dir;
    public Float pressure_mb;
    public Float precip_mm;
    public Integer humidity;
    public Integer cloud;
    public Float feelslike_c;
    public Float vis_km;
}
