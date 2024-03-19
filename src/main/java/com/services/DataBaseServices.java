package com.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dto.ColumnasDto;
import com.dto.DataBasesDto;
import com.dto.TablasDto;
import com.model.Campo;
import com.model.Tabla;
import com.repository.CampoRepository;
import com.repository.Consultas;
import com.repository.TablaRepository;

@Service
public class DataBaseServices {

	@Autowired
	private Consultas consultas;
	private static final Logger logger = LoggerFactory.getLogger(DataBaseServices.class);

	private DataBasesDto dbDto;
	private TablasDto tablasDto;
	private ColumnasDto columnasDto;
	private List<ColumnasDto> listaColumnas;

	String dataBase;
	String tabla;

	@Autowired
	private CampoRepository campoRepository;

	@Autowired
	private TablaRepository tablaRepository;

	public DataBasesDto getDataBases() {
		dbDto = new DataBasesDto();

		ResultSet result = consultas.consultaBases();
		if (result != null) {
			try {
				while (result.next()) {
					dbDto.setNombre(result.getString("name"));
				}
			} catch (SQLException e) {
				logger.debug(e.getMessage());
				return null;
			}
		}
		return dbDto;

	}

	public TablasDto getTablas(String db) {
		tablasDto = new TablasDto();
		ResultSet result = consultas.consultaTablas(db);

		if (result != null) {

			try {
				while (result.next()) {
					tablasDto.setNombre(result.getString("name"));
				}
			} catch (SQLException e) {
				logger.debug(e.getMessage());
				return null;
			}

		}

		return tablasDto;
	}

	public List<ColumnasDto> getCampos(String db, String tabla) {

		listaColumnas = new ArrayList<ColumnasDto>();

		ResultSet result = consultas.consultaCampos(db, tabla);

		if (result != null) {

			try {
				while (result.next()) {
					columnasDto = new ColumnasDto();

					columnasDto.setColumna(result.getString("COLUMN_NAME"));
					columnasDto.setDb(result.getString("TABLE_CATALOG"));
					columnasDto.setSchema(result.getString("TABLE_SCHEMA"));
					columnasDto.setTabla(result.getString("TABLE_NAME"));
					columnasDto.setTipo(result.getString("DATA_TYPE"));

					listaColumnas.add(columnasDto);

				}
			} catch (SQLException e) {
				logger.debug(e.getMessage());
				return null;
			}

		}

		this.dataBase = db;
		this.tabla = tabla;

		return listaColumnas;
	}

	public String insertarDatos(String titulo, List<String>[] campos) {

		try {

			Tabla tabla = new Tabla();
			tabla.setBaseDeDatos(this.dataBase);
			tabla.setTabla(this.tabla);
			tabla.setOrden(this.tabla +"."+campos[0].get(0)) ;
			tabla.setVisible(true);
			tabla.setTopRows(50L);
			tabla.setTitulo(titulo);

			for (List<String> columnas : campos) {

				Campo campo = new Campo();
				campo.setBaseDeDatos(this.dataBase);
				campo.setTabla(this.tabla);
				campo.setCampo(columnas.get(0));
				campo.setTitulo(columnas.get(1));
				campo.setAnchoDeColumna(Long.parseLong(columnas.get(2)));
				campo.setTipo(columnas.get(3));
				campo.setVisible(Boolean.parseBoolean(columnas.get(4)));
				campo.setFiltrar(Boolean.parseBoolean(columnas.get(5)));				
				tabla.setCampos(campo);

			}

			tablaRepository.save(tabla);

		} catch (Exception e) {
			logger.debug(e.getMessage());
			return e.getMessage();
		}

		return "La insercción se realizó correctamente";
	}

}
