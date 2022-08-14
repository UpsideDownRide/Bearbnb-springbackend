package pl.upside.bearbnbbackend.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import pl.upside.bearbnbbackend.model.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Optional;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {

    private final EntityManager entityManager;

    @Transactional
    public boolean existsByEmail(String email){

        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
        query.setParameter("email", email);
        query.setMaxResults(1);

        return query.getResultList().size() == 1;
    }

    @Transactional
    public Optional<User> saveIfNotExists(User user) {
        if (!existsByEmail(user.getEmail())) {
            this.entityManager.persist(user);
            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }
}
