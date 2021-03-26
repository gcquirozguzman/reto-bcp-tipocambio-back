package com.bcp.tipoCambio.exception;

import java.util.Set;

@SuppressWarnings("serial")
public class TipoMonedaUnSupportedFieldPatchException extends RuntimeException{
	
	public TipoMonedaUnSupportedFieldPatchException(Set<String> keys) {
		super("Campo "+keys.toString()+" no esta permitido");
	}

}
