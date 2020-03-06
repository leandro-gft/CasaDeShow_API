package br.com.gft.casadeshowapi.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;


@Entity
public class Evento {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@ApiModelProperty(value="ID de um evento",example="1")
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	@ApiModelProperty(value="Gênero do evento",example="POP")
	private Genero genero;
	
	@NotNull
	@Size(min=3, max=30)
	@ApiModelProperty(value="Nome do Evento",example="Show do Queen")
	private String nomeEvento;
	
	@NotNull
	@Positive
	@ApiModelProperty(value="Quantidade máxima de pessoas que cabem no evento",example="1000")
	private Integer capacidade;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	@NotNull
	@Future
	@ApiModelProperty(value="Data em que acontecerá o evento",example="02/02/2022")
	private LocalDate data;
	
	@NumberFormat(pattern="#,##0.00")
	@NotNull
	@ApiModelProperty(value="Preço do ingresso",example="100.00")
	private BigDecimal valor;
	
	@ManyToOne
	@NotNull
	@ApiModelProperty(value="Casa de show onde acontecerá o evento",example="'casa':{'id':'1'}")
	private Casa casa;
	
	@OneToMany(mappedBy="evento", cascade=javax.persistence.CascadeType.ALL) 
	private List<Venda> venda;
	@JsonIgnore
	public List<Venda> getVenda() {
		return venda;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}

	public void setVenda(List<Venda> venda) {
		this.venda = venda;
	}

	public Casa getCasa() {
		return casa;
	}
	public void setCasa(Casa casa) {
		this.casa = casa;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCapacidade() {
		return capacidade;
	}
	public void setCapacidade(Integer capacidade) {
		this.capacidade = capacidade;
	}
	
	public BigDecimal getValor() {
		return valor;
	} 
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Evento other = (Evento) obj;
		if (id != other.id)
			return false;
		return true;
	}
	public String getNomeEvento() {
		return nomeEvento;
	}
	public void setNomeEvento(String nomeEvento) {
		this.nomeEvento = nomeEvento;
	}
	
	public Genero getGenero() {
		return genero;
	}
	public void setGenero(Genero genero) {
		this.genero = genero;
	}

}


