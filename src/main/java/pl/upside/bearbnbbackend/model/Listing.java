package pl.upside.bearbnbbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.data.geo.Point;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    private Point   geolocation;
    private String  country;
    private String  city;
    private String  address;
    private String  title;
    private String  description;
    private String  type;
    private String  status;
    private int     bedrooms;
    private int     bathrooms;
    private int     guestLimit;
    private int     beds;
    private boolean petFriendly;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "users_id")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "listing")
    @Cascade({CascadeType.PERSIST, CascadeType.MERGE})
    private List<ListingImage> images;

    @OneToMany(mappedBy = "listing")
    @Cascade({CascadeType.PERSIST, CascadeType.MERGE})
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "listing")
    @Cascade({CascadeType.PERSIST, CascadeType.MERGE})
    private List<Availability> availability;

    @OneToMany(mappedBy = "listing")
    @Cascade({CascadeType.PERSIST, CascadeType.MERGE})
    private List<Price> prices;

    public void setPrices(List<Price> prices) {
        prices.forEach(price -> price.setListing(this));
        this.prices = prices;
    }

    public void setAvailability(List<Availability> availability) {
        prices.forEach(avail -> avail.setListing(this));
        this.availability = availability;
    }
}
