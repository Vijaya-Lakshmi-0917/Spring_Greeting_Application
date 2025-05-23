package Spring.example.SpringGreetingApplication.controller;

import Spring.example.SpringGreetingApplication.entity.Greeting;
import Spring.example.SpringGreetingApplication.repository.GreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/greetings")
public class GreetingController {

    @Autowired
    private GreetingRepository greetingRepository;

    @GetMapping
    public ResponseEntity<List<Greeting>> getAllGreetings() {
        List<Greeting> greetings = greetingRepository.findAll();
        return ResponseEntity.ok(greetings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Greeting> getGreetingById(@PathVariable Long id) {
        Optional<Greeting> greeting = greetingRepository.findById(id);
        return greeting.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Greeting> createGreeting(@RequestBody Greeting greeting) {
        if (greeting.getMessage() == null || greeting.getMessage().trim().isEmpty()) {
            greeting.setMessage("Hello World");
        }
        Greeting saved = greetingRepository.save(greeting);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Greeting> updateGreeting(@PathVariable Long id, @RequestBody Greeting updatedGreeting) {
        return greetingRepository.findById(id)
                .map(existingGreeting -> {
                    existingGreeting.setMessage(updatedGreeting.getMessage());
                    greetingRepository.save(existingGreeting);
                    return ResponseEntity.ok(existingGreeting);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGreeting(@PathVariable Long id) {
        if (greetingRepository.existsById(id)) {
            greetingRepository.deleteById(id);
            return ResponseEntity.ok("Greeting with ID " + id + " deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}