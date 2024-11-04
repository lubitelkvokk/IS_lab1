package itmo.is.lab1.model.data;

import jakarta.persistence.*;
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
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Integer id;

    @JoinColumn(name = "official_address_id", referencedColumnName = "id")
    @OneToOne(cascade = CascadeType.ALL)
    private Address officialAddress; // Поле не может быть null

    @Column(name = "annual_turnover")
    private Integer annualTurnover; // Поле может быть null, значение должно быть больше 0

    @Column(name = "employees_count", nullable = false)
    private long employeesCount; // Значение должно быть больше 0

    @Column(name = "full_name")
    private String fullName; // Длина не должна быть больше 1272, не может быть пустой, может быть null

    @Column(name = "rating", nullable = false)
    private double rating; // Значение должно быть больше 0

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private OrganizationType type; // Поле может быть null

}

