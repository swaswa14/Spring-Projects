package ph.joshua.salcedo.restfulwebservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class RestfulWebServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestfulWebServiceApplication.class, args);
    }

    @GetMapping("/greeting")
    public ResponseEntity<Map<String, Object>> greetings(@RequestParam(value = "name", defaultValue = "World!") String name) {
        var map = new HashMap<String, Object>();
        map.put("name", name);
        map.put("greetings",String.format("Hello %s!!! Nice knowing you!", name));
        return ResponseEntity.ok(map);
    }
}
