package co.edu.javeriana.as.personapp.controller;

import co.edu.javeriana.as.personapp.adapter.PersonaInputAdapterRest;
import co.edu.javeriana.as.personapp.adapter.TelefonoInputAdapterRest;
import co.edu.javeriana.as.personapp.model.request.PersonaRequest;
import co.edu.javeriana.as.personapp.model.request.TelefonoRequest;
import co.edu.javeriana.as.personapp.model.response.PersonaResponse;
import co.edu.javeriana.as.personapp.model.response.TelefonoResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/telefono")
public class TelefonoControllerV1 {
	
	@Autowired
	private TelefonoInputAdapterRest telefonoInputAdapterRest;
	
	@ResponseBody
	@GetMapping(path = "/{database}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TelefonoResponse> telefonos(@PathVariable String database) {
		log.info("Into personas REST API");
			return telefonoInputAdapterRest.historial(database.toUpperCase());
	}

	@ResponseBody
	@GetMapping(path = "/{database}/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public TelefonoResponse findTelefono(@PathVariable String database, @PathVariable String id) {
		log.info("Into personas REST API");
		return telefonoInputAdapterRest.findTelefono(database, id);
	}
	
	@ResponseBody
	@PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public TelefonoResponse crearTelefono(@RequestBody TelefonoRequest request) {
		log.info("esta en el metodo crearTarea en el controller del api");
		return telefonoInputAdapterRest.crearTelefono(request);
	}

	@ResponseBody
	@PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public TelefonoResponse editarTelefono(@RequestBody TelefonoRequest request, @PathVariable String id) {
		log.info("esta en el metodo editar en el controller del api");
		return telefonoInputAdapterRest.editarTelefono(request, id);
	}

	@ResponseBody
	@DeleteMapping(path = "/{database}/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Boolean eliminarTelefono(@PathVariable String database, @PathVariable String id) {
		log.info("esta en el metodo editar en el controller del api");
		return telefonoInputAdapterRest.eliminarTelefono(database, id);
	}
}
