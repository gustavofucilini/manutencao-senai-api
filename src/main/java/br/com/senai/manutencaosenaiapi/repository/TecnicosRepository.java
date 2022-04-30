package br.com.senai.manutencaosenaiapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.senai.manutencaosenaiapi.entity.Tecnico;

@Repository
public interface TecnicosRepository extends JpaRepository<Tecnico, Integer>{
	
	@Modifying
	@Query(value = "delete from Tecnico t where t.id = :id")
	void deletarPor(Integer id);
	
	@Query(value = "Select t from Tecnico t where Upper(t.nomeCompleto) LIKE Upper(:nome)")
	List<Tecnico> listarPor(@Param("nome") String nome);

}
