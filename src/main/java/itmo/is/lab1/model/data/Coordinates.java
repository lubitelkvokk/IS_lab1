package itmo.is.lab1.model.data;

import itmo.is.lab1.model.auth.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "coordinates")
public class Coordinates implements Creation{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(nullable = false)
    private Double x; //Поле не может быть null
    @Column(nullable = false)
    private Integer y; //Максимальное значение поля: 574, Поле не может быть null
}
