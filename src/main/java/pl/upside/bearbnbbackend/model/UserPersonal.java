package pl.upside.bearbnbbackend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserPersonal {
    @Id
    @Column(name = "user_id")
    private Long id;

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    public UserPersonal(String firstName, String lastName, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }
}
