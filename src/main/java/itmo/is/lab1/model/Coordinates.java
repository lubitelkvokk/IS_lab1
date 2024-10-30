package itmo.is.lab1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "coordinates")
public class Coordinates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @NotNull(message = "X coordinate cannot be null")
    @Column(nullable = false)
    private Double x; //Поле не может быть null
    @Max(value = 574, message = "Y coordinate must be less than 575")
    @NotNull(message = "Y coordinate cannot be null")
    @Column(nullable = false)
    private Integer y; //Максимальное значение поля: 574, Поле не может быть null
}
