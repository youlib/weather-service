package com.demo.weather;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Georgia Bogdanou
 */

@RestController
public class WelcomeController {


    //TODO delete this

    @RequestMapping("/welcome")
    public String helloWorld() {
        return "Hello World!";
    }
}
