package ar.edu.davinci.dvds20212cg1.controller.rest;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.davinci.dvds20212cg1.controller.TiendaAppRest;
import ar.edu.davinci.dvds20212cg1.controller.request.ClienteInsertRequest;
import ar.edu.davinci.dvds20212cg1.controller.request.ClienteUpdateRequest;
import ar.edu.davinci.dvds20212cg1.controller.response.ClienteResponse;
import ar.edu.davinci.dvds20212cg1.domain.Cliente;
import ar.edu.davinci.dvds20212cg1.services.ClienteService;
import exception.BusinessException;
import ma.glasnost.orika.MapperFacade;

@RestController
public class ClienteControllerRest extends TiendaAppRest {
	
	private final Logger LOGGER = LoggerFactory.getLogger(ClienteControllerRest.class);

	@Autowired
	private ClienteService service;
	
	@Autowired
	private MapperFacade mapper;
	
	
	/*
	 * Listar
	 */
	@GetMapping(path = "/clientes/all")
	public List<Cliente> getListAll() {
		LOGGER.info("Listar todos los clientes");
		
		return service.list();
	}
	
	/*
	 * Listar Paginado
	 */
	@GetMapping(path = "/clientes")
	public ResponseEntity<Page<ClienteResponse>> getList(Pageable pageable) {
		LOGGER.info("Listar todos los clientes paginados");
		LOGGER.info("Pageable: " + pageable);
		
		Page<ClienteResponse> clienteResponse = null;
		Page<Cliente> clientes = null;
		
		try {
			clientes = service.list(pageable);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
		
		try {
			clienteResponse = clientes.map(cliente -> mapper.map(cliente, ClienteResponse.class));
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(clienteResponse, HttpStatus.OK);
	}
	
	@GetMapping(path = "clientes/{id}")
	public ResponseEntity<Object> getCliente(@PathVariable Long id) {
		LOGGER.info("Lista cliente con id: " + id);
		
		ClienteResponse clienteResponse = null;
		Cliente cliente = null;
		try {
			cliente = service.findById(id);
		} catch (BusinessException e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
		
		try {
			clienteResponse = mapper.map(cliente, ClienteResponse.class);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<>(clienteResponse, HttpStatus.OK);
	}
	
	/*
	 * Guardar nuevo cliente
	 * 
	 * @param datosCliente son los datos para un nuevo cliente
	 * @return un cliente nuevo
	 */
	@PostMapping(path = "/clientes")
	public ResponseEntity<ClienteResponse> createCliente(@RequestBody ClienteInsertRequest datosCliente) {
		Cliente cliente = null;
		ClienteResponse clienteResponse = null;
		
		// Convertir ClienteInsertRequest en Cliente
		try {
			cliente = mapper.map(datosCliente, Cliente.class);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
		
		// Guardar el nuevo Cliente
        try {
            cliente = service.save(cliente);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
		
		// Convertir en Cliente Response
		try {
			clienteResponse = mapper.map(cliente, ClienteResponse.class);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(clienteResponse, HttpStatus.CREATED);
	}
	
	/*
	 * Modificar datos de un cliente
	 * 
	 * @param id identificador de un cliente
	 * @param datosCliente datos a modificar del cliente
	 * @return los datos de un cliente modificado
	 */
	@PutMapping("/clientes/{id}")
	public ResponseEntity<Object> updateCliente(@PathVariable("id") long id, @RequestBody ClienteUpdateRequest datosCliente) {
		Cliente clienteModificar = null;
		Cliente clienteNuevo = null;
		ClienteResponse clienteResponse = null;
		
		// Convertir ClienteInsertRequest en Cliente
		try {
			clienteNuevo = mapper.map(datosCliente, Cliente.class);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
		
		try {
			clienteModificar = service.findById(id);
		} catch (BusinessException e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		if(Objects.nonNull(clienteModificar)) {
			clienteModificar.setNombre(clienteNuevo.getNombre());
			clienteModificar.setApellido(clienteNuevo.getApellido());
			
			// Guardar Cliente nuevo en Cliente modificar
			
			try {
				clienteModificar = service.update(clienteModificar);
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
			LOGGER.error("Cliente a modificar es null");
			
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		// Convertir Cliente en ClienteResponse
		try {
			clienteResponse = mapper.map(clienteModificar, ClienteResponse.class);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(clienteResponse, HttpStatus.CREATED);
	}
	
	/*
	 * Borrado de cliente
	 * @param id identificador de cliente
	 * @return
	 */
	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<HttpStatus> deleteCliente (@PathVariable("id") Long id) {
		try {
			service.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}
}












