package ma.enset.jpaap.repositories;

import ma.enset.jpaap.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

// interface de gestion d'acces de donnee   / interface jpa spring generique il faut preciser l'entite et id
// mapping objet relationnel est fait ici
// hibernate genere la base de donnee avec la table patient
public interface PatientRepository extends JpaRepository <Patient,Long> {
    public List<Patient> findByMalade(Boolean m);
    public Page<Patient> findByMalade(Boolean m, Pageable pageable);
    public List<Patient> findByMaladeAndScoreLessThan(Boolean m,int s);
    public List<Patient> findByMaladeIsTrueAndScoreLessThan(int s);
    public List<Patient> findByDateNaissanceBetweenAndMaladeIsTrueAndNomIsLike(Date d1, Date d2, String mc);
    // or this method
    @Query("select p from Patient p where p.dateNaissance between :x and :y and p.nom like :z")
    public List<Patient> recherchePatientDate(@Param("x") Date d1, @Param("y")Date d2, @Param("z")String mc);

    @Query("select p from Patient p where  p.nom like :x and p.score < :y")
    public List<Patient> recherchePatientNom(@Param("x") String nom, @Param("y")int scoreMin);

}
