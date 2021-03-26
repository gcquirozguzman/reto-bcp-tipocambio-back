package com.bcp.tipoCambio.exception;

@SuppressWarnings("serial")
public class TipoMonedaNotFoundException extends RuntimeException{

	public TipoMonedaNotFoundException(Long id) {
		super("Tipo de moneda no ya sido encontrado con id : "+id);
	}
	
}
