package br.com.senai.manutencaosenaiapi.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.com.senai.manutencaosenaiapi.entity.TipoPeca;
import br.com.senai.manutencaosenaiapi.repository.TipoPecasRepository;

@Service
@Validated
public class TipoPecaService {
	
	@Autowired
	private TipoPecasRepository repository;

	public TipoPeca inserir(
			@Valid
			@NotNull(message = "O tipo da peça não pode ser nula")
			TipoPeca novaPeca) {
		TipoPeca pecaSalva = repository.save(novaPeca);
		return pecaSalva;
	}
	
	public TipoPeca alterar(
			@Valid 
			@NotNull(message = "O tipo da peça não pode ser nula")
			TipoPeca pecaSalva) {
		TipoPeca pecaAtualizada = repository.save(pecaSalva);
		return pecaAtualizada;
	}
	
	public void removerPor(
			@NotNull(message = "O id do tipo da peça para remoção não pode ser nulo")
			@Min(value = 1, message = "O id do tipo da peça deve ser maior que zero")
			Integer id) {
		this.repository.deleteById(id);
	}
	
	public List<TipoPeca> listarPor(
			@NotEmpty(message = "A descrição da busca é obrigatória")
			@NotBlank(message = "A descrição não pode conter espaço em branco")
			String descricao){
		return repository.listarPor("%" + descricao + "%");
	}

}
