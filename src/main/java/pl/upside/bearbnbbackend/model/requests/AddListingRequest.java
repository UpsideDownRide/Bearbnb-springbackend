package pl.upside.bearbnbbackend.model.requests;

import org.springframework.data.geo.Point;
import pl.upside.bearbnbbackend.model.Availability;
import pl.upside.bearbnbbackend.model.Price;

import java.util.List;

public record AddListingRequest(String title, String description, String type, Integer bedrooms,
                                Integer bathrooms, Integer guestsLimit, Boolean petsAllowed,
                                String country, String city, String address, Point location,
                                List<Price> pricingDates, List<Availability> availabilityDates) {
}
