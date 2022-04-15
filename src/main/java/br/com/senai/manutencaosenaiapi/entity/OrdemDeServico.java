package br.com.senai.manutencaosenaiapi.entity;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OrdemDeServico {
	
	@EqualsAndHashCode.Include
	private Integer id;
	@NotNull(message = "O cliente da ordem não pode ser nulo")
	private Cliente cliente;
	@NotNull(message = "O técnico da order não pode ser nulo")
	private Tecnico tecnico;
	@NotNull(message = "A data de abertura e obrigatoria")
	@PastOrPresent(message = "A data de abertura não pode ser posterior a data atual")
	private LocalDate dataDeAbertura;
	@PastOrPresent(message = "A data de encerramento não pode ser posterior a data atual")
	private LocalDate dataDeDateEncerramento;
	@NotEmpty(message = "A descrição do problema e obrigatorio")
	@NotBlank(message = "A descrição do problema não foi informada")
	private String descricaoDoProblema;
	private String descricaoDoReparo;
	@NotEmpty(message = "Deve haver ao menos uma peça de reparo")
	private List<Peca> pecasDoReparo;

}
