package itmo.is.lab1.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private int id;

    @NotNull(message = "Address's street name cannot be null")
    @Size(max = 196, message = "Size of address's street name must be less than 197")
    @Column(nullable = false)
    private String street; //Длина строки не должна быть больше 196, Поле может быть null
    @NotNull(message = "zipCode of address cannot be null")
    @Column(nullable = false)
    private String zipCode; //Поле может быть null
}