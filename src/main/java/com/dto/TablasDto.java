package com.dto;

import java.util.ArrayList;
import java.util.List;

public class TablasDto {
	
	
	private List<String> nombre = new ArrayList<String>();

	public List<String> getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
								
		this.nombre.add(nombre);
	}
	

}
