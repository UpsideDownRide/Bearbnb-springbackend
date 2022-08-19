package pl.upside.bearbnbbackend.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.upside.bearbnbbackend.model.Listing;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ListingRepository extends CrudRepository<Listing, Long> {
    Optional<List<Listing>> findAllByUserId(Long userId);
}
