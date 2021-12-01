package ar.edu.davinci.dvds20212cg1.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ar.edu.davinci.dvds20212cg1.domain.Negocio;
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

	@Override
	public Negocio save(Negocio negocio) throws BusinessException {
		LOGGER.debug("Guardamos el negocio: " + negocio.toString());
        if (negocio.getId() == null) {
            return negocioRepository.save(negocio);
        }
        throw new BusinessException("No se puede crear el negocio con un id específico.");
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

}
