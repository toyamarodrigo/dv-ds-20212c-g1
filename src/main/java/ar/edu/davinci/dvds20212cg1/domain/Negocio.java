package ar.edu.davinci.dvds20212cg1.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "negocios")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Negocio implements Serializable {

	private static final long serialVersionUID = 9112683896901479466L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "ngc_id")
	private Long id;
	
	@Column(name = "ngc_sucursal")
	private String sucursal;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "negocio", cascade = CascadeType.PERSIST, orphanRemoval = true   )
	@JsonIgnore
	private List<Venta> ventas;
	
    public BigDecimal calcularGananciaPorDia(Date dia){
    	return ventas.stream().filter(venta -> venta.getFecha() == dia).map(Venta::importeFinal).reduce(BigDecimal::add).get();
    }
    
    public BigDecimal calcularGananciaTotal() {
			if(ventas.stream().map(Venta::importeFinal).reduce(BigDecimal::add).isPresent()) {
				return ventas.stream().map(Venta::importeFinal).reduce(BigDecimal::add).get();
			} else {
				return BigDecimal.ZERO;
			}
		};
}
