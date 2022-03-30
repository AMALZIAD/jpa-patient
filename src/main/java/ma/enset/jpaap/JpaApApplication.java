package ma.enset.jpaap;

import ma.enset.jpaap.entities.Patient;
import ma.enset.jpaap.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class JpaApApplication implements CommandLineRunner {

    // gestion des patients
    // declare variable de type interface  PatientRepository
    // injection des depencies
    // checrhe moi une class de ce type et l'inject
    @Autowired
    private PatientRepository patientrepository;

    public static void main(String[] args) {

        // run springboot
        SpringApplication.run(JpaApApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        // once spring is unned the this code here run
        // here you can add coe to run in the begining of loading the app
        // au demarage create those patient in table sql
        for (int i=0;i<50;i++){
            patientrepository.save(new Patient(null,"Amal"+i,new Date(),Math.random()>0.5?true:false,i));

        }

        // list des patients
        Page<Patient> patients =patientrepository.findAll(PageRequest.of(0,5));
        System.out.println("nombre de pages : "+patients.getTotalPages());
        System.out.println("Nàmbre d(enregistrement sur la page :"+patients.getTotalElements());
        System.out.println("N° page :"+patients.getNumber());
        List<Patient> content = patients.getContent();
        content.forEach(p->{
                System.out.println(p);
        });
        // find patient by id
        Patient p= patientrepository.findById(2L).orElse(null);
        if(p!=null){
            // update score
            p.setScore(100);
              System.out.println(p);
            patientrepository.save(p);
        }else {
            System.out.println("pateint not found");
        }
        patientrepository.delete(p);
        // findBymalade
       /* List<Patient> malades= patientrepository.findByMalade(true);
        System.out.println("-----------les patients malade------- ");
        malades.forEach(m->{
            System.out.println(m);
        });*/
        // findmalade pages
        Page<Patient> malades= patientrepository.findByMalade(true,PageRequest.of(3,5));
        System.out.println("-----------les patients malade par page------- ");
        malades.forEach(m->{
            System.out.println(m);
        });
        // method hql
        List<Patient> bymalade= patientrepository.recherchePatientNom("%al4%",45);
        System.out.println("-----------les patients malade method hql------- ");
        bymalade.forEach(m->{
            System.out.println(m);
        });
    }
}
