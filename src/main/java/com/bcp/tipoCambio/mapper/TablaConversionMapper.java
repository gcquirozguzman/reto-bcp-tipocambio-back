package com.bcp.tipoCambio.mapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.function.BiFunction;
import com.bcp.tipoCambio.entity.TablaConversionEntity;
import io.r2dbc.spi.Row;

public class TablaConversionMapper implements BiFunction<Row, Object, TablaConversionEntity> {

	@Override
	public TablaConversionEntity apply(Row row, Object o) {
		
		TablaConversionEntity entity = new TablaConversionEntity();
		
		entity.setId(row.get("a_id", Long.class));
		entity.setMonto(row.get("a_monto", BigDecimal.class));
		entity.setFecha(row.get("a_fecha", LocalDate.class));
		
		return entity;
	}
	
}
