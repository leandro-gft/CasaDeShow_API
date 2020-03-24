package br.com.gft.casadeshowapi.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;

//@ApiModel(value="Casa", description="Representa uma casa de show")
@Entity
public class Casa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(value="ID da casa de show",example="1")
	private Long id;
	
	@ApiModelProperty(value="Nome da casa de show",example="Teatro Municipal")
	@NotNull
	@Size(min=3, max=30)
	private String nomeCasa;
	
	@ApiModelProperty(value="Endereço da casa de show",example="Barueri")
	@NotNull
	@Size(min=3, max=50)
	private String localCasa;
	
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
	
	public Casa () {}
	
	public Casa (String nomeCasa, String localCasa) {
		this.nomeCasa = nomeCasa;
		this.localCasa = localCasa; 
		
	}
	
}
