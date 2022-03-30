package ma.enset.jpaap.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
@Entity // as table in sql
@Data   // add gitter and sitters
// construteur sans et avec all params
@NoArgsConstructor @AllArgsConstructor @ToString
public class Patient {
    // primary key autoincrement
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private String nom;
    // specifier le type sur la table sql
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    private Boolean malade;
    private int score;
}
