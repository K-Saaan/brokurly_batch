package brokurly.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing  //  배치 기능 활성
@SpringBootApplication
public class BrokurlyBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(BrokurlyBatchApplication.class, args);
    }

}
