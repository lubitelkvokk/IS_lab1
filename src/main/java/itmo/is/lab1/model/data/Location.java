package itmo.is.lab1.model.data;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private int id;

    @NotNull(message = "X location cannot be null")
    @Column
    private Float x; //Поле не может быть null
    @Column
    private int y;

    @NotNull
    @Column
    private String name; //Поле может быть null
}