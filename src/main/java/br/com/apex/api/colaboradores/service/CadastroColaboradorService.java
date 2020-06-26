package br.com.apex.api.colaboradores.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.apex.api.colaboradores.exception.NegocioException;
import br.com.apex.api.colaboradores.model.ColaboradorModel;
import br.com.apex.api.colaboradores.repository.ColaboradorRepository;

@Service
public class CadastroColaboradorService {

	@Autowired
	private ColaboradorRepository colaboradorRepository;

	public ColaboradorModel salvar(ColaboradorModel colaboradorId) {

		ColaboradorModel colaboradorExistente = colaboradorRepository.findByEmail(colaboradorId.getEmail());

		if (colaboradorExistente != null && !colaboradorExistente.equals(colaboradorId)) {
			throw new NegocioException("já existe um cliente cadastrado com este email.");

		}
		return colaboradorRepository.save(colaboradorId);

	}
	
	public void excluir(Integer colaboradorId) {
		colaboradorRepository.deleteById(colaboradorId);
		
	}
}
