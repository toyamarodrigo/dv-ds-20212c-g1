package ar.edu.davinci.dvds20212cg1.controller.response;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NegocioResponse {
	private Long id;
	private String sucursal;
	private BigDecimal importeTotal;
}
