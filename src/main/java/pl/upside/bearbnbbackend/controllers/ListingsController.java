package pl.upside.bearbnbbackend.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.upside.bearbnbbackend.model.Listing;
import pl.upside.bearbnbbackend.model.UserDetailsImpl;
import pl.upside.bearbnbbackend.model.requests.AddImagesToListing;
import pl.upside.bearbnbbackend.model.requests.AddListingRequest;
import pl.upside.bearbnbbackend.repositories.ListingRepository;

import java.time.LocalDateTime;

@RestController
@Slf4j
@RequestMapping("/api/listings")
@RequiredArgsConstructor
public class ListingsController {
    private final ListingRepository listingRepository;

    @PostMapping("/add")
    public Listing addListing(@RequestBody AddListingRequest addListingRequest, Authentication auth) {
        Listing toAdd = new Listing();

        toAdd.setTitle(addListingRequest.title());
        toAdd.setDescription(addListingRequest.description());
        toAdd.setCity(addListingRequest.city());
        toAdd.setCountry(addListingRequest.country());
        toAdd.setAddress(addListingRequest.address());
        toAdd.setType(addListingRequest.type());
        toAdd.setBedrooms(addListingRequest.bedrooms());
        toAdd.setBathrooms(addListingRequest.bathrooms());
        toAdd.setGuestLimit(addListingRequest.guestsLimit());
        toAdd.setGeolocation(addListingRequest.location());
        toAdd.setCreatedAt(LocalDateTime.now());
        toAdd.setModifiedAt(LocalDateTime.now());

        toAdd.setPrices(addListingRequest.pricingDates());
        toAdd.setAvailability(addListingRequest.availabilityDates());

        UserDetailsImpl details = (UserDetailsImpl) auth.getPrincipal();
        toAdd.setUser(details.getUser());
        return listingRepository.save(toAdd);
    }

    @PostMapping("/add/images/{id}")
    public Listing addImages(@RequestBody AddImagesToListing addImageToListingRequest, Authentication auth) {
        return null;
    }
}
