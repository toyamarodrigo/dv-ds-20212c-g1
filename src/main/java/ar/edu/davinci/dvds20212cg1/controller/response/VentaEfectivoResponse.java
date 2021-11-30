package ar.edu.davinci.dvds20212cg1.controller.response;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VentaEfectivoResponse {
	private Long id;
	private ClienteResponse cliente;
	private String fecha;
	private List<ItemResponse> items;
	private BigDecimal importeFinal;
}
