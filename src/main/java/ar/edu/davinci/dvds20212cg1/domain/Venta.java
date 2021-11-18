package ar.edu.davinci.dvds20212cg1.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "ventas")
@DiscriminatorColumn(name = "tipo_venta")
@Inheritance(strategy = InheritanceType.JOINED)

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
public abstract class Venta implements Serializable {

	private static final long serialVersionUID = 9127287652493802232L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "vta_id")
	private Long id;

	@ManyToOne(targetEntity = Cliente.class, cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinColumn(name = "vta_cli_id", referencedColumnName = "cli_id", nullable = false)
	private Cliente cliente;

	@Column(name = "vta_fecha")
	@Temporal(TemporalType.DATE)
	private Date fecha;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "venta", cascade = CascadeType.PERSIST, orphanRemoval = true)
	@JsonManagedReference
	private List<Item> items;

	public abstract Double conRecargo(Double importeBase);

	public String getRazonSocial() {
		return cliente.getRazonSocial();
	}

	public BigDecimal importeBruto() {
		Double suma = items.stream().collect(Collectors.summingDouble(item -> item.importe().doubleValue()));
		return new BigDecimal(suma).setScale(2, RoundingMode.UP);
	}

	// Template Method
	public BigDecimal importeFinal() {
		Double suma = items.stream()
				.collect(Collectors.summingDouble(item -> conRecargo(item.importe().doubleValue())));
		return new BigDecimal(suma).setScale(2, RoundingMode.UP);
	}

	public String getImporteFinalStr() {
		return importeFinal().toString();
	}

	public boolean esDeFecha(Date fecha) {
		return (this.fecha.compareTo(fecha) == 0) ? true : false;
	}

	public void addItem(Item item) {
		if (this.items == null) {
			this.items = new ArrayList<Item>();
		}
		this.items.add(item);
	}

	public String getFormatoFecha() {
		DateFormat formatearFecha = new SimpleDateFormat("dd-MM-yyyy");
		return formatearFecha.format(getFecha());
	}

}
