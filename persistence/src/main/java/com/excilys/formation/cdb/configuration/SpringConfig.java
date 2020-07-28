package com.excilys.formation.cdb.configuration;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages="com.excilys.formation.cdb")
@EnableJpaRepositories(basePackages = "com.excilys.formation.cdb.daos")
public class SpringConfig {

	@Bean
	public DataSource hikariDataSource() {
		return new HikariDataSource(new HikariConfig("/datasource.properties"));
	}

	@Bean
	public EntityManagerFactory entityManagerFactory() {

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("com.excilys.formation.cdb.models");
		factory.setDataSource(hikariDataSource());
		factory.afterPropertiesSet();

		return factory.getObject();
	}

	@Bean
	public NamedParameterJdbcTemplate jdbcTemplate(DataSource hikariDataSource){
		return new NamedParameterJdbcTemplate(hikariDataSource);
	}

	@Bean
	public PlatformTransactionManager transactionManager(DataSource hikariDataSource) {
		DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
		dataSourceTransactionManager.setDataSource(hikariDataSource);
		return dataSourceTransactionManager;
	}
}
