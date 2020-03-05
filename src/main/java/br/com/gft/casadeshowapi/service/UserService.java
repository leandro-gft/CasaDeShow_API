package br.com.gft.casadeshowapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.gft.casadeshowapi.domain.User;
import br.com.gft.casadeshowapi.repository.UserRepository;
import br.com.gft.casadeshowapi.service.exceptions.UsuarioExistenteException;
import br.com.gft.casadeshowapi.service.exceptions.UsuarioNaoEncontradoException;


@Service
public class UserService {

	@Autowired
	private UserRepository usersRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public List<User> listar() {
			return usersRepository.findAll();
	}
	
	public User salvar(User user) {
		if (user.getUsername() !=null) {
			User a  = usersRepository.findByUsername(user.getUsername());
			
			if (a != null) //se a for diferente de null, significa que foi encontrado no banco, ou seja, já existe
				{
				throw new UsuarioExistenteException("Esse usuario já existe.");				
			}			
		}
		
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return usersRepository.save(user);
	}
	
	public User buscar(Long id) {
		User user = usersRepository.findById(id).orElse(null);
		
		if(user ==null) {
			throw new EmptyResultDataAccessException(1);
			
		}
		return user;		
	}
	
		
}
