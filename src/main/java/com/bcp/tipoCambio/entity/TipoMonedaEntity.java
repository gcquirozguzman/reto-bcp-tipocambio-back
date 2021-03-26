package com.bcp.tipoCambio.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotEmpty;

import org.hibernate.envers.Audited;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import com.bcp.tipoCambio.validator.TipoMoneda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Audited
@AllArgsConstructor 
@NoArgsConstructor 
@Entity(name = "tipoMoneda")
@Table("tipoMoneda")
@SequenceGenerator(sequenceName = "tipoMoneda_seq", allocationSize = 1, name = "tipoMoneda_seq")
public class TipoMonedaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipoMoneda_seq")
	private Long id;
	
	@TipoMoneda
	@Column(name="codigo",nullable = false)
	@NotEmpty(message = "Ingrese codigo")
	private String codigo;
	
	@Column(name="nombre",nullable = false)
	@NotEmpty(message = "Ingrese nombre")
	private String nombre;
	
}