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
    private int     bedrooms;
    private int     bathrooms;
    private int     beds;
    private boolean petFriendly;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "users_id")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "listing")
    @Cascade(CascadeType.PERSIST)
    private List<ListingImage> images;

    @OneToMany(mappedBy = "listing")
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "listing")
    private List<Price> prices;


}
