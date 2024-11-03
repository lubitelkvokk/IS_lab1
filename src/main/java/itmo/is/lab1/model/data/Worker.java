package itmo.is.lab1.model.data;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "worker", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Min(value = 1, message = "ID must be greater than 0")
    @Column(name = "id", nullable = false, unique = true)
    private int id; // Значение должно быть больше 0, уникально, генерируется автоматически

    @NotBlank(message = "Name cannot be null or empty")
    @Column(name = "name", nullable = false)
    private String name; // Поле не может быть null, строка не может быть пустой

    @NotNull(message = "Coordinates cannot be null")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coordinates_id", referencedColumnName = "id")
    private Coordinates coordinates; // Поле не может быть null

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull(message = "Creation date cannot be null")
    @Column(name = "creation_date", nullable = false, updatable = false)
    private Date creationDate; // Поле не может быть null, генерируется автоматически

    @NotNull(message = "Organization cannot be null")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "organization_id", referencedColumnName = "id")
    private Organization organization; // Поле не может быть null

    @Min(value = 1, message = "Salary must be greater than 0")
    @Column(name = "salary", nullable = false)
    private long salary; // Значение должно быть больше 0

    @Min(value = 1, message = "Rating must be greater than 0")
    @Column(name = "rating")
    private Integer rating; // Поле может быть null, значение должно быть больше 0

    @Column(name = "end_date")
    private LocalDateTime endDate; // Поле может быть null

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Position cannot be null")
    @Column(name = "position", nullable = false)
    private Position position; // Поле не может быть null

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Status cannot be null")
    @Column(name = "status", nullable = false)
    private Status status; // Поле не может быть null

    @NotNull(message = "Person cannot be null")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person; // Поле не может быть null
}
