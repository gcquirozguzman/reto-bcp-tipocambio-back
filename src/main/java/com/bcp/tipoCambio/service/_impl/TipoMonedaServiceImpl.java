package com.bcp.tipoCambio.service._impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcp.tipoCambio.entity.TipoMonedaEntity;
import com.bcp.tipoCambio.repository.TipoMonedaRepository;
import com.bcp.tipoCambio.service.TipoMonedaService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TipoMonedaServiceImpl implements TipoMonedaService{

	@Autowired
	TipoMonedaRepository tipoMonedaRepository;
	
	@Override
	public Flux<TipoMonedaEntity> listarTodos() {
		return tipoMonedaRepository.findAll();
	}

	@Override
	public Mono<TipoMonedaEntity> crear(TipoMonedaEntity tipoMonedaEntity) {
		return tipoMonedaRepository.save(tipoMonedaEntity);
	}

	@Override
	public Mono<TipoMonedaEntity> buscarById(Long id) {
		return tipoMonedaRepository.findById(id);
	}

	@Override
	public Mono<Void> eliminar(Long id) {
		return tipoMonedaRepository.deleteById(id);
	}

	@Override
	public Mono<TipoMonedaEntity> findByCodigo(String codigo) {
		return tipoMonedaRepository.findByCodigo(codigo);
	}
	
}
