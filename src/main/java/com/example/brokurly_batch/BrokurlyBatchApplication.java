package com.example.brokurly_batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;

@EnableBatchProcessing  //  배치 기능 활성
@SpringBootApplication
public class BrokurlyBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(BrokurlyBatchApplication.class, args);
    }

}
