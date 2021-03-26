package com.bcp.tipoCambio.service._impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bcp.tipoCambio.entity.TablaConversionEntity;
import com.bcp.tipoCambio.repository.TablaConversionRepository;
import com.bcp.tipoCambio.repository.TipoMonedaRepository;
import com.bcp.tipoCambio.service.TablaConversionService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TablaConversionServiceImpl implements TablaConversionService{

	@Autowired
	TablaConversionRepository tablaConversionRepository;
	
	@Autowired
	TipoMonedaRepository tipoMonedaRepository;
	
	@Override
	public Flux<TablaConversionEntity> listarTodos() {
		return tablaConversionRepository.findAll();
	}
	
	@Override
	public Flux<TablaConversionEntity> listarTodosCodigo() {
		Flux<TablaConversionEntity> listTabla = tablaConversionRepository.findAll();		
		List<TablaConversionEntity> listDescripcion = listTabla.collectList().block();		
		for (int i = 0; i < listDescripcion.size(); i++) {
			listDescripcion.get(i).setCodigoOrigen(tipoMonedaRepository.findById(listDescripcion.get(i).getOrigen()).block().getCodigo());
			listDescripcion.get(i).setCodigoDestino(tipoMonedaRepository.findById(listDescripcion.get(i).getDestino()).block().getCodigo());
		}
		return Mono.just(listDescripcion).flatMapMany(Flux::fromIterable);
	}

	@Override
	public Mono<TablaConversionEntity> crear(TablaConversionEntity tablaConversionEntity) {
		return tablaConversionRepository.save(tablaConversionEntity);
	}

	@Override
	public Mono<TablaConversionEntity> obtenerTipoCambioDia(TablaConversionEntity tablaConversionEntity) {
		return tablaConversionRepository.findByOrigenAndDestinoAndFecha(tablaConversionEntity.getOrigen(), tablaConversionEntity.getDestino(), tablaConversionEntity.getFecha());
	}
	
	@Override
	public Mono<TablaConversionEntity> buscarById(Long id) {
		return tablaConversionRepository.findById(id);
	}

	@Override
	public Mono<Void> eliminar(Long id) {
		return tablaConversionRepository.deleteById(id);
	}

}
