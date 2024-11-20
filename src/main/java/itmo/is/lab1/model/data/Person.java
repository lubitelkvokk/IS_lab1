package itmo.is.lab1.model.data;

import itmo.is.lab1.model.auth.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "person")
public class Person implements Creation{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Enumerated(EnumType.STRING)
    @Column(name = "eye_color", nullable = false)
    private Color eyeColor; // Поле не может быть null

    @Enumerated(EnumType.STRING)
    @Column(name = "hair_color", nullable = false)
    private Color hairColor; // Поле не может быть null

    @ManyToOne()
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location; // Поле может быть null

    @Column(name = "birthday", nullable = false)
    private ZonedDateTime birthday; // Поле не может быть null

    @Enumerated(EnumType.STRING)
    @Column(name = "nationality", nullable = false)
    private Country nationality; // Поле не может быть null



}
