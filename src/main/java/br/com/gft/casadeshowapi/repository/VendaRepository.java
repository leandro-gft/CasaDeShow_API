package br.com.gft.casadeshowapi.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gft.casadeshowapi.domain.Venda;
import br.com.gft.casadeshowapi.domain.User;


public interface VendaRepository extends JpaRepository<Venda, Long> {
		
}
