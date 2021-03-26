package com.bcp.tipoCambio.modal;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor 
@NoArgsConstructor 
public class ConversionRequest {

	private BigDecimal monto;
	private String codigoMonedaOrigen;
	private String codigoMonedaDestino;
	
}