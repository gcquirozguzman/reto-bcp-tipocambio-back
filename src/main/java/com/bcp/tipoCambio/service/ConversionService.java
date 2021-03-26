package com.bcp.tipoCambio.service;

import com.bcp.tipoCambio.modal.ConversionRequest;
import com.bcp.tipoCambio.modal.ConversionResponse;

import reactor.core.publisher.Mono;

public interface ConversionService {
	
	Mono<ConversionResponse> convertirTipoCambio(ConversionRequest conversionRequest);
	
}
