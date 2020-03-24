package br.com.gft.casadeshowapi.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gft.casadeshowapi.domain.Casa;

public interface CasaRepository extends JpaRepository<Casa, Long> {

	List<Casa> findByNomeCasaContaining(String nomeCasa);
	Casa findByNomeCasa(String nomeCasa);

	
}
