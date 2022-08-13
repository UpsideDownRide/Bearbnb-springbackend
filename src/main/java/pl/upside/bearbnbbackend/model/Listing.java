package pl.upside.bearbnbbackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.data.geo.Point;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Listing {
    @Id
    @GeneratedValue
    private UUID id;

    private Point geolocation;
    private String country;
    private String city;
    private String address;

    private String title;
    private String description;

    @OneToMany(mappedBy = "listing")
    @Cascade(CascadeType.PERSIST)
    private List<ListingImage> images;

    @OneToMany(mappedBy = "listing")
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "listing")
    private List<Price> prices;


}
