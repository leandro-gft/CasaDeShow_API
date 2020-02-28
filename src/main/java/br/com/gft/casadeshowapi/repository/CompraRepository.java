package br.com.gft.casadeshowapi.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gft.casadeshowapi.domain.Compra;
import br.com.gft.casadeshowapi.domain.User;


	public interface CompraRepository extends JpaRepository<Compra, Long> {
			List<Compra> findByUser(User user);
	}
