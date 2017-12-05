package br.com.alg.oficinaspringframework.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alg.oficinaspringframework.model.Vinho;

public interface VinhosRepository extends JpaRepository<Vinho, Long>{

	
}
