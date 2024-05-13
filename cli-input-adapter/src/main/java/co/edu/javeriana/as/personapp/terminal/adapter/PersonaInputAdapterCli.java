package co.edu.javeriana.as.personapp.terminal.adapter;

import java.util.List;
import java.util.stream.Collectors;

import co.edu.javeriana.as.personapp.common.exceptions.NoExistException;
import co.edu.javeriana.as.personapp.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import co.edu.javeriana.as.personapp.application.port.in.PersonInputPort;
import co.edu.javeriana.as.personapp.application.port.out.PersonOutputPort;
import co.edu.javeriana.as.personapp.application.usecase.PersonUseCase;
import co.edu.javeriana.as.personapp.common.annotations.Adapter;
import co.edu.javeriana.as.personapp.common.exceptions.InvalidOptionException;
import co.edu.javeriana.as.personapp.common.setup.DatabaseOption;
import co.edu.javeriana.as.personapp.terminal.mapper.PersonaMapperCli;
import co.edu.javeriana.as.personapp.terminal.model.PersonaModelCli;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Adapter
public class PersonaInputAdapterCli {

	@Autowired
	@Qualifier("personOutputAdapterMaria")
	private PersonOutputPort personOutputPortMaria;

	@Autowired
	@Qualifier("personOutputAdapterMongo")
	private PersonOutputPort personOutputPortMongo;

	@Autowired
	private PersonaMapperCli personaMapperCli;

	PersonInputPort personInputPort;

	public void setPersonOutputPortInjection(String dbOption) throws InvalidOptionException {
		if (dbOption.equalsIgnoreCase(DatabaseOption.MARIA.toString())) {
			personInputPort = new PersonUseCase(personOutputPortMaria);
		} else if (dbOption.equalsIgnoreCase(DatabaseOption.MONGO.toString())) {
			personInputPort = new PersonUseCase(personOutputPortMongo);
		} else {
			throw new InvalidOptionException("Invalid database option: " + dbOption);
		}
	}

	public void historial1() {
		log.info("Into historial PersonaEntity in Input Adapter");
		List<PersonaModelCli> persona = personInputPort.findAll().stream().map(personaMapperCli::fromDomainToAdapterCli)
					.collect(Collectors.toList());
		persona.forEach(p -> System.out.println(p.toString()));
	}
	public void historial() {
	    log.info("Into historial PersonaEntity in Input Adapter");
	    personInputPort.findAll().stream()
	        .map(personaMapperCli::fromDomainToAdapterCli)
	        .forEach(System.out::println);
	}
	public void encontrarUno(Integer identificacion){
		log.info("Into encontrar uno.");
		try {
				Person person = personInputPort.findOne(identificacion);
				if(person!=null){
					System.out.println(personaMapperCli.fromDomainToAdapterCli(person).toString());
				}
		} catch (NoExistException e) {
			System.out.println("El usuario no existe.");
		}
	}
	public void agregar(Person newPerson){
		log.info("Into historial PersonaEntity in Input Adapter");
		Person person = personInputPort.create(newPerson);
		System.out.println("Se creo el usuario: "+ personaMapperCli.fromDomainToAdapterCli(person).toString());
	}
	public void editar(Person newPerson) throws NoExistException{
		log.info("Into historial PersonaEntity in Input Adapter");
		Person person = personInputPort.edit(newPerson.getIdentification(), newPerson);
		if(person!=null)
			System.out.println("Se creo el usuario: "+ personaMapperCli.fromDomainToAdapterCli(person).toString());
	}
	public void eliminar(Integer identificacion) throws NoExistException{
		log.info("Into historial PersonaEntity in Input Adapter");
		boolean eliminado = personInputPort.drop(identificacion);
		if(eliminado==true)
			System.out.println("El registro ha sido eliminado.");
	}

}
