package com.carros.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

//Crud = Create, restart, update, delete
public interface CarroRepository extends JpaRepository<Carro, Long>{

	List<Carro> findByTipo(String tipo);

}
