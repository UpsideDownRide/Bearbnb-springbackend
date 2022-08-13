package pl.upside.bearbnbbackend.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.upside.bearbnbbackend.model.Listing;

import java.util.UUID;

public interface ListingRepository extends CrudRepository<Listing, UUID> {

}
