package br.com.apex.api.colaboradores.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.apex.api.colaboradores.model.ColaboradorModel;

public interface ColaboradorRepository extends JpaRepository<ColaboradorModel, Integer> {
	
	ColaboradorModel findByEmail(String email);
}
