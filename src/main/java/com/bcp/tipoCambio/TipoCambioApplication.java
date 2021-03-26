package com.bcp.tipoCambio;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TipoCambioApplication {

	public static void main(String[] args) {
		SpringApplication.run(TipoCambioApplication.class, args);
	}
	
	@Bean
	CommandLineRunner cargaDataInicial(ApplicationContext context) {
//		TipoMonedaRepository tipoMonedaRepository = context.getBean(TipoMonedaRepository.class);
//		tipoMonedaRepository.save(new TipoMonedaEntity(null, "PEN", "Nuevo Sol"));
//		tipoMonedaRepository.save(new TipoMonedaEntity(null, "USS","Dolar"));
		return null;
	}
	
}
