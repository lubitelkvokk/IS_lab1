package itmo.is.lab1.model.auth;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity(name = "admin_requests")
@Data
public class AdminRequests {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "sender_id", nullable = false, referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private User sender;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(name = "accepted")
    private Boolean accepted = false;
}
