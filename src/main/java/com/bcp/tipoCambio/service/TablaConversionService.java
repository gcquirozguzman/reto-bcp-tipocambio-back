package com.bcp.tipoCambio.service;

import com.bcp.tipoCambio.entity.TablaConversionEntity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TablaConversionService {
	
	Flux<TablaConversionEntity> listarTodos();
	Flux<TablaConversionEntity> listarTodosCodigo();
	Mono<TablaConversionEntity> crear(TablaConversionEntity tablaConversionEntity);
	Mono<TablaConversionEntity> buscarById(Long id);
	Mono<Void> eliminar(Long id);
	Mono<TablaConversionEntity> obtenerTipoCambioDia(TablaConversionEntity tablaConversionEntity);
	
}
