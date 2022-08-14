package pl.upside.bearbnbbackend.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.geo.Point;
import org.springframework.web.bind.annotation.*;
import pl.upside.bearbnbbackend.model.Listing;
import pl.upside.bearbnbbackend.model.ListingImage;
import pl.upside.bearbnbbackend.model.User;
import pl.upside.bearbnbbackend.repositories.ListingRepository;
import pl.upside.bearbnbbackend.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/listings")
@RequiredArgsConstructor
public class ListingsController {
    private final ListingRepository listingRepository;
    private final UserRepository userRepository;

    @PostMapping("/add")
    public Listing addListing(@RequestParam String title) {
        Listing toAdd = new Listing();
        toAdd.setTitle(title);
        toAdd.setUser(userRepository.findById(13L).orElseThrow());
        return listingRepository.save(toAdd);
    }

    @GetMapping("/test")
    public Listing test() {
        Listing toAdd = new Listing();
        User testUser = userRepository.findByEmail("t1@t.com").orElseThrow();
        toAdd.setUser(testUser);
        toAdd.setTitle("test");
        toAdd.setAddress("ulicowska 10");
        toAdd.setCountry("Poland");
        toAdd.setDescription("testowisko");
        toAdd.setGeolocation(new Point(1, 1));
        toAdd.setCity("Gniezno");
        List<ListingImage> images = new ArrayList<>();
        images.add(new ListingImage(toAdd, "test.jpg", 100, 100));
        toAdd.setImages(images);
        return listingRepository.save(toAdd);
    }
}
