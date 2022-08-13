package pl.upside.bearbnbbackend.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.upside.bearbnbbackend.model.ListingImage;
import java.util.UUID;

public interface ListingImagesRepository extends CrudRepository<ListingImage, UUID> {

}
