package itmo.is.lab1.model.additional;

import itmo.is.lab1.model.auth.User;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class HistoryOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @ManyToOne
    private User user;

    @Column
    private boolean status;

    @Column
    private Integer objCount;

    @Column
    private String filename;

}
