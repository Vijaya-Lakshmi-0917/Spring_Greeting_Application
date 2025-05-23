package Spring.example.SpringGreetingApplication.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    @Autowired
    private GreetingService greetingService;

    @GetMapping
    public ResponseEntity<Greeting> getGreeting() {
        return greetingService.getGreetingMessage();
    }

    @PostMapping
    public ResponseEntity<Greeting> postGreeting() {
        return greetingService.getGreetingMessage();
    }

    @PutMapping
    public ResponseEntity<Greeting> putGreeting() {
        return greetingService.getGreetingMessage();
    }

    @DeleteMapping
    public ResponseEntity<Greeting> deleteGreeting() {
        return greetingService.getGreetingMessage();
    }

    static class Greeting {
        private String message;

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

