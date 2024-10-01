package be.kdg.prog6.landside;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class LandsideApplication {

    public static void main(String[] args) {
        SpringApplication.run(LandsideApplication.class, args);
    }

}
