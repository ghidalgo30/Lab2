package co.edu.javeriana.as.personapp.application.port.in;

import co.edu.javeriana.as.personapp.application.port.out.PersonOutputPort;
import co.edu.javeriana.as.personapp.application.port.out.StudyOutputPort;
import co.edu.javeriana.as.personapp.common.annotations.Port;
import co.edu.javeriana.as.personapp.common.exceptions.NoExistException;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.domain.Study;

import java.util.List;

@Port
public interface StudyInputPort {
    public void setPersintence(StudyInputPort studyPersintence);

    public Study create(Study study);

    public Study edit(Integer identification, Study person) throws NoExistException;

    void setPersintence(StudyOutputPort studyPersistence);

    public Boolean drop(Integer identification) throws NoExistException;

    public List<Study> findAll();

    public Study findOne(Integer identification) throws NoExistException;

    public Integer count();

}
