package co.edu.javeriana.as.personapp.mariadb.adapter;

import co.edu.javeriana.as.personapp.application.port.out.PersonOutputPort;
import co.edu.javeriana.as.personapp.application.port.out.StudyOutputPort;
import co.edu.javeriana.as.personapp.common.annotations.Adapter;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.domain.Study;
import co.edu.javeriana.as.personapp.mariadb.entity.EstudiosEntity;
import co.edu.javeriana.as.personapp.mariadb.entity.PersonaEntity;
import co.edu.javeriana.as.personapp.mariadb.mapper.EstudiosMapperMaria;
import co.edu.javeriana.as.personapp.mariadb.mapper.PersonaMapperMaria;
import co.edu.javeriana.as.personapp.mariadb.repository.EstudioRepositoryMaria;
import co.edu.javeriana.as.personapp.mariadb.repository.PersonaRepositoryMaria;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Adapter("studyOutputAdapterMaria")
@Transactional
public class StudyOutputAdapterMaria implements StudyOutputPort {

	@Autowired
	private EstudioRepositoryMaria estudioRepositoryMaria;

	@Autowired
	private EstudiosMapperMaria estudiosMapperMaria;

	@Override
	public Study save(Study study) {
		log.debug("Into save on Adapter MariaDB");
		EstudiosEntity persistedEstudio = estudioRepositoryMaria.save(estudiosMapperMaria.fromDomainToAdapter(study));
		return estudiosMapperMaria.fromAdapterToDomain(persistedEstudio);
	}

	@Override
	public Boolean delete(Integer identification) {
		log.debug("Into delete on Adapter MariaDB");
		estudioRepositoryMaria.deleteById(identification);
		return estudioRepositoryMaria.findById(identification).isEmpty();
	}

	@Override
	public List<Study> find() {
		log.debug("Into find on Adapter MariaDB");
		return estudioRepositoryMaria.findAll().stream().map(estudiosMapperMaria::fromAdapterToDomain)
				.collect(Collectors.toList());
	}

	@Override
	public Study findById(Integer identification) {
		log.debug("Into findById on Adapter MariaDB");
		if (estudioRepositoryMaria.findById(identification).isEmpty()) {
			return null;
		} else {
			return estudiosMapperMaria.fromAdapterToDomain(estudioRepositoryMaria.findById(identification).get());
		}
	}

}
