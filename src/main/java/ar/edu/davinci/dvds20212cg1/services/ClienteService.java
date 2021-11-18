package ar.edu.davinci.dvds20212cg1.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ar.edu.davinci.dvds20212cg1.domain.Cliente;
import exception.BusinessException;

public interface ClienteService {
	// Metodos de creacion, modificacion y borrado
	Cliente save(Cliente cliente) throws BusinessException;

	Cliente update(Cliente cliente) throws BusinessException;

	void delete(Cliente cliente);
	
	void delete(Long id);

	// Metodos de busqueda
	Cliente findById(Long id) throws BusinessException;

	// Metodos de listado
	List<Cliente> list();
	Page<Cliente> list(Pageable pageable);

	// Metodo de contador
	long count();
}
