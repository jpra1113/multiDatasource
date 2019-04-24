package com.example.demo.config;

import java.util.HashMap;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "sqlserverEntityManagerFactory",
                transactionManagerRef = "sqlserverTransactionManager", 
        basePackages = { "com.example.demo.repositories.sqlserver" })
public class SqlserverDBConfig {

	@Autowired
	private Environment env;
	
	@Primary
	@Bean
	public DataSource sqlserverDataSource() {

	    DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    dataSource.setDriverClassName(env.getProperty("spring.datasource.sqlserver.driver-class-name"));
	    dataSource.setUrl(env.getProperty("spring.datasource.sqlserver.url"));
	    dataSource.setUsername(env.getProperty("spring.datasource.sqlserver.username"));
	    dataSource.setPassword(env.getProperty("spring.datasource.sqlserver.password"));

	    return dataSource;

	}

	@Primary
	@Bean(name = "sqlserverEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean sqlserverEmFactory(
			EntityManagerFactoryBuilder builder) {
		LocalContainerEntityManagerFactoryBean em = builder.dataSource(sqlserverDataSource()).packages("com.example.demo.entity.sqlserver")
				.build();
		HibernateJpaVendorAdapter ven = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(ven);
		HashMap<String, Object> prop = new HashMap<>();
		prop.put("hibernate.dialect", env.getProperty("spring.sqlserver.jpa.properties.hibernate.dialect"));
		prop.put("hibernate.show_sql", env.getProperty("spring.jpa.show-sql"));

		em.setJpaPropertyMap(prop);
		em.afterPropertiesSet();
		return em;
	}

	@Primary
	@Bean(name = "sqlserverTransactionManager")
	public PlatformTransactionManager dsTransactionManager(@Qualifier("sqlserverEntityManagerFactory") EntityManagerFactory sqlserverEntityManagerFactory) {
		return new JpaTransactionManager(sqlserverEntityManagerFactory);
	}
}