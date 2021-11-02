package ar.edu.davinci.dvds20212cg1.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class PrendaTest {
	@Test
	void testBuilder() {
		Long id = 1L;
		String descripcion = "Camisa";
		TipoPrenda tipo = TipoPrenda.CAMISA;
		BigDecimal precioBase = new BigDecimal(10);

		// When
		Prenda prenda = Prenda.builder().id(id).descripcion(descripcion).tipo(tipo).precioBase(precioBase).build();
		
		// Then
		assertNotNull(prenda);
		assertEquals(id, prenda.getId());
		assertEquals(descripcion, prenda.getDescripcion());
		assertEquals(tipo, prenda.getTipo());
		assertEquals(precioBase, prenda.getPrecioBase());
	}
}
