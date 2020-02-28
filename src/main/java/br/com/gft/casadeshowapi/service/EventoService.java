package br.com.gft.casadeshowapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.gft.casadeshowapi.domain.Evento;
import br.com.gft.casadeshowapi.repository.EventoRepository;
//import br.com.gft.casadeshowapi.repository.filter.EventoFilter;
import br.com.gft.casadeshowapi.service.exceptions.EventoNaoEncontradoException;

@Service
public class EventoService {

	@Autowired
	private EventoRepository eventosRepository;

	public List<Evento> listar() {
		return eventosRepository.findAll();
	}
	
//	public List<Evento> pesquisar(EventoFilter eventoFilter) {
//		return eventosRepository.filtrar(eventoFilter);
//	}

	public Evento buscar(Long id) {
		Evento evento = eventosRepository.findById(id).orElse(null);

		if (evento == null) {
			throw new EventoNaoEncontradoException("O evento não pôde ser encontrado.");

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
			throw new EventoNaoEncontradoException("O evento não pôde ser encontrado.");
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
