package br.com.gft.casadeshowapi.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.gft.casadeshowapi.domain.Venda;
import br.com.gft.casadeshowapi.domain.User;
import br.com.gft.casadeshowapi.repository.VendaRepository;
import br.com.gft.casadeshowapi.repository.UserRepository;
import br.com.gft.casadeshowapi.service.exceptions.CompraExistenteException;
import br.com.gft.casadeshowapi.service.exceptions.CompraNaoEncontradaException;

@Service
public class VendaService {

	@Autowired
	private VendaRepository vendasRepository;
	
	@Autowired // injeção de dependencia
	private UserRepository usersRepository;
	
	
	public List<Venda> listar() {
		return vendasRepository.findAll();

	}
	
	public Venda salvar(Venda venda) {
		if (venda.getId() !=null) {
			Venda a  = vendasRepository.findById(venda.getId()).orElse(null);
			
			if (a != null) //se a for diferente de null, significa que foi encontrado no banco, ou seja, já existe
				{
				throw new CompraExistenteException("Essa compra já existe.");				
			}			
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = usersRepository.findByUsername(auth.getName());
		venda.setUser(user);
		venda.setDataCompra(Calendar.getInstance().getTime());
		return vendasRepository.save(venda);
	}
	
	
	public Venda buscar(Long id) {
		Venda venda = vendasRepository.findById(id).orElse(null);
		
		if(venda ==null) {
			throw new EmptyResultDataAccessException(1);
			
		}
		return venda;		
	}
	
}