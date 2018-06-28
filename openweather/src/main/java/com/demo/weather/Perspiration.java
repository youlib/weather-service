package com.demo.weather;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Georgia Bogdanou
 */
class Perspiration {

    /**
     * Current property can be passed as null from the service
     */
    @JsonProperty("3h")
    public Double _3h;
}
