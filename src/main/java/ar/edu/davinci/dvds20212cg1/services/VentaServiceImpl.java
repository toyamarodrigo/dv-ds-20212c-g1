package ar.edu.davinci.dvds20212cg1.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ar.edu.davinci.dvds20212cg1.domain.Item;
import ar.edu.davinci.dvds20212cg1.domain.Venta;
import ar.edu.davinci.dvds20212cg1.domain.VentaEfectivo;
import ar.edu.davinci.dvds20212cg1.domain.VentaTarjeta;
import ar.edu.davinci.dvds20212cg1.repository.VentaEfectivoRepository;
import ar.edu.davinci.dvds20212cg1.repository.VentaRepository;
import ar.edu.davinci.dvds20212cg1.repository.VentaTarjetaRepository;
import exception.BusinessException;

@Service
public class VentaServiceImpl implements VentaService {

	private final Logger LOGGER = LoggerFactory.getLogger(VentaServiceImpl.class);

	private final VentaRepository ventaRepository;
	private final VentaEfectivoRepository ventaEfectivoRepository;
	private final VentaTarjetaRepository ventaTarjetaRepository;

	@Autowired
	public VentaServiceImpl(final VentaRepository ventaRepository,
			final VentaEfectivoRepository ventaEfectivoRepository,
			final VentaTarjetaRepository ventaTarjetaRepository) {
		this.ventaEfectivoRepository = ventaEfectivoRepository;
		this.ventaRepository = ventaRepository;
		this.ventaTarjetaRepository = ventaTarjetaRepository;
	}

	@Override
	public VentaEfectivo save(VentaEfectivo venta) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VentaEfectivo save(VentaEfectivo ventaEfectivo, Item item) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VentaTarjeta save(VentaTarjeta venta) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VentaTarjeta save(VentaTarjeta ventaTarjeta, Item item) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Venta update(Venta venta) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Venta venta) {
		LOGGER.debug("Borrando la venta con el id: " + venta.getId());
		ventaRepository.delete(venta);
	}

	@Override
	public void delete(Long id) {
		LOGGER.debug("Borrando la venta con el id: " + id);
		ventaRepository.deleteById(id);
	}

	@Override
	public Venta findById(Long id) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Venta> list() {
		LOGGER.debug("Listado de prendas");
		return ventaRepository.findAll();
	}

	@Override
	public Page<Venta> list(Pageable pageable) {
		LOGGER.debug("Listado de ventas paginadas");
		return ventaRepository.findAll(pageable);
	}

	@Override
	public long count() {
		return ventaRepository.count();
	}

	@Override
	public Venta addItem(Long id, Item item) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Venta updateItem(Long ventaId, Long itemId, Item item) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Venta deleteItem(Long ventaId, Long itemId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
