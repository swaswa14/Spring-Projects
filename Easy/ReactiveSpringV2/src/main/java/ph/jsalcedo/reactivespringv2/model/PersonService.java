package ph.jsalcedo.reactivespringv2.model;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PersonService {

    private final PersonRepository repository;


    public Person save(Person person){
        return repository.saveAndFlush(person);
    }

    public boolean delete(Long id){
        repository.deleteById(id);
        return true;
    }

    public Person update(Long id, Person updatedPerson){
        Person originalPerson = repository.findById(id).orElseThrow();
        if(updatedPerson.getFirstName() != null)
            originalPerson.setFirstName(updatedPerson.getFirstName());
        if(updatedPerson.getLastName() != null)
            originalPerson.setLastName(updatedPerson.getLastName());
        if(updatedPerson.getGender() != null)
            originalPerson.setGender(updatedPerson.getGender());

        return repository.saveAndFlush(originalPerson);
    }

    public Person findById(Long id){
        return repository.findById(id).orElseThrow();
    }

    public List<Person> findAll(){
        return repository.findAll();
    }

}
