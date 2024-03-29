package com.carros.api;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.carros.domain.Carro;
import com.carros.domain.CarroService;
import com.carros.domain.dto.CarroDTO;

@RestController
@RequestMapping("/api/v1/carros")
public class CarrosController {

	@Autowired
	CarroService service;

	//Retorno 200 OK
	@GetMapping
	public ResponseEntity<List<CarroDTO>> get() {
		return ResponseEntity.ok(service.getCarros());
	}

	@GetMapping("/{id}")
	public ResponseEntity getCarrosById(@PathVariable("id") Long id) {
		  CarroDTO carro = service.getCarroById(id);

	        return ResponseEntity.ok(carro);
	}

	@GetMapping("/tipo/{tipo}")
	public ResponseEntity <List<CarroDTO>> getCarrosByTipo(@PathVariable("tipo") String tipo) {
		
		List<CarroDTO> carros = service.getCarrosByTipo(tipo);
		return carros.isEmpty() ?
				ResponseEntity.noContent().build() :
				ResponseEntity.ok(carros);
	}

	// RequestBody converte um json para o objeto que está sendo vinculado
	@PostMapping
	@Secured({ "ROLE_ADMIN" })
	public ResponseEntity postCarro(@RequestBody Carro carro) {
	
			CarroDTO  c = service.insert(carro);
			
			URI location = getUri(c.getId());
			return ResponseEntity.created(location).build();
		
	}
	//Retorna um location com o path do carro novo
	private URI getUri(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}

	@PutMapping("/{id}")
	public ResponseEntity putCarro(@PathVariable("id") Long id, @RequestBody Carro carro) {
		carro.setId(id);
		
		CarroDTO c = service.update(carro, id);
		
		return c != null ? 
					ResponseEntity.ok(c) : 
					ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable("id") Long id) {
		service.delete(id);
		
		return  ResponseEntity.ok().build();
					
	}
}
