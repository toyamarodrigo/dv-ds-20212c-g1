package ar.edu.davinci.dvds20212cg1.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clientes")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Cliente implements Serializable {

	private static final long serialVersionUID = -8023825270886896322L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "S_CLIENTES")
	@Column(name = "cli_id")
	private Long id;

	@Column(name = "cli_nombre")
	@NotNull
	@Size(min = 2, max = 30)
	private String nombre;

	@Column(name = "cli_apellido")
	@NotNull
	@Size(min = 2, max = 30)
	private String apellido;

	public String getRazonSocial() {
		return nombre + " " + apellido;
	}
}
