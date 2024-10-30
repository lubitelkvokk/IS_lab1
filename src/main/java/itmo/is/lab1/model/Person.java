package itmo.is.lab1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private int id;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Eye color cannot be null")
    @Column(name = "eye_color", nullable = false)
    private Color eyeColor; // Поле не может быть null

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Hair color cannot be null")
    @Column(name = "hair_color", nullable = false)
    private Color hairColor; // Поле не может быть null

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location; // Поле может быть null

    @NotNull(message = "Birthday cannot be null")
    @Column(name = "birthday", nullable = false)
    private ZonedDateTime birthday; // Поле не может быть null

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Nationality cannot be null")
    @Column(name = "nationality", nullable = false)
    private Country nationality; // Поле не может быть null

}
