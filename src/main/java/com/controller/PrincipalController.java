package com.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dto.ColumnasDto;
import com.dto.DataBasesDto;
import com.dto.TablasDto;
import com.model.Campo;
import com.services.DataBaseServices;


@Controller
public class PrincipalController {

	
	
	@Autowired
	DataBaseServices db;
	
	@RequestMapping(value = "/principal", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
	
       DataBasesDto result = db.getDataBases();
       model.addAttribute("dbs", result.getNombre());
      
       return "principal";
	}

	
	@RequestMapping(value = "/consulta", method = RequestMethod.POST)
	@ResponseBody
	public List<String> getTablas(@RequestParam(value = "dbName") String dbName) {
	
		TablasDto result = db.getTablas(dbName);
		List<String> tablas = result.getNombre();
		
		return tablas;
	}
	
	
	@RequestMapping(value = "/campos", method = RequestMethod.POST)
	@ResponseBody
	public List<ColumnasDto> getCampos(@RequestParam(value = "dbName") String dbName,
			@RequestParam(value = "tableName") String tableName) {
	
		List<ColumnasDto> campos = db.getCampos(dbName, tableName);
				
		return campos;
	}
	
	
	@RequestMapping(value = "/form", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String form(@RequestBody List<String>[] campos, @RequestParam("titulo") String titulo) {

      String result = db.insertarDatos(titulo, campos);
  
       return result;
	}
	
	
}
