package ph.jsalcedo.reactivespringv2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ph.jsalcedo.reactivespringv2.model.Person;
import ph.jsalcedo.reactivespringv2.model.PersonService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService service;

    @GetMapping("/id")
    public Mono<Person> getPersonById(Long id){
        return Mono.just(service.findById(id));
    }

    @GetMapping("/all")
    public Flux<Person>getAllPersons(){
        return Flux.fromIterable(service.findAll());

    }
}
