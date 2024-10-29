package itmo.is.lab1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "organization")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull(message = "Official address cannot be null")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "official_address_id", referencedColumnName = "id")
    private Address officialAddress; // Поле не может быть null

    @Min(value = 1, message = "Annual turnover must be greater than 0")
    @Column(name = "annual_turnover")
    private Integer annualTurnover; // Поле может быть null, значение должно быть больше 0

    @Min(value = 1, message = "Employee count must be greater than 0")
    @Column(name = "employees_count", nullable = false)
    private long employeesCount; // Значение должно быть больше 0

    @Size(max = 1272, message = "Full name cannot exceed 1272 characters")
    @NotBlank(message = "Full name cannot be blank")
    @Column(name = "full_name")
    private String fullName; // Длина не должна быть больше 1272, не может быть пустой, может быть null

    @Min(value = 0, message = "Rating must be greater than 0")
    @Column(name = "rating", nullable = false)
    private double rating; // Значение должно быть больше 0

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private OrganizationType type; // Поле может быть null

}
