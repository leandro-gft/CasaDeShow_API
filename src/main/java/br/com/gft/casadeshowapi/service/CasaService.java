package br.com.gft.casadeshowapi.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
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

	public List<Casa> listarasc() {
		Sort sort = Sort.by("nomeCasa").ascending();
		return casasRepository.findAll(sort);
	}

	public List<Casa> listardesc() {
		Sort sort = Sort.by("nomeCasa").descending();
		return casasRepository.findAll(sort);
	}

	public Casa salvar(Casa casa) {
		if (casa.getId() != null) {
			Casa a = casasRepository.findById(casa.getId()).orElse(null);
			if (a != null) // se a for diferente de null, significa que foi encontrado no banco, ou seja,
										// já existe
			{
				throw new CasaExistenteException("Essa casa já existe.");
			}
		} else {
			if (casa.getNomeCasa() != null) {
				Casa b = casasRepository.findByNomeCasa(casa.getNomeCasa());
				if (b != null) // se a for diferente de null, significa que foi encontrado no banco, ou seja,
								// já existe
				{
					throw new CasaExistenteException("Essa casa já existe.");
				}
			}
		}
		return casasRepository.save(casa);
	}

	public Casa buscar(Long id) {
		Casa casa = casasRepository.findById(id).orElse(null);

		if (casa == null) {
			throw new EmptyResultDataAccessException(1);

		}
		return casa;
	}

	public Casa buscarPorNome(String nomeCasa) {
		Casa casa = casasRepository.findByNomeCasa(nomeCasa);

		if (casa == null) {
			throw new EmptyResultDataAccessException(1);

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