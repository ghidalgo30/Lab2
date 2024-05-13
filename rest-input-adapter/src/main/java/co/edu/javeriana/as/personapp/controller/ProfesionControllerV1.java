package co.edu.javeriana.as.personapp.controller;

import co.edu.javeriana.as.personapp.adapter.PersonaInputAdapterRest;
import co.edu.javeriana.as.personapp.adapter.ProfesionInputAdapterRest;
import co.edu.javeriana.as.personapp.model.request.PersonaRequest;
import co.edu.javeriana.as.personapp.model.request.ProfesionRequest;
import co.edu.javeriana.as.personapp.model.response.PersonaResponse;
import co.edu.javeriana.as.personapp.model.response.ProfesionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/profesion")
public class ProfesionControllerV1 {
	
	@Autowired
	private ProfesionInputAdapterRest profesionInputAdapterRest;
	
	@ResponseBody
	@GetMapping(path = "/{database}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProfesionResponse> profesiones(@PathVariable String database) {
		log.info("Into profesiones REST API");
			return profesionInputAdapterRest.historial(database.toUpperCase());
	}

	@ResponseBody
	@GetMapping(path = "/{database}/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ProfesionResponse findProfesion(@PathVariable String database, @PathVariable String id) {
		log.info("Into personas REST API");
		return profesionInputAdapterRest.findProfesion(database, Integer.parseInt(id));
	}
	
	@ResponseBody
	@PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ProfesionResponse crearProfesion(@RequestBody ProfesionRequest request) {
		log.info("esta en el metodo crearTarea en el controller del api");
		return profesionInputAdapterRest.crearProfesion(request);
	}

	@ResponseBody
	@PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ProfesionResponse editarProfesion(@RequestBody ProfesionRequest request, @PathVariable String id) {
		log.info("esta en el metodo editar en el controller del api");
		return profesionInputAdapterRest.editarProfesion(request, Integer.parseInt(id));
	}

	@ResponseBody
	@DeleteMapping(path = "/{database}/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Boolean eliminarProfesion(@PathVariable String database, @PathVariable String id) {
		log.info("esta en el metodo editar en el controller del api");
		return profesionInputAdapterRest.eliminarProfesion(database, Integer.parseInt(id));
	}
}
