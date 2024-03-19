package com.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Consultas {

	@Autowired
	private DataSource ds;

	public ResultSet consultaBases() {
		String query = "SELECT * FROM master.dbo.sysdatabases ORDER BY name";
		try {
			Connection con = ds.getConnection();
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			return rs;

		} catch (SQLException e) {
			return null;
		}

	}

	public ResultSet consultaTablas(String db) {
		String query = "select table_name as name from " + db
				+ ".INFORMATION_SCHEMA.TABLES ORDER BY table_name";

		try {
			Connection con = ds.getConnection();
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			return rs;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

	public ResultSet consultaCampos(String db, String tabla) {

		String query = "Select * from INFORMATION_SCHEMA.COLUMNS where TABLE_CATALOG='"
				+ db
				+ "' "
				+ "and TABLE_SCHEMA='DBO' and TABLE_Name='"
				+ tabla
				+ "'";

		try {
			Connection con = ds.getConnection();
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			return rs;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

}
