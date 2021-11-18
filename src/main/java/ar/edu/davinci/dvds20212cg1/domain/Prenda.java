package ar.edu.davinci.dvds20212cg1.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Config inicial JPA
@Entity
@Table(name = "prendas")

// Config Lombok
@NoArgsConstructor // Agrega constructor
@AllArgsConstructor // Agrega constructor con todos los params
@Data // Agrega todos los setter & getter
@Builder // Crea una inner Class PrendaBuilder con un method build()
public class Prenda implements Serializable {
	
	private static final long serialVersionUID = 8307387603457708504L;

	// Config JPA de la PJ de la tabla
	@Id
	// Config JPA de la manera en que se generan los IDs autogenerados
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	// Config JPA del nombre de la columna
	@Column(name = "prd_id")
	private Long id;

	@Column(name = "prd_descripcion", nullable = false)
	private String descripcion;

	@Column(name = "prd_tipo_prenda")
	@Enumerated(EnumType.STRING)
	private TipoPrenda tipo;

	@Column(name = "prd_precio_base")
	private BigDecimal precioBase;
	
	public BigDecimal getPrecioFinal() {
		return precioBase;
	}
}