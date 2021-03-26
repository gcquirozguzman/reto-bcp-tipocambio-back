package com.bcp.tipoCambio.controller;

import java.math.BigDecimal;
import java.util.Map;

import javax.validation.constraints.Min;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bcp.tipoCambio.entity.TablaConversionEntity;
import com.bcp.tipoCambio.exception.TipoMonedaUnSupportedFieldPatchException;
import com.bcp.tipoCambio.service.TablaConversionService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin()
@RestController
@RequestMapping("tablaConversion")
public class TablaConversionController {

	@Autowired
	TablaConversionService tablaConversionService;
	
	@GetMapping("listarTodos")
	Flux<TablaConversionEntity> listarTodos(){
		return tablaConversionService.listarTodos();
	}
	
	@GetMapping("listarTodosDescripcion")
	Flux<TablaConversionEntity> listarTodosDescripcion(){
		return tablaConversionService.listarTodosCodigo();
	}
	
	@PostMapping("crear")
	@ResponseStatus(code = HttpStatus.CREATED)
	Mono<TablaConversionEntity> crear(@Validated @RequestBody TablaConversionEntity tablaConversionEntity) {
		return tablaConversionService.crear(tablaConversionEntity);
	}
	
	@PostMapping("obtenerTipoCambioDia")
	@ResponseStatus(code = HttpStatus.CREATED)
	Mono<TablaConversionEntity> obtenerTipoCambioDia(@Validated @RequestBody TablaConversionEntity tablaConversionEntity) {
		return tablaConversionService.obtenerTipoCambioDia(tablaConversionEntity);
	}
	
	@GetMapping("buscar/{id}")
	Mono<ResponseEntity<TablaConversionEntity>> buscarById(@PathVariable @Min(1) Long id){
		return tablaConversionService.buscarById(id).map( u -> ResponseEntity.ok(u))
                .defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("eliminar/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	Mono<ResponseEntity<Void>> eliminar(@PathVariable Long id) {
		return tablaConversionService.eliminar(id)
                .map( r -> ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@PutMapping("actualizar/{id}")
	Mono<Object> actualizar(@Validated @RequestBody TablaConversionEntity tablaConversionEntity, @PathVariable Long id) {
		
		return tablaConversionService.buscarById(id).map(
						x -> {
							x.setOrigen(tablaConversionEntity.getOrigen());
							x.setDestino(tablaConversionEntity.getDestino());
							x.setMonto(tablaConversionEntity.getMonto());
							x.setFecha(tablaConversionEntity.getFecha());
							return tablaConversionService.crear(x)
									.map( r -> ResponseEntity.ok().<Void>build())
					                .defaultIfEmpty(ResponseEntity.notFound().build());
						}
				);
			
	}
	
	@PatchMapping("actualizarMonto/{id}")
	Mono<Object> actualizarMonto(@Validated @RequestBody Map<String, String> parametro, @PathVariable Long id) {
		return tablaConversionService.buscarById(id)
				.map(
						x -> {
							BigDecimal monto = new BigDecimal(parametro.get("monto"));
							if(!StringUtils.isEmpty(parametro.get("monto"))) {
								x.setMonto(monto);
								return tablaConversionService.crear(x)
										.map( r -> ResponseEntity.ok().<Void>build())
						                .defaultIfEmpty(ResponseEntity.notFound().build());
							} else {
								throw new TipoMonedaUnSupportedFieldPatchException(parametro.keySet());
							}
							
						}
				);
	}
	
	@PostMapping("actualizarMontoBCP/{id}")
	Mono<Object> actualizarMontoBCP(@Validated @RequestBody Map<String, String> parametro, @PathVariable Long id) {
		return tablaConversionService.buscarById(id)
				.map(
						x -> {
							BigDecimal monto = new BigDecimal(parametro.get("monto"));
							if(!StringUtils.isEmpty(parametro.get("monto"))) {
								x.setMonto(monto);
								return tablaConversionService.crear(x)
										.map( r -> ResponseEntity.ok().<Void>build())
						                .defaultIfEmpty(ResponseEntity.notFound().build());
							} else {
								throw new TipoMonedaUnSupportedFieldPatchException(parametro.keySet());
							}
							
						}
				);
	}
	
}
