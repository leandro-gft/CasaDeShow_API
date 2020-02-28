package br.com.gft.casadeshowapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gft.casadeshowapi.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
		default List<Role> findAllByUserId(Long id) {
	
		return null;
	}
}
