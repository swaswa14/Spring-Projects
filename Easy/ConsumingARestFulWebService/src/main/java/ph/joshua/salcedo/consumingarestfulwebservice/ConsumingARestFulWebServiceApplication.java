package ph.joshua.salcedo.consumingarestfulwebservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@SpringBootApplication
@Configuration
@EnableScheduling
public class ConsumingARestFulWebServiceApplication {

    private final static Logger log = LoggerFactory.getLogger(ConsumingARestFulWebServiceApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(ConsumingARestFulWebServiceApplication.class, args);
    }

    @Bean
    public Random random() {
        return new Random();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return args -> {
            for(int i = 0; i < 100; i++) {
                String result = restTemplate.getForObject(
                        "http://localhost:8080/random-number", String.class);
                log.info(result);
            }

        };
    }
}