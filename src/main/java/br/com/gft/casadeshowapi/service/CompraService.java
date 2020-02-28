package br.com.gft.casadeshowapi.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.gft.casadeshowapi.domain.Compra;
import br.com.gft.casadeshowapi.domain.User;
import br.com.gft.casadeshowapi.repository.CompraRepository;
import br.com.gft.casadeshowapi.repository.UserRepository;
import br.com.gft.casadeshowapi.service.exceptions.CompraExistenteException;
import br.com.gft.casadeshowapi.service.exceptions.CompraNaoEncontradaException;

@Service
public class CompraService {

	@Autowired
	private CompraRepository comprasRepository;
	
	@Autowired // injeção de dependencia
	private UserRepository usersRepository;
	
	
	public List<Compra> listar() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = usersRepository.findByUsername(auth.getName());
		return comprasRepository.findByUser(user);

	}
	
	public Compra salvar(Compra compra) {
		if (compra.getId() !=null) {
			Compra a  = comprasRepository.findById(compra.getId()).orElse(null);
			
			if (a != null) //se a for diferente de null, significa que foi encontrado no banco, ou seja, já existe
				{
				throw new CompraExistenteException("Essa compra já existe.");				
			}			
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = usersRepository.findByUsername(auth.getName());
		compra.setUser(user);
		compra.setDataCompra(Calendar.getInstance().getTime());
		return comprasRepository.save(compra);
	}
	
	
	public Compra buscar(Long id) {
		Compra compra = comprasRepository.findById(id).orElse(null);
		
		if(compra ==null) {
			throw new CompraNaoEncontradaException("Essa compra não pôde ser encontrado.");
			
		}
		return compra;		
	}
	
}