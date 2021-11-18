package ar.edu.davinci.dvds20212cg1.services;

import java.util.List;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cliente update(Cliente cliente) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
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
