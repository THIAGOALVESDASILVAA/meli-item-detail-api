package com.meli.itemdetail.restadapter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.meli.itemdetail.restadapter",
        "com.meli.itemdetail.app",
        "com.meli.itemdetail.infrastructure"
})
public class RestAdapterApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestAdapterApplication.class, args);
    }
}
