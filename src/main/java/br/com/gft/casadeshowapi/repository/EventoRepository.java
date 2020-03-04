package br.com.gft.casadeshowapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gft.casadeshowapi.domain.Evento;


public interface EventoRepository extends JpaRepository<Evento, Long> {
}
