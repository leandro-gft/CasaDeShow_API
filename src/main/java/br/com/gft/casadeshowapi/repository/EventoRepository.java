package br.com.gft.casadeshowapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gft.casadeshowapi.domain.Evento;
//import br.com.gft.casadeshowapi.repository.evento.EventoRepositoryQuery;


public interface EventoRepository extends JpaRepository<Evento, Long>{ //, EventoRepositoryQuery {


}
