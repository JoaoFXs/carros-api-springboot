package com.carros.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//Mapeamento da tabela carro com o pojo de carro
@Entity(name="carro")
@Data
/*
 * @Getter
 * 
 * @Setter
 * 
 * @ToString
 * 
 * @EqualsAndHashCode
 * 
 * @NoArgsConstructor
 */
public class Carro {
	//@Id denomina que a variavel é um id da tabela
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)  utilizará da anotação auto_increment do proprio mysql para quando tiver um carro novo
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	//@Column(name = "nome"): Especifica qual valor da tabela a variavel esta referenciando
	@Column(name = "nome")
	private String nome;
	
	
	private String tipo;
	
	private String descricao;
	
	private String url_foto;
	
	private String url_video;
	
	private String latitude;
	
	private String longitude;
	public static void main(String[] args) {
		BCryptPasswordEncoder teste = new BCryptPasswordEncoder();
		System.out.println(teste.encode("joao123"));
		System.out.println(teste.encode("camilla123"));
		System.out.println(teste.encode("admin123"));
	}
}
