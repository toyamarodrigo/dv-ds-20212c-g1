package ar.edu.davinci.dvds20212cg1.controller.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.davinci.dvds20212cg1.controller.TiendaAppRest;
import ar.edu.davinci.dvds20212cg1.controller.request.NegocioInsertRequest;
import ar.edu.davinci.dvds20212cg1.controller.response.NegocioResponse;
import ar.edu.davinci.dvds20212cg1.domain.Negocio;
import ar.edu.davinci.dvds20212cg1.services.NegocioService;
import ma.glasnost.orika.MapperFacade;

@RestController
public class NegocioControllerRest extends TiendaAppRest {
	private final Logger LOGGER = LoggerFactory.getLogger(NegocioControllerRest.class);

	@Autowired
	private NegocioService negocioService;
	
	@Autowired
	private MapperFacade mapper;
	
	/*
	 * Listar
	 */
	@GetMapping(path = "/negocio/all")
	public List<Negocio> getListAll() {
		LOGGER.info("Listar todos los clientes");
		
		return negocioService.list();
	}
	
	/*
	 * Listar Paginado
	 */
	@GetMapping(path = "/negocio")
	public ResponseEntity<Page<NegocioResponse>> getList(Pageable pageable) {
		LOGGER.info("Listar todos los negocios paginados");
		LOGGER.info("Pageable: " + pageable);
		
		Page<NegocioResponse> negocioResponse = null;
		Page<Negocio> negocios = null;
		
		try {
			negocios = negocioService.list(pageable);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
		
		try {
			negocioResponse = negocios.map(negocio -> mapper.map(negocio, NegocioResponse.class));
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(negocioResponse, HttpStatus.OK);
	}
	
	/*
	 * Guardar nuevo negocio
	 * 
	 * @param datosNegocio son los datos para un nuevo negocio
	 * @return un negocio nuevo
	 */
	@PostMapping(path = "/negocio")
	public ResponseEntity<NegocioResponse> createNegocio(@RequestBody NegocioInsertRequest datosNegocio) {
		Negocio negocio = null;
		NegocioResponse negocioResponse = null;
		
		// Convertir NegocioInsertRequest en Negocio
		try {
			negocio = mapper.map(datosNegocio, Negocio.class);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
		
		// Guardar el nuevo negocio
        try {
            negocio = negocioService.save(negocio);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
		
		// Convertir en Negocio Response
		try {
			negocioResponse = mapper.map(negocio, NegocioResponse.class);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(negocioResponse, HttpStatus.CREATED);
	}
	

//	@PostMapping(path = "/negocio/{negocioId}/ventaefectivo")
//	public ResponseEntity<NegocioResponse> createItem(@PathVariable("negocioId") Long negocioId,
//			@RequestBody VentaEfectivoRequest datosVenta) {
//		
//	}
	
}
