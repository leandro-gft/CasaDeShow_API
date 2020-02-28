package br.com.gft.casadeshowapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.gft.casadeshowapi.domain.Casa;
import br.com.gft.casadeshowapi.repository.CasaRepository;
import br.com.gft.casadeshowapi.service.exceptions.CasaExistenteException;
import br.com.gft.casadeshowapi.service.exceptions.CasaNaoEncontradaException;

@Service
public class CasaService {

	@Autowired
	private CasaRepository casasRepository;
	
	
	public List<Casa> listar() {
		return casasRepository.findAll();

	}
	
	public Casa salvar(Casa casa) {
		if (casa.getId() !=null) {
			Casa a  = casasRepository.findById(casa.getId()).orElse(null);
			
			if (a != null) //se a for diferente de null, significa que foi encontrado no banco, ou seja, já existe
				{
				throw new CasaExistenteException("Essa casa já existe.");				
			}			
		}
		return casasRepository.save(casa);
	}
	
	public Casa buscar(Long id) {
		Casa casa = casasRepository.findById(id).orElse(null);
		
		if(casa ==null) {
			throw new CasaNaoEncontradaException("Essa casa não pôde ser encontrado.");
			
		}
		return casa;		
	}

	public void deletar(Long id) {

		try {
			casasRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new CasaNaoEncontradaException("A casa de show não pôde ser encontrado.");
		}
	}

	public void atualizar(Casa casa) {
		verificarExistencia(casa);
		casasRepository.save(casa);
	}

	private void verificarExistencia(Casa casa) {
		buscar(casa.getId());
	}
}