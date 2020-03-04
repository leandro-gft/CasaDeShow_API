package br.com.gft.casadeshowapi.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gft.casadeshowapi.domain.Casa;

public interface CasaRepository extends JpaRepository<Casa, Long> {

	Casa findByNomeCasa(String nomeCasa);

	
}
