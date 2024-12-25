package itmo.is.lab1.model.data;

import itmo.is.lab1.model.auth.User;
import jakarta.persistence.*;
import jakarta.validation.Constraint;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "organization")
public class Organization implements Creation{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "official_address_id", referencedColumnName = "id", nullable = false)
    private Address officialAddress;


    @Column(name = "annual_turnover",
            nullable = false)
    private Integer annualTurnover; // Поле может быть null, значение должно быть больше 0

    @Column(name = "employees_count",
            nullable = false)
    private long employeesCount; // Значение должно быть больше 0

    @Column(name = "full_name", unique = true, nullable = false)
    private String fullName; // Длина не должна быть больше 1272, не может быть пустой, может быть null

    @Column(name = "rating")
    private double rating; // Значение должно быть больше 0

    @Enumerated(EnumType.STRING)
    @Column(name = "type",
            nullable = false)
    private OrganizationType type; // Поле может быть null

}

