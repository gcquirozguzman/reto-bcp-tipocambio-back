package com.bcp.tipoCambio.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Audited
@AllArgsConstructor 
@NoArgsConstructor 
@Entity(name = "tablaConversion")
@Table("tablaConversion")
@SequenceGenerator(sequenceName = "tablaConversion_seq", allocationSize = 1, name = "tablaConversion_seq")
public class TablaConversionEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tablaConversion_seq")
	private Long id;
	
	@NotNull(message = "Ingrese moneda origen")	
	private Long origen;
	
	@NotNull(message = "Ingrese moneda destino")	
	private Long destino;
	
	@NotNull(message = "Ingrese monto")
	private BigDecimal monto;
	
	@Column(nullable = false)
	@NotNull(message = "Ingrese fecha")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDate fecha;
	
	@Transient
	private String codigoOrigen;
	
	@Transient
	private String codigoDestino;

}
