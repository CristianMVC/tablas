package com.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;

@Entity
@Table(name = "CsTabla")
public class Tabla implements Serializable {

	private static final long serialVersionUID = 1L;

	public Tabla() {
	}

	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;

	@Column(name = "ddbb")
	private String baseDeDatos;

	@Column(name = "tabla")
	private String tabla;

	@Column(name = "titulo")
	private String titulo;

	@Column(name = "visible")
	private boolean visible;

	@Column(name = "orden")
	private String orden;

	@Column(name = "topRows")
	private Long topRows;

	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL })
	@JoinColumn(name = "idtabla", referencedColumnName = "id")
	@OrderBy("orden asc")
	private List<Campo> campos = new ArrayList<Campo>();



	@Column(name = "posicion")
	private int posicion;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBaseDeDatos() {
		return baseDeDatos;
	}

	public void setBaseDeDatos(String baseDeDatos) {
		this.baseDeDatos = baseDeDatos;
	}

	public String getTabla() {
		return tabla.toLowerCase();
	}

	public void setTabla(String tabla) {
		this.tabla = tabla;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public String getOrden() {
		return orden;
	}

	public void setOrden(String orden) {
		this.orden = orden;
	}

	public Long getTopRows() {
		return topRows;
	}

	public void setTopRows(Long topRows) {
		this.topRows = topRows;
	}

	public List<Campo> getCampos() {
		return campos;
	}

	public void setCampos(Campo campos) {		
		this.campos.add(campos);
	}


	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

}
