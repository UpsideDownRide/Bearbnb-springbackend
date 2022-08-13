package pl.upside.bearbnbbackend.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Reservation {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Listing listing;

    private LocalDateTime startDateTime;
    private LocalDateTime endingDateTime;
}
