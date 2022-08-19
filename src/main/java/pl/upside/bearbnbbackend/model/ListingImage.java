package pl.upside.bearbnbbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ListingImage {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Listing listing;
    private String url;
//    private int width;
//    private int height;

    public ListingImage(Listing listing, String url) {
        this.listing = listing;
        this.url = url;
//        this.width = width;
//        this.height = height;
    }
}
