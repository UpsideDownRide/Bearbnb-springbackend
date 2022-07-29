package pl.upside.bearbnbbackend.repository;
import org.springframework.data.repository.CrudRepository;
import pl.upside.bearbnbbackend.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
}
