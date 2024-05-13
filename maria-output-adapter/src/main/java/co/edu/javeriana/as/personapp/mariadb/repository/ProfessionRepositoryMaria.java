package co.edu.javeriana.as.personapp.mariadb.repository;

import co.edu.javeriana.as.personapp.mariadb.entity.PersonaEntity;
import co.edu.javeriana.as.personapp.mariadb.entity.ProfesionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessionRepositoryMaria extends JpaRepository<ProfesionEntity, Integer>{

}
