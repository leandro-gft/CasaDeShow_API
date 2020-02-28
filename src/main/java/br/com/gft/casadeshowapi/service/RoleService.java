package br.com.gft.casadeshowapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gft.casadeshowapi.domain.Role;
import br.com.gft.casadeshowapi.repository.RoleRepository;


@Service
public class RoleService {

	@Autowired
	private RoleRepository rolesRepository;
	
	public List<Role> listar() {
			return rolesRepository.findAll();
	}
	
	public Role salvar(Role role) {
		return rolesRepository.save(role);
	}
	
	public Role buscar(Long id) {
		Role role = rolesRepository.findById(id).orElse(null);
		return role;
		
	}
		
}
