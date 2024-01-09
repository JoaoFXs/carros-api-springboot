package com.carros.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.carros.domain.dto.CarroDTO;

import lombok.Data;


@Service
public class CarroService {
	@Autowired
	private CarroRepository rep;
	
	public List<CarroDTO> getCarros(){
		List<Carro> carros = rep.findAll();
		
		List<CarroDTO> list = new ArrayList<>();
		
		for (Carro c : carros) {
			list.add(CarroDTO.create(c));
		}
		return list;
	}
    public CarroDTO getCarroById(Long id) {
        Optional<Carro> carro = rep.findById(id);
        return carro.map(CarroDTO::create).orElseThrow(() -> new ObjectNotFoundException("Carro não encontrado", null));
    }


	public List<CarroDTO> getCarrosByTipo(String tipo) {
		// TODO Auto-generated method stub
		List<Carro> carros = rep.findByTipo(tipo);
		
		List<CarroDTO> list = new ArrayList<>();
		
		for (Carro c : carros) {
			list.add(CarroDTO.create(c));
		}
		return list;
	
	}
	
	
	public CarroDTO insert(Carro carro) {
		Assert.isNull(carro.getId());
		
		return CarroDTO.create(rep.save(carro));
	}
	
	public Carro save(Carro carro) {
		return rep.save(carro);
		
	}

	public CarroDTO update(Carro carro, Long id) {
		//Verifica se o id informado não é nulo
		Assert.notNull(id, "Não foi possível atualizar o registro");
		//Busca o carro no banco de dados
		Optional<Carro> optional = rep.findById(id);
		if(optional.isPresent()) {
			Carro db = optional.get();
			//Copiar as propriedades
			db.setNome(carro.getNome());
			db.setTipo(carro.getTipo());
			//Atualiza o carro
			rep.save(db);
			return CarroDTO.create(db);
		}else {
			return null;
			//throw new RuntimeException("Não foi possivel atualizar o registro");
		}
	}

	public void delete(Long id) {

			rep.deleteById(id);
	
	}
	
	
}
