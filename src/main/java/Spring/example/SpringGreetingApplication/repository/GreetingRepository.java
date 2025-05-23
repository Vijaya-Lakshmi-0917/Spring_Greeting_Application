package Spring.example.SpringGreetingApplication.repository;

import Spring.example.SpringGreetingApplication.entity.Greeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GreetingRepository extends JpaRepository<Greeting, Long> {
}