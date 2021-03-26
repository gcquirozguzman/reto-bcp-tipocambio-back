package com.bcp.tipoCambio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bcp.tipoCambio.modal.ConversionRequest;
import com.bcp.tipoCambio.modal.ConversionResponse;
import com.bcp.tipoCambio.service.ConversionService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("conversion")
public class ConversionController {

	@Autowired
	ConversionService conversionService;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	Mono<ResponseEntity<ConversionResponse>> convertirTipoCambio(@Validated @RequestBody ConversionRequest conversionRequest) {
		return conversionService.convertirTipoCambio(conversionRequest).map( u -> ResponseEntity.ok(u))
                .defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
}
