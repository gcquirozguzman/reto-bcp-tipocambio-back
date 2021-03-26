package com.bcp.tipoCambio.modal;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor 
@NoArgsConstructor 
public class ConversionResponse {

	private BigDecimal monto;
	private BigDecimal montoConTipoCambio;
	private String codigoMonedaOrigen;
	private String codigoMonedaDestino;
	private BigDecimal tipoCambio;
	
}