package co.edu.javeriana.as.personapp.application.port.in;

import co.edu.javeriana.as.personapp.application.port.out.PersonOutputPort;
import co.edu.javeriana.as.personapp.application.port.out.PhoneOutputPort;
import co.edu.javeriana.as.personapp.application.usecase.PhoneUseCase;
import co.edu.javeriana.as.personapp.common.annotations.Port;
import co.edu.javeriana.as.personapp.common.exceptions.NoExistException;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.domain.Phone;
import co.edu.javeriana.as.personapp.domain.Study;

import java.util.List;

@Port
public interface PhoneInputPort {
	
	public void setPersintence(PhoneOutputPort phonePersintence);
	
	public Phone create(Phone phone);

	public Phone edit(String identification, Phone phone) throws NoExistException;

	public Boolean drop(String identification) throws NoExistException;

	public List<Phone> findAll();

	public Phone findOne(String identification) throws NoExistException;

	public Integer count();

}
