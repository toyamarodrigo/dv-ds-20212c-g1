package ar.edu.davinci.dvds20212cg1.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ar.edu.davinci.dvds20212cg1.domain.Item;
import ar.edu.davinci.dvds20212cg1.domain.Venta;
import ar.edu.davinci.dvds20212cg1.domain.VentaEfectivo;
import ar.edu.davinci.dvds20212cg1.domain.VentaTarjeta;
import exception.BusinessException;


public interface VentaService {

	// Metodos de creacion de una venta en Efectivo
	public VentaEfectivo save(VentaEfectivo venta) throws BusinessException;

	public VentaEfectivo save(VentaEfectivo ventaEfectivo, Item item) throws BusinessException;

	// Metodos de creacion de una venta con Tarjeta
	public VentaTarjeta save(VentaTarjeta venta) throws BusinessException;

	public VentaTarjeta save(VentaTarjeta ventaTarjeta, Item item) throws BusinessException;

	void delete(Venta venta);

	void delete(Long id);

	// Metodos de busqueda
	Venta findById(Long id) throws BusinessException;

	// Metodos de listado
	List<Venta> list();

	Page<Venta> list(Pageable pageable);

	// Metodo de contador
	long count();

	public Venta addItem(Long id, Item item) throws BusinessException;

	public Venta updateItem(Long ventaId, Long itemId, Item item) throws BusinessException;

	public Venta deleteItem(Long ventaId, Long itemId) throws BusinessException;
}
