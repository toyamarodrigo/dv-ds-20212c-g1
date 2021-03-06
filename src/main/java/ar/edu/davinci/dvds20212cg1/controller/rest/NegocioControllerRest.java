package ar.edu.davinci.dvds20212cg1.controller.rest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.davinci.dvds20212cg1.Constantes;
import ar.edu.davinci.dvds20212cg1.controller.TiendaAppRest;
import ar.edu.davinci.dvds20212cg1.controller.request.NegocioInsertRequest;
import ar.edu.davinci.dvds20212cg1.controller.request.NegocioUpdateRequest;
import ar.edu.davinci.dvds20212cg1.controller.response.NegocioResponse;
import ar.edu.davinci.dvds20212cg1.domain.Negocio;
import ar.edu.davinci.dvds20212cg1.services.NegocioService;
import exception.BusinessException;
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
	 * Listar Negocio por Id
	 */
	@GetMapping(path = "negocio/{sucursalId}")
	public ResponseEntity<Object> getNegocio(@PathVariable Long sucursalId) {
		LOGGER.info("Lista negocio con id: " + sucursalId);

		NegocioResponse negocioResponse = null;
		Negocio negocio = null;
		try {
			negocio = negocioService.findById(sucursalId);
		} catch (BusinessException e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}

		try {
			negocioResponse = mapper.map(negocio, NegocioResponse.class);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<>(negocioResponse, HttpStatus.OK);
	}

	/*
	 * Get Negocios by Fecha de venta
	 */
	@GetMapping(path = "negocio/{sucursalId}/total")
	public List<Negocio> getNegocioVentas(@PathVariable Long sucursalId,
			@RequestParam(required = true, name = "fecha") String date) {
		LOGGER.info("Lista negocio con id: " + sucursalId);
		LOGGER.info("Fecha: " + date);

		DateFormat formatearFecha = new SimpleDateFormat(Constantes.FORMATO_FECHA);
		Date fecha = null;
		
		List<Negocio> negocios = null;
		
		try {
			fecha = formatearFecha.parse(date);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
		
		try {
			negocios = negocioService.calcularGananciaPorDia(fecha).stream().filter(n -> Objects.equals(n.getId(), sucursalId)).collect(Collectors.toList());			
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}		

		LOGGER.info("Ganancias de las ventas del dia");

		return negocios;
	}

	/*
	 * Guardar nuevo negocio
	 * 
	 * @param datosNegocio son los datos para un nuevo negocio
	 * 
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

	/*
	 * Modificar nombre sucursal
	 * 
	 * @param datosNegocio son los datos para modificar negocio
	 * 
	 * @return un nuevo nombre sucursal
	 */
	@PutMapping("/negocio/{sucursalId}")
	public ResponseEntity<Object> updateNegocio(@PathVariable("sucursalId") Long sucursalId,
			@RequestBody NegocioUpdateRequest datosNegocio) {
		Negocio negocioModificar = null;
		Negocio negocioNuevo = null;
		NegocioResponse negocioResponse = null;

		// Convertir NegocioInsertRequest en Cliente
		try {
			negocioNuevo = mapper.map(datosNegocio, Negocio.class);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}

		try {
			negocioModificar = negocioService.findById(sucursalId);
		} catch (BusinessException e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

		if (Objects.nonNull(negocioModificar)) {
			negocioModificar.setSucursal(negocioNuevo.getSucursal());

			// Guardar Negocio nuevo en Negocio modificar
			try {
				negocioModificar = negocioService.update(negocioModificar);
			} catch (BusinessException e) {
				LOGGER.error(e.getMessage());
				e.printStackTrace();

				return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
				e.printStackTrace();

				return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
			}
		} else {
			LOGGER.error("Negocio a modificar es null");

			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

		// Convertir Cliente en ClienteResponse
		try {
			negocioResponse = mapper.map(negocioModificar, NegocioResponse.class);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}

		return new ResponseEntity<>(negocioResponse, HttpStatus.CREATED);
	}
}
