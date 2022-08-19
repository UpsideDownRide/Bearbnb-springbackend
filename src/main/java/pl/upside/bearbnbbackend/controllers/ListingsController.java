package pl.upside.bearbnbbackend.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.upside.bearbnbbackend.model.Listing;
import pl.upside.bearbnbbackend.model.UserDetailsImpl;
import pl.upside.bearbnbbackend.model.requests.AddImagesToListing;
import pl.upside.bearbnbbackend.model.requests.AddListingRequest;
import pl.upside.bearbnbbackend.repositories.ListingRepository;
import pl.upside.bearbnbbackend.services.FileStorageService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ListingsController {
    private final ListingRepository listingRepository;
    private final FileStorageService storageService;

    @PostMapping("/api/listings/add")
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

    @PostMapping("/api/images/add")
    public Listing addImages(@RequestParam List<MultipartFile> files, Authentication auth) {
        List<MultipartFile> f = files;
        try {
            var userDetails = (UserDetailsImpl) auth.getPrincipal();
            var userId = userDetails.getUser().getId();
            storageService.save(files.get(0), "test");
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
