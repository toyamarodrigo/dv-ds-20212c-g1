package ar.edu.davinci.dvds20212cg1.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ar.edu.davinci.dvds20212cg1.domain.Cliente;
import ar.edu.davinci.dvds20212cg1.repository.ClienteRepository;
import exception.BusinessException;

@Service
public class ClienteServiceImpl implements ClienteService {

	private final Logger LOGGER = LoggerFactory.getLogger(ClienteServiceImpl.class);

	private final ClienteRepository clienteRepository;

	@Autowired
	public ClienteServiceImpl(final ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	@Override
	public Cliente save(Cliente cliente) throws BusinessException {
		LOGGER.debug("Guardamos el cliente: " + cliente.toString());
        if (cliente.getId() == null) {
            return clienteRepository.save(cliente);
        }
        throw new BusinessException("No se puede crear el cliente con un id específico.");
	}

	@Override
	public Cliente update(Cliente cliente) throws BusinessException {
		LOGGER.debug("Modificamos el cliente: " + cliente.toString());
        if (cliente.getId() != null) {
            return clienteRepository.save(cliente);
        }
        throw new BusinessException("No se puede modificar un cliente que aún no fue creado.");

	}

	@Override
	public void delete(Cliente cliente) {
		LOGGER.debug("Borrando la cliente con el id: " + cliente.getId());
		clienteRepository.delete(cliente);
	}

	@Override
	public void delete(Long id) {
		LOGGER.debug("Borrando la cliente con el id: " + id);
		clienteRepository.deleteById(id);
	}

	@Override
	public Cliente findById(Long id) throws BusinessException {
		LOGGER.debug("Buscamos al cliente por id: " + id);
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isPresent()) {
            return clienteOptional.get();
        }
        throw new BusinessException("No se encontró el cliente con el id: " + id);
	}

	@Override
	public List<Cliente> list() {
		LOGGER.debug("Listado de todas las clientes");
		return clienteRepository.findAll();
	}

	@Override
	public Page<Cliente> list(Pageable pageable) {
		LOGGER.debug("Listado de todas las clientes paginadas");
		return clienteRepository.findAll(pageable);
	}

	@Override
	public long count() {
		return clienteRepository.count();
	}

}
