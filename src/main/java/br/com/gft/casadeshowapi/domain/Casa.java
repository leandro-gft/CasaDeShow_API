package br.com.gft.casadeshowapi.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@Entity
public class Casa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonInclude(Include.NON_NULL)
	private Long id;
	
	@JsonInclude(Include.NON_NULL)
	@NotNull(message="Campo NOME DA CASA DE SHOW é de preenchimento obrigatório.")
	private String nomeCasa;
	
	@JsonInclude(Include.NON_NULL)
	@NotNull(message="Campo LOCAL DA CASA DE SHOW é de preenchimento obrigatório.")
	private String localCasa;
	
	@JsonInclude(Include.NON_EMPTY)
	@OneToMany(mappedBy="casa")
	private List<Comentario> comentarios;
	
	@JsonInclude(Include.NON_EMPTY)
	@OneToMany(mappedBy="casa", cascade=javax.persistence.CascadeType.ALL) 
	@JsonIgnore
	private List<Evento> evento;
	
	public List<Evento> getEvento() {
		return evento;
	}
	public void setEvento(List<Evento> evento) {
		this.evento = evento;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomeCasa() {
		return nomeCasa;
	}
	public void setNomeCasa(String nomeCasa) {
		this.nomeCasa = nomeCasa;
	}
	public String getLocalCasa() {
		return localCasa;
	}
	public void setLocalCasa(String localCasa) {
		this.localCasa = localCasa;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Casa other = (Casa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
