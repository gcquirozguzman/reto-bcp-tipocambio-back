package com.bcp.tipoCambio.service._impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcp.tipoCambio.entity.TablaConversionEntity;
import com.bcp.tipoCambio.entity.TipoMonedaEntity;
import com.bcp.tipoCambio.modal.ConversionRequest;
import com.bcp.tipoCambio.modal.ConversionResponse;
import com.bcp.tipoCambio.repository.TablaConversionRepository;
import com.bcp.tipoCambio.repository.TipoMonedaRepository;
import com.bcp.tipoCambio.service.ConversionService;

import reactor.core.publisher.Mono;

@Service
public class ConversionServiceImpl implements ConversionService{

	@Autowired
	TablaConversionRepository tablaConversionRepository;
	
	@Autowired
	TipoMonedaRepository tipoMonedaRepository;

	@Override
	public Mono<ConversionResponse> convertirTipoCambio(ConversionRequest conversionRequest) {
		Mono<TipoMonedaEntity> monedaOrigen = tipoMonedaRepository.findByCodigo(conversionRequest.getCodigoMonedaOrigen());
		Mono<TipoMonedaEntity> monedaDestino = tipoMonedaRepository.findByCodigo(conversionRequest.getCodigoMonedaDestino());
		
		String pattern = "yyyy-MM-dd";
		DateFormat df = new SimpleDateFormat(pattern);
		Date today = Calendar.getInstance().getTime();
		LocalDate localDate = LocalDate.parse(df.format(today));		
		Mono<TablaConversionEntity> tipoCambioDia = tablaConversionRepository.findByOrigenAndDestinoAndFecha(monedaOrigen.block().getId(), monedaDestino.block().getId(), localDate);		
		ConversionResponse conversionResponse = new ConversionResponse();
		conversionResponse.setCodigoMonedaOrigen(conversionRequest.getCodigoMonedaOrigen());
		conversionResponse.setCodigoMonedaDestino(conversionRequest.getCodigoMonedaDestino());
		conversionResponse.setMonto(conversionRequest.getMonto());
		conversionResponse.setTipoCambio(tipoCambioDia.block().getMonto());
		conversionResponse.setMontoConTipoCambio(tipoCambioDia.block().getMonto().multiply(conversionRequest.getMonto()));
		
		return Mono.just(conversionResponse);
	}
	

}
