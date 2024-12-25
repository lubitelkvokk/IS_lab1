package itmo.is.lab1.model.data;

import itmo.is.lab1.model.auth.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "location")
public class Location implements Creation{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column
    private Float x; //Поле не может быть null
    @Column
    private int y;

    @Column
    private String name; //Поле может быть null
}