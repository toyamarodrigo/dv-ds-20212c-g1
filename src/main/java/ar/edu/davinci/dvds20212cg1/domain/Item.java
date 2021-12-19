package ar.edu.davinci.dvds20212cg1.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

@Entity
@Table(name = "venta_items")

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Item implements Serializable {

	private static final long serialVersionUID = 4755409522124663455L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@Column(name = "itm_id")
	@GenericGenerator(name = "native", strategy = "native")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "itm_vta_id", referencedColumnName = "vta_id", nullable = false)
	@JsonBackReference
	private Venta venta;

	@Column(name = "itm_cantidad")
	private Integer cantidad;

	@ManyToOne
	@JoinColumn(name = "itm_prd_id", referencedColumnName = "prd_id", nullable = false)
	private Prenda prenda;

	public BigDecimal importe() {
		return prenda.getPrecioFinal().multiply(new BigDecimal(cantidad));
	}
}
