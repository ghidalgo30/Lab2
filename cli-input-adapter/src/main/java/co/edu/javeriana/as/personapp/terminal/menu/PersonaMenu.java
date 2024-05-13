package co.edu.javeriana.as.personapp.terminal.menu;

import java.util.InputMismatchException;
import java.util.Scanner;

import co.edu.javeriana.as.personapp.common.exceptions.InvalidOptionException;
import co.edu.javeriana.as.personapp.common.exceptions.NoExistException;
import co.edu.javeriana.as.personapp.domain.Gender;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.terminal.adapter.PersonaInputAdapterCli;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PersonaMenu {

	private static final int OPCION_REGRESAR_MODULOS = 0;
	private static final int PERSISTENCIA_MARIADB = 1;
	private static final int PERSISTENCIA_MONGODB = 2;

	private static final int OPCION_REGRESAR_MOTOR_PERSISTENCIA = 0;
	private static final int OPCION_VER_TODO = 1;
	private static final int OPCION_VER_UNO = 2;
	private static final int OPCION_AGREGAR = 3;
	private static final int OPCION_EDITAR = 4;
	private static final int OPCION_ELIMINAR = 5;
	// mas opciones

	public void iniciarMenu(PersonaInputAdapterCli personaInputAdapterCli, Scanner keyboard) throws NoExistException{
		boolean isValid = false;
		do {
			try {
				mostrarMenuMotorPersistencia();
				int opcion = leerOpcion(keyboard);
				switch (opcion) {
				case OPCION_REGRESAR_MODULOS:
					isValid = true;
					break;
				case PERSISTENCIA_MARIADB:
					personaInputAdapterCli.setPersonOutputPortInjection("MARIA");
					menuOpciones(personaInputAdapterCli,keyboard);
					break;
				case PERSISTENCIA_MONGODB:
					personaInputAdapterCli.setPersonOutputPortInjection("MONGO");
					menuOpciones(personaInputAdapterCli,keyboard);
					break;
				default:
					log.warn("La opción elegida no es válida.");
				}
			}  catch (InvalidOptionException e) {
				log.warn(e.getMessage());
			}
		} while (!isValid);
	}

	private void menuOpciones(PersonaInputAdapterCli personaInputAdapterCli, Scanner keyboard) throws NoExistException {
		boolean isValid = false;
		do {
			try {
				mostrarMenuOpciones();
				int opcion = leerOpcion(keyboard);
				switch (opcion) {
				case OPCION_REGRESAR_MOTOR_PERSISTENCIA:
					isValid = true;
					break;
				case OPCION_VER_TODO:
					personaInputAdapterCli.historial();					
					break;
				case OPCION_VER_UNO:
					System.out.println("Iniciando creacion de persona.");
					System.out.println("Ingrese el ID de la persona: ");
					int identificacion = keyboard.nextInt();
					personaInputAdapterCli.encontrarUno(identificacion);
					break;
				case OPCION_AGREGAR:
					System.out.println("A continuación, ingrese los datos para agregar usuario");
					personaInputAdapterCli.agregar(insertarDatosPersona(keyboard));
					break;
				case OPCION_EDITAR:
					System.out.println("A continuación, ingrese los datos para editar usuario");
					personaInputAdapterCli.editar(insertarDatosPersona(keyboard));
					break;
				case OPCION_ELIMINAR:
					System.out.println("Iniciando eliminacion de persona.");
					System.out.println("Ingrese el ID de la persona: ");
					int identificacion_drop = keyboard.nextInt();
					personaInputAdapterCli.eliminar(identificacion_drop);
					break;
				default:
					log.warn("La opción elegida no es válida.");
				}
			} catch (InputMismatchException e) {
				log.warn("Solo se permiten números.");
			}
		} while (!isValid);
	}

	private void mostrarMenuOpciones() {
		System.out.println("----------------------");
		System.out.println(OPCION_VER_TODO + " para ver todas las personas.");
		System.out.println(OPCION_VER_UNO + " para ver una persona.");
		System.out.println(OPCION_AGREGAR + " para agregar una persona.");
		System.out.println(OPCION_EDITAR + " para editar una persona.");
		System.out.println(OPCION_ELIMINAR + " para eliminar una persona.");
		// implementar otras opciones
		System.out.println(OPCION_REGRESAR_MOTOR_PERSISTENCIA + " para regresar");
	}

	private void mostrarMenuMotorPersistencia() {
		System.out.println("----------------------");
		System.out.println(PERSISTENCIA_MARIADB + " para MariaDB");
		System.out.println(PERSISTENCIA_MONGODB + " para MongoDB");
		System.out.println(OPCION_REGRESAR_MODULOS + " para regresar");
	}

	private int leerOpcion(Scanner keyboard) {
		try {
			System.out.print("Ingrese una opción: ");
			return keyboard.nextInt();
		} catch (InputMismatchException e) {
			log.warn("Solo se permiten números.");
			return leerOpcion(keyboard);
		}
	}

	private Person insertarDatosPersona(Scanner keyboard){
		System.out.println("Ingrese la identificación: ");
		int id = keyboard.nextInt();
		System.out.println("Ingrese el nombre: ");
		String nombre = keyboard.next();
		System.out.println("Ingrese el apellido: ");
		String apellido = keyboard.next();
		System.out.println("Ingrese la edad: ");
		int edad = keyboard.nextInt();
		System.out.println("Ingrese el genero (1 para MALE, 2 para FEMALE, 0 para OTHER)");
		int opcionGenero = keyboard.nextInt();
		Gender genero = Gender.OTHER;
		if(opcionGenero==1)
			genero = Gender.MALE;
		else if(opcionGenero==2)
			genero = Gender.FEMALE;
		Person newPerson = new Person(id,nombre,apellido,genero,edad);
		return newPerson;
	}


}
