package ar.edu.davinci.dvds20212cg1.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VentaEfectivoInsertRequest {
	private Long sucursalId;
}
