package ph.joshua.salcedo.consumingarestfulwebservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

@RestController

public class RandomController {


    private final Random random;
    private final AtomicLong counter = new AtomicLong();
    @Autowired
    public RandomController(Random random) {
        this.random = random;
    }



    @GetMapping("/random-number")
    public String randomNumber(){
        return String.format(
                """
                <h1>Random Number Generator</h1>
                <h2> Attempts : %d </h2>
                number: <b> %02d </b>
""",counter.incrementAndGet(), random.nextInt(100)+1);
    }
}
