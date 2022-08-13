package pl.upside.bearbnbbackend.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Price {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Listing listing;

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BigDecimal adultRate;
    private BigDecimal childRate;
    private BigDecimal cleaningFee;
}
