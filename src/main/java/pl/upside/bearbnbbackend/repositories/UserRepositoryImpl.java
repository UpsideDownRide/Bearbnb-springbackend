package pl.upside.bearbnbbackend.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import pl.upside.bearbnbbackend.model.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Optional;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {

    private final EntityManager entityManager;

    @Transactional
    public boolean existsByEmail(String email){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

        Root<User> user = criteriaQuery.from(User.class);
        Predicate equalEmails = criteriaBuilder.equal(user.get("email"), email);
        criteriaQuery.where(equalEmails);

        TypedQuery<User> query = entityManager.createQuery(criteriaQuery);

        return query.setMaxResults(1).getResultList().size() == 1;
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
