package br.com.gft.casadeshowapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.gft.casadeshowapi.domain.Evento;
import br.com.gft.casadeshowapi.repository.EventoRepository;
import br.com.gft.casadeshowapi.service.exceptions.EventoNaoEncontradoException;

@Service
public class EventoService {

	@Autowired
	private EventoRepository eventosRepository;

	public List<Evento> listar() {
		return eventosRepository.findAll();
	}
	
	public List<Evento> listarAsc() {
		Sort sort = Sort.by("nomeEvento").ascending();
		return eventosRepository.findAll(sort);
	}
	
	public List<Evento> listarDesc() {
		Sort sort = Sort.by("nomeEvento").descending();
		return eventosRepository.findAll(sort);
	}
	
	public List<Evento> capacidadeAsc() {
		Sort sort = Sort.by("capacidade").ascending();
		return eventosRepository.findAll(sort);
	}
	
	public List<Evento> capacidadeDesc() {
		Sort sort = Sort.by("capacidade").descending();
		return eventosRepository.findAll(sort);
	}
	
	public List<Evento> dataAsc() {
		Sort sort = Sort.by("data").ascending();
		return eventosRepository.findAll(sort);
	}
	
	public List<Evento> dataDesc() {
		Sort sort = Sort.by("data").descending();
		return eventosRepository.findAll(sort);
	}
	
	public List<Evento> precoAsc() {
		Sort sort = Sort.by("valor").ascending();
		return eventosRepository.findAll(sort);
	}
	
	public List<Evento> precoDesc() {
		Sort sort = Sort.by("valor").descending();
		return eventosRepository.findAll(sort);
	}

	public Evento buscar(Long id) {
		Evento evento = eventosRepository.findById(id).orElse(null);

		if (evento == null) {
			throw new EmptyResultDataAccessException(1);

		}

		return evento;
	}

	public Evento salvar(Evento evento) {
		evento.setId(null);
		return eventosRepository.save(evento);
	}

	public void deletar(Long id) {

		try {
			eventosRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EmptyResultDataAccessException(1);
		}
	}

	public void atualizar(Evento evento) {
		verificarExistencia(evento);
		eventosRepository.save(evento);
	}

	private void verificarExistencia(Evento evento) {
		buscar(evento.getId());
	}

}
