package com.bcp.tipoCambio.repository;

import java.time.LocalDate;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.bcp.tipoCambio.entity.TablaConversionEntity;

import reactor.core.publisher.Mono;

public interface TablaConversionRepository extends ReactiveCrudRepository<TablaConversionEntity, Long> {
	
	Mono<TablaConversionEntity> findByOrigenAndDestinoAndFecha(Long origen, Long destino, LocalDate fecha);
	
}
