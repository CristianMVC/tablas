package com.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import javax.persistence.TableGenerator;

import org.apache.commons.lang.StringUtils;



@Entity
@Table(name = "csCampo")
public class Campo {


	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;

    @Column(name = "ddbb")
    private String baseDeDatos;
		
	//@Column(name = "idTabla")
	//private long idTabla;

	@Column(name = "campo")
	private String campo;

	@Column(name = "tabla")
	private String tabla;

	@Column(name = "titulo")
	private String titulo;

	@Column(name = "formato")
	private String formato;

	@Column(name = "anchoColumna")
	private Long anchoDeColumna;

	@Column(name = "tipo")
	private String tipo;

	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "filtrar")
	private boolean filtrar;

	@Column(name = "visible")
	private boolean visible;

	@Column(name = "orden")
	private int orden;


	@Column(name = "alineacion")
	private String alineacion;

	@Column(name = "relacion")
	private String relacion;

//	@Column(name = "sumarizar")
//	private boolean sumarizar;

	
	 @ManyToOne()
	 @JoinColumn(name = "idTabla")
	 private Tabla ttabla;
	
	
	public String getFormato() {
		return formato == null ? "" : formato;
	}

	public String getBaseDeDatos() {
		return baseDeDatos;
	}

	public void setBaseDeDatos(String baseDeDatos) {
		this.baseDeDatos = baseDeDatos;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	

	public void setAnchoDeColumna(Long anchoDeColumna) {
		this.anchoDeColumna = anchoDeColumna;
	}

	

	public String getTipoNativo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getRelacion() {
		return relacion;
	}

	public void setRelacion(String relacion) {
		this.relacion = relacion;
	}

	public boolean isFiltrar() {
		return filtrar;
	}

	public void setFiltrar(boolean filtrar) {
		this.filtrar = filtrar;
	}



	public String getCampo() {
		return campo.toLowerCase();
	}

	//public long getIdTabla() {
	//	return idTabla;
	//}

	//public void setIdTabla(long idTabla) {
	//	this.idTabla = idTabla;
	//}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public String getTitulo() {

		return titulo == null ? campo : titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public String getTabla() {
		return tabla;
	}

	public void setTabla(String tabla) {
		this.tabla = tabla;
	}
	
	

//	public boolean isSumarizar() {
//		return sumarizar;
//	}
//
//	public void setSumarizar(boolean sumarizar) {
//		this.sumarizar = sumarizar;
//	}

	/**
	 * Obtiene el alias de los campos por Relacion o Tabla
	 * 
	 * @return
	 */
	public String getAlias() {
		String alias = "";

		if (getRelacion() == null) {
			alias = String.format("%s.%s", tabla, getCampo());
		} else {

			alias = String.format("%s.%s", getRelacion(), getCampo());
		}

		return alias.toLowerCase();
	}

	public String getCampoEstado() {
		String molde = "csESTADO{valorDATOREFERENCIA}";
		String estado = campo;
		estado = StringUtils.replace(molde, "{valorDATOREFERENCIA}",
				String.format("%s.descripcion %sdescripcion", campo, campo));
		return estado.toLowerCase();
	}

	
	public String getCampoEstadoSinAlias() {
		String molde = "csESTADO{valorDATOREFERENCIA}";
		String estado = campo;
		estado = StringUtils.replace(molde, "{valorDATOREFERENCIA}", String.format("%s.descripcion", campo));
		return estado.toLowerCase();
	}

	public Campo() {

	}

}
