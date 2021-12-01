package ar.edu.davinci.dvds20212cg1.controller.response;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VentaTarjetaResponse extends VentaResponse {
	private Integer cantidadCuotas;
	private BigDecimal coeficienteTarjeta;
}
