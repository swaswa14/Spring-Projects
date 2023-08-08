package ph.jsalcedo.reactivespringv2.config;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ph.jsalcedo.reactivespringv2.model.Gender;
import ph.jsalcedo.reactivespringv2.model.Person;
import ph.jsalcedo.reactivespringv2.model.PersonService;

import java.util.Random;

@Configuration
@AllArgsConstructor
@Slf4j
public class Config {



    @Bean
    public Faker faker() {
        return new Faker();
    }

    @Bean
    public Random random() {
        return new Random();
    }

    @Bean

    public CommandLineRunner commandLineRunner(  @Autowired Faker faker, @Autowired Random random, @Autowired PersonService service) {
        return args -> {
            System.out.println("Program has started!!!");

            for(int i = 0; i < 100; i++){
                Person person = Person.builder()
                        .firstName(faker.name().firstName())
                        .lastName(faker.name().lastName())
                        .gender(Gender.values()[random.nextInt(Gender.values().length)])
                        .build();
                service.save(person);
                log.info("Added person: {}", person.toString());
            }

        };
    }
}
