package Spring.example.SpringGreetingApplication.controller;

import Spring.example.SpringGreetingApplication.entity.Greeting;
import Spring.example.SpringGreetingApplication.repository.GreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/greetings")
public class GreetingController {

    @Autowired
    private GreetingRepository greetingRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Greeting> getGreetingById(@PathVariable Long id) {
        Optional<Greeting> greeting = greetingRepository.findById(id);
        return greeting.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Greeting> createGreeting(@RequestBody Greeting greeting) {
        Greeting saved = greetingRepository.save(greeting);
        return ResponseEntity.ok(saved);
    }
}