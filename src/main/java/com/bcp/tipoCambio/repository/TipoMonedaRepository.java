package com.bcp.tipoCambio.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.bcp.tipoCambio.entity.TipoMonedaEntity;

import reactor.core.publisher.Mono;

public interface TipoMonedaRepository extends ReactiveCrudRepository<TipoMonedaEntity, Long>{
	
	Mono<TipoMonedaEntity> findByCodigo(String codigo);
	
}
