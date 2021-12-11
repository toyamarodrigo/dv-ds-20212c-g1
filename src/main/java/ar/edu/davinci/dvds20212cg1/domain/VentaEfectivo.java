package ar.edu.davinci.dvds20212cg1.domain;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@PrimaryKeyJoinColumn(name = "vta_id")
@DiscriminatorValue("EFECTIVO")
@Table(name = "ventas_efectivos")

@Data
@NoArgsConstructor(force = true)
@SuperBuilder
public class VentaEfectivo extends Venta implements Serializable {

	private static final long serialVersionUID = 1393479611369152386L;

	public Double conRecargo(Double importeBase) {
		return importeBase;
	}
}
