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
//@EnableJpaRepositories(basePackages = "com.example.demo.repositories.h2", entityManagerFactoryRef = "dsEmFactory", transactionManagerRef = "dsTransactionManager")

@EnableJpaRepositories(
        entityManagerFactoryRef = "h2EntityManagerFactory", 
        transactionManagerRef = "h2TransactionManager",
        basePackages = { "com.example.demo.repositories.h2" })
public class H2DBConfig {

	@Autowired
	private Environment env;

	
	@Bean
	public DataSource h2DataSource() {

	    DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    dataSource.setDriverClassName(env.getProperty("spring.datasource.h2.driver-class-name"));
	    dataSource.setUrl(env.getProperty("spring.datasource.h2.url"));
	    dataSource.setUsername(env.getProperty("spring.datasource.h2.username"));
	    dataSource.setPassword(env.getProperty("spring.datasource.h2.password"));

	    return dataSource;

	}

	@Bean(name = "h2EntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean h2EntityManagerFactory(
			EntityManagerFactoryBuilder builder) {
		LocalContainerEntityManagerFactoryBean em = builder.dataSource(h2DataSource()).packages("com.example.demo.entity.h2")
				.build();
		HibernateJpaVendorAdapter ven = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(ven);
		HashMap<String, Object> prop = new HashMap<>();
		prop.put("hibernate.dialect", env.getProperty("spring.h2.jpa.properties.hibernate.dialect"));
		prop.put("hibernate.show_sql", env.getProperty("spring.jpa.show-sql"));

		em.setJpaPropertyMap(prop);
		em.afterPropertiesSet();
		return em;
	}

	@Bean(name = "h2TransactionManager")
	public PlatformTransactionManager h2TransactionManager(@Qualifier("h2EntityManagerFactory") EntityManagerFactory h2EntityManagerFactory) {
		return new JpaTransactionManager(h2EntityManagerFactory);
	}
}