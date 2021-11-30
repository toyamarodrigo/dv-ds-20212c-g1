package ar.edu.davinci.dvds20212cg1.controller.response;

import java.util.List;

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
	private List<VentaResponse> ventas;
}
