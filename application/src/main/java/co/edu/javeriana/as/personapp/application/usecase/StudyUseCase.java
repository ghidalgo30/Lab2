package co.edu.javeriana.as.personapp.application.usecase;

import co.edu.javeriana.as.personapp.application.port.in.StudyInputPort;
import co.edu.javeriana.as.personapp.application.port.out.StudyOutputPort;
import co.edu.javeriana.as.personapp.common.annotations.UseCase;
import co.edu.javeriana.as.personapp.common.exceptions.NoExistException;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.domain.Study;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Slf4j
@UseCase
public class StudyUseCase implements StudyInputPort {
    private StudyOutputPort studyPersistence;


    public StudyUseCase(@Qualifier("studyOutputAdapterMaria") StudyOutputPort studyPersintence) {
        this.studyPersistence=studyPersintence;
    }

    @Override
    public void setPersintence(StudyOutputPort studyPersistence) {
        this.studyPersistence=studyPersistence;
    }

    @Override
    public void setPersintence(StudyInputPort studyPersintence) {

    }

    @Override
    public Study create(Study study) {
        log.debug("Into create on Application Domain");
        return studyPersistence.save(study);
    }

    @Override
    public Study edit(Integer identification, Study study) throws NoExistException {
        Study oldStudy = studyPersistence.findById(identification);
        if (oldStudy != null)
            return studyPersistence.save(study);
        throw new NoExistException(
                "The study with id " + identification + " does not exist into db, cannot be edited");
    }

    @Override
    public Boolean drop(Integer identification) throws NoExistException {
        Study oldStudy = studyPersistence.findById(identification);
        if (oldStudy != null)
            return studyPersistence.delete(identification);
        throw new NoExistException(
                "The person with id " + identification + " does not exist into db, cannot be dropped");
    }

    @Override
    public List<Study> findAll() {
        log.info("Output: " + studyPersistence.getClass());
        return studyPersistence.find();
    }

    @Override
    public Study findOne(Integer identification) throws NoExistException {
        Study oldStudy = studyPersistence.findById(identification);
        if (oldStudy != null)
            return oldStudy;
        throw new NoExistException("The study with id " + identification + " does not exist into db, cannot be found");
    }

    @Override
    public Integer count() {
        return findAll().size();
    }
}
