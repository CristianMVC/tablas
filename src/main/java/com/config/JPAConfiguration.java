package com.config;


import java.io.IOException;
import java.math.BigInteger;
import java.sql.SQLException;

import javax.naming.ConfigurationException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.FilterType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import coop.bancocredicoop.xt.commons.config.ConfigurationHelper;
import coop.bancocredicoop.xt.commons.encryptacion.Encryptador;

import com.services.DataBaseServices;

/**
 * The Class JPAConfiguration.
 */
@Configuration
@EnableTransactionManagement
@ComponentScan
@EnableJpaRepositories(basePackages = {"com.repository"})
public class JPAConfiguration {


	  @Autowired
	    private ApplicationContext appContext;
	
	  
	
	@Bean
	public DataSource dataSource() throws SQLException {
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

		dataSource.setDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
		try {
			BigInteger modulo = new BigInteger(
					ConfigurationHelper.getValueBySectionAndParameter("xtConsultaParametriaT11-claves", "EncryptadorModulo"));
			BigInteger exponente = new BigInteger(
					ConfigurationHelper.getValueBySectionAndParameter("xtConsultaParametriaT11-claves", "EncryptadorExponente"));

			//String serverName = ConfigurationHelper.getValueBySectionAndParameter("xtConsulta-database", "serverName");
			//String dataBaseName = ConfigurationHelper.getValueBySectionAndParameter("xtConsulta-database",
				//	"dataBaseName");
			// url
			String urlConnection = ConfigurationHelper.getValueBySectionAndParameter("xtBuzon-database",
					"urlConnection");
			
			
			
			String serverName = "SSQL1002";
			String dataBaseName = "ITSQLWP1";
			
			urlConnection = urlConnection.replace("{SERVERNAME}", serverName).replace("{DATABASE}", dataBaseName);
			dataSource.setUrl(urlConnection);

			// password
			String password = ConfigurationHelper.getValueBySectionAndParameter("xtConsultaParametriaT11-claves",
					"ConnectionPassword");
			String passwordConnection = Encryptador.descifrar(modulo, exponente, password);
			dataSource.setPassword(passwordConnection);

			// user
			String userName = ConfigurationHelper.getValueBySectionAndParameter("xtConsulta-database", "userName");
			dataSource.setUsername(userName);
			

		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		return dataSource;
	}
	
	@Bean
	public EntityManagerFactory entityManagerFactory() throws SQLException {

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);
		vendorAdapter.setShowSql(true);
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("com");
		factory.setDataSource(dataSource());
		factory.afterPropertiesSet();
		return factory.getObject();
	}

	
	@Bean
	public DataBaseServices dataBaseServices() {			
		return new DataBaseServices();
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() throws SQLException {

		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory());
		return txManager;
	}

	

}
