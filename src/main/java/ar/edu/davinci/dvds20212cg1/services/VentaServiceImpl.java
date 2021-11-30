package ar.edu.davinci.dvds20212cg1.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ar.edu.davinci.dvds20212cg1.domain.Cliente;
import ar.edu.davinci.dvds20212cg1.domain.Item;
import ar.edu.davinci.dvds20212cg1.domain.Prenda;
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
	private final ClienteService clienteService;
	private final PrendaService prendaService;
	private final ItemService itemService;

	@Autowired
	public VentaServiceImpl(final VentaRepository ventaRepository,
			final VentaEfectivoRepository ventaEfectivoRepository, 
			final VentaTarjetaRepository ventaTarjetaRepository,
			final ClienteService clienteService,
			final ItemService itemService,
			final PrendaService prendaService) {
		this.ventaEfectivoRepository = ventaEfectivoRepository;
		this.ventaRepository = ventaRepository;
		this.ventaTarjetaRepository = ventaTarjetaRepository;
		this.clienteService = clienteService;
		this.prendaService = prendaService;
		this.itemService = itemService;
	}

	private Venta getVenta(Long ventaId) throws BusinessException {
		Optional<Venta> ventaOptional = ventaRepository.findById(ventaId);
		if (ventaOptional.isPresent()) {
			return ventaOptional.get();
		} else {
			throw new BusinessException("Venta no encontrada para el id: " + ventaId);
		}
	}

	private Prenda getPrenda(Item requestItem) throws BusinessException {
		if (requestItem.getPrenda().getId() != null) {
			return prendaService.findById(requestItem.getPrenda().getId());
		} else {
			throw new BusinessException("La Prenda es obligatoria");
		}
	}

	private List<Item> getItems(List<Item> requestItems) throws BusinessException {
		List<Item> items = new ArrayList<Item>();
		for (Item requestItem : requestItems) {

			Prenda prenda = getPrenda(requestItem);
			Item item = Item.builder().cantidad(requestItem.getCantidad()).prenda(prenda).build();
			items.add(item);
		}
		return items;
	}

	private Item getItem(Long id) throws BusinessException {
		return itemService.findById(id);
	}

	private Cliente getCliente(Long id) throws BusinessException {
		return clienteService.findById(id);
	}

	@Override
	public VentaEfectivo save(VentaEfectivo venta) throws BusinessException {
		LOGGER.debug("Guardado de Venta en Efectivo");
		Cliente cliente = null;
		if (venta.getCliente().getId() != null) {
			cliente = getCliente(venta.getCliente().getId());
		} else {
			throw new BusinessException("El cliente es obligatorio");
		}

		List<Item> items = new ArrayList<Item>();
		if (venta.getItems() != null) {
			items = getItems(venta.getItems());
		}

		venta = VentaEfectivo.builder().cliente(cliente).fecha(Calendar.getInstance().getTime()).items(items).build();

		return ventaEfectivoRepository.save(venta);
	}

	@Override
	public VentaEfectivo save(VentaEfectivo ventaEfectivo, Item item) throws BusinessException {
		LOGGER.debug("Guardado de Venta en Efectivo");
		ventaEfectivo.addItem(item);
		return ventaEfectivoRepository.save(ventaEfectivo);
	}

	@Override
	public VentaTarjeta save(VentaTarjeta venta) throws BusinessException {
		LOGGER.debug("Guardado de Venta con Tarjeta");
		Cliente cliente = null;
		if (venta.getCliente().getId() != null) {
			cliente = getCliente(venta.getCliente().getId());
		} else {
			throw new BusinessException("El cliente es obligatorio");
		}

		List<Item> items = new ArrayList<Item>();
		if (venta.getItems() != null) {
			items = getItems(venta.getItems());
		}

		venta = VentaTarjeta.builder().cliente(cliente).fecha(Calendar.getInstance().getTime()).items(items).build();

		return ventaTarjetaRepository.save(venta);
	}

	@Override
	public VentaTarjeta save(VentaTarjeta ventaTarjeta, Item item) throws BusinessException {
		LOGGER.debug("Guardado de Venta con Tarjeta");
		ventaTarjeta.addItem(item);
		return ventaTarjetaRepository.save(ventaTarjeta);
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
		LOGGER.debug("Busqueda de venta por ID");

		Optional<Venta> itemOptional = ventaRepository.findById(id);
		if (itemOptional.isPresent()) {
			return itemOptional.get();
		}

		throw new BusinessException("No se encontra la venta con id: " + id);
	}

	@Override
	public List<Venta> list() {
		LOGGER.debug("Listado de ventas");
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
		Venta venta = getVenta(id);

		Prenda prenda = getPrenda(item);
		Item newItem = Item.builder().cantidad(item.getCantidad()).prenda(prenda).venta(venta).build();

		newItem = itemService.save(newItem);
		venta.addItem(newItem);

		return venta;
	}

	@Override
	public Venta updateItem(Long ventaId, Long itemId, Item item) throws BusinessException {
		Venta venta = getVenta(ventaId);

		Item actualItem = getItem(itemId);
		actualItem.setCantidad(item.getCantidad());
		actualItem = itemService.update(actualItem);

		return venta;
	}

	@Override
	public Venta deleteItem(Long ventaId, Long itemId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
