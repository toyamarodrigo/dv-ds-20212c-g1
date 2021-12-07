package ar.edu.davinci.dvds20212cg1.services;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ar.edu.davinci.dvds20212cg1.domain.Negocio;
import ar.edu.davinci.dvds20212cg1.domain.Venta;
import ar.edu.davinci.dvds20212cg1.repository.NegocioRepository;
import exception.BusinessException;

@Service
public class NegocioServiceImpl implements NegocioService {

	private final Logger LOGGER = LoggerFactory.getLogger(ClienteServiceImpl.class);

	private final NegocioRepository negocioRepository;

	@Autowired
	public NegocioServiceImpl(final NegocioRepository negocioRepository) {
		this.negocioRepository = negocioRepository;
	}

//	private List<Venta> listAllByDate(Date date, Negocio negocio) {
//		return negocio.getVentas().stream().filter(v -> v.getFecha().equals(date)).collect(java.util.stream.Collectors.toList());
//	}

	@Override
	public Negocio save(Negocio negocio) throws BusinessException {
		LOGGER.debug("Guardamos el negocio: " + negocio.toString());
		if (negocio.getId() == null) {
			return negocioRepository.save(negocio);
		}
		throw new BusinessException("No se puede crear el negocio con un id específico.");
	}
	
	@Override
	public Negocio update(Negocio negocio) throws BusinessException {
		LOGGER.debug("Modificamos el negocio: " + negocio.toString());
        if (negocio.getId() != null) {
            return negocioRepository.save(negocio);
        }
        throw new BusinessException("No se puede modificar un negocio que aún no fue creado.");
	}

	@Override
	public List<Negocio> list() {
		LOGGER.debug("Listado de todos los negocios");
		return negocioRepository.findAll();
	}

	@Override
	public Page<Negocio> list(Pageable pageable) {
		LOGGER.debug("Listado de todos los negocios paginados");
		return negocioRepository.findAll(pageable);
	}

	@Override
	public void delete(Negocio negocio) {
		LOGGER.debug("Borro negocio");
		negocioRepository.delete(negocio);
	}

	@Override
	public void delete(Long id) {
		LOGGER.debug("Borro negocio con id: " + id);
		negocioRepository.deleteById(id);
	}

	@Override
	public Negocio findById(Long id) throws BusinessException {
		LOGGER.debug("Buscamos al negocio por id: " + id);
		Optional<Negocio> negocioOptional = negocioRepository.findById(id);
		if (negocioOptional.isPresent()) {
			return negocioOptional.get();
		}
		throw new BusinessException("No se encontró el negocio con el id: " + id);
	}

//	@Override
//	public List<Negocio> calcularGananciaPorDia(Date date) {
//
//		List<Negocio> negocios = negocioRepository.findAll();
//
//		for(Negocio negocio : negocios) {
//			List<Venta> ventas = listAllByDate(date, negocio);
//			LOGGER.info("ventas" + ventas);
//			BigDecimal ganancia = BigDecimal.ZERO;
//			LOGGER.info("ganancia: " + ganancia.toString());
//
//			for(Venta venta : ventas) {
//				LOGGER.info("importe: " + venta.importeBruto().toString());
//				ganancia = ganancia.add(venta.importeFinal());
//			}

//			negocio.setGanancia(ganancia);
//		}
//		return negocios;
//	}
}
