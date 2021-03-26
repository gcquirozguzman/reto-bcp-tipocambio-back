package com.bcp.tipoCambio.controller;

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

import com.bcp.tipoCambio.entity.TipoMonedaEntity;
import com.bcp.tipoCambio.exception.TipoMonedaUnSupportedFieldPatchException;
import com.bcp.tipoCambio.service.TipoMonedaService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin()
@RestController
@RequestMapping("tipoMoneda")
public class TipoMonedaController {

	@Autowired
	TipoMonedaService tipoMonedaService;
	
	@GetMapping("listarTodos")
	Flux<TipoMonedaEntity> listarTodos(){
		return tipoMonedaService.listarTodos();
	}
	
	@PostMapping("crear")
	@ResponseStatus(code = HttpStatus.CREATED)
	Mono<TipoMonedaEntity> crear(@Validated @RequestBody TipoMonedaEntity tipoMonedaEntity) {
		return tipoMonedaService.crear(tipoMonedaEntity);
	}
	
	@GetMapping("buscar/{id}")
	Mono<ResponseEntity<TipoMonedaEntity>> buscarById(@PathVariable @Min(1) Long id){
		return tipoMonedaService.buscarById(id).map( u -> ResponseEntity.ok(u))
                .defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@GetMapping("buscarPorCodigo/{codigo}")
	Mono<ResponseEntity<TipoMonedaEntity>> buscarByCodigo(@PathVariable String codigo){
		return tipoMonedaService.findByCodigo(codigo).map( u -> ResponseEntity.ok(u))
                .defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("eliminar/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	Mono<ResponseEntity<Void>> eliminar(@PathVariable Long id) {
		return tipoMonedaService.eliminar(id)
                .map( r -> ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@PutMapping("actualizar/{id}")
	Mono<Object> actualizar(@Validated @RequestBody TipoMonedaEntity tipoMonedaEntity, @PathVariable Long id) {
		
		return tipoMonedaService.buscarById(id).map(
						x -> {
							x.setCodigo(tipoMonedaEntity.getCodigo());
							x.setNombre(tipoMonedaEntity.getNombre());
							return tipoMonedaService.crear(x)
									.map( r -> ResponseEntity.ok().<Void>build())
					                .defaultIfEmpty(ResponseEntity.notFound().build());
						}
				);
			
	}
	
	@PatchMapping("actualizarNombre/{id}")
	Mono<Object> actualizarNombre(@Validated @RequestBody Map<String, String> parametro, @PathVariable Long id) {
		return tipoMonedaService.buscarById(id)
				.map(
						x -> {
							String nombre = parametro.get("nombre");
							if(!StringUtils.isEmpty(nombre)) {
								x.setNombre(nombre);
								return tipoMonedaService.crear(x)
										.map( r -> ResponseEntity.ok().<Void>build())
						                .defaultIfEmpty(ResponseEntity.notFound().build());
							} else {
								throw new TipoMonedaUnSupportedFieldPatchException(parametro.keySet());
							}
							
						}
				);
	}
	
	
}
