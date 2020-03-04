package br.com.gft.casadeshowapi.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@Entity 
@Table(name="users", uniqueConstraints=@UniqueConstraint(columnNames= {"username"}))
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonInclude(Include.NON_NULL)
	private long id;
	
	@Column(unique=true)
	@JsonInclude(Include.NON_NULL)
	@NotNull(message="Campo USERNAME é de preenchimento obrigatório.")
	private String username;

	@NotNull(message="Campo PASSWORD é de preenchimento obrigatório.")
	@JsonInclude(Include.NON_NULL)
    
	private String password;	
		
	@ManyToOne
	private Role role;
	

	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)	
    @JsonIgnore
    private List<Venda> compra;
	
    public List<Venda> getCompra() {
		return compra;
	}
	public void setCompra(List<Venda> compra) {
		this.compra = compra;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
//	public String getConfirmPassword() {
//		return confirmPassword;
//	}
//	public void setConfirmPassword(String confirmPassword) {
//		this.confirmPassword = confirmPassword;
//	}
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
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
	
}
