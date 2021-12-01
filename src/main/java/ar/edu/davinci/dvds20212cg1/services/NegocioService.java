package ar.edu.davinci.dvds20212cg1.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ar.edu.davinci.dvds20212cg1.domain.Negocio;
import exception.BusinessException;

public interface NegocioService {
	// Metodos de creacion
	Negocio save(Negocio negocio) throws BusinessException;

	// Metodos de listado
	List<Negocio> list();

	Page<Negocio> list(Pageable pageable);
}
