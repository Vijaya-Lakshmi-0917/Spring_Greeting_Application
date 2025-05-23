package Spring.example.SpringGreetingApplication.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    private final GreetingService greetingService;

    @Autowired
    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping
    public ResponseEntity<Greeting> getGreeting(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName) {
        String message = greetingService.createGreetingMessage(firstName, lastName);
        return ResponseEntity.ok(new Greeting(message));
    }

    @PostMapping
    public ResponseEntity<Greeting> postGreeting() {
        return greetingService.getDefaultGreeting();
    }

    @PutMapping
    public ResponseEntity<Greeting> putGreeting() {
        return greetingService.getDefaultGreeting();
    }

    @DeleteMapping
    public ResponseEntity<Greeting> deleteGreeting() {
        return greetingService.getDefaultGreeting();
    }

    public static class Greeting {
        private String message;

        public Greeting() {}

        public Greeting(String message) {
            this.message = message;
        }
        public String getMessage() {
            return message;
        }
        public void setMessage(String message) {
            this.message = message;
        }
    }
}

@Service
class GreetingService {

    public String createGreetingMessage(String firstName, String lastName) {
        if (firstName != null && !firstName.isEmpty() && lastName != null && !lastName.isEmpty()) {
            return "Hello " + firstName + " " + lastName + "!";
        }
        if (firstName != null && !firstName.isEmpty()) {
            return "Hello " + firstName + "!";
        }
        if (lastName != null && !lastName.isEmpty()) {
            return "Hello " + lastName + "!";
        }
        return "Hello World!";
    }

    public ResponseEntity<GreetingController.Greeting> getDefaultGreeting() {
        return ResponseEntity.ok(new GreetingController.Greeting("Hello World"));
    }
}

