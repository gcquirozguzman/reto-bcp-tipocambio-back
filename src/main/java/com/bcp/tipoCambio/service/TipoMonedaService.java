package com.bcp.tipoCambio.service;

import com.bcp.tipoCambio.entity.TipoMonedaEntity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TipoMonedaService {
	
	Flux<TipoMonedaEntity> listarTodos();
	Mono<TipoMonedaEntity> crear(TipoMonedaEntity tipoMonedaEntity);
	Mono<TipoMonedaEntity> buscarById(Long id);
	Mono<Void> eliminar(Long id);
	Mono<TipoMonedaEntity> findByCodigo(String codigo);
	
}
