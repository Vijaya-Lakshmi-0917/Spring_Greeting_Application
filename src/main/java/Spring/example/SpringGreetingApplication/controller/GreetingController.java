package Spring.example.SpringGreetingApplication.controller;

import Spring.example.SpringGreetingApplication.dto.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
public class GreetingController {

    @PostMapping("/post")
    public ResponseEntity<String> greetUser(@RequestBody User user) {
        String fullName = user.getFirstName() + " " + user.getLastName();
        String message = "Hello " + fullName + " from BridgeLabz";
        return ResponseEntity.ok(message);
    }
}