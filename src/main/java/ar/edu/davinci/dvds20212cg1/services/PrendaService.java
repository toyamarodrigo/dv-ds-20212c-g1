package ar.edu.davinci.dvds20212cg1.services;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import ar.edu.davinci.dvds20212cg1.domain.Prenda;
import exception.BusinessException;

public interface PrendaService {

	// Metodos de creacion, modificacion y borrado
	Prenda save(Prenda prenda) throws BusinessException;

	Prenda update(Prenda prenda) throws BusinessException;

	void delete(Prenda prenda);

	void delete(Long id);

	// Metodos de busqueda
	Prenda findById(Long id) throws BusinessException;

	// Metodos de listado
	List<Prenda> list();

	Page<Prenda> list(Pageable pageable);

	// Metodo de contador
	long count();
}
