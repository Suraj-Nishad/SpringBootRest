package org.cap.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import org.hibernate.dialect.Oracle10gDialect;
import org.springframework.context.annotation.*;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="org.cap")
@EnableTransactionManagement
public class WebMVCConfig {

	@Bean
	public ViewResolver getViewResolver() {
		InternalResourceViewResolver viewResolver=new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/pages/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em
				= new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(oracleDataSource());
		em.setPackagesToScan("org.cap");
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(oracleProperties());
		return em;
	}

	/**
	 * postgres
	 * @return
	 */

//    For oracle Jdbc Connection

	@Bean
	public DataSource oracleDataSource() {
		try {
			ComboPooledDataSource dataSource = new ComboPooledDataSource();
			dataSource.setDriverClass("oracle.jdbc.driver.OracleDriver");
			dataSource.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");
			dataSource.setUser("hr");
			dataSource.setPassword("hr");
			return dataSource;
		} catch (Throwable e) {
			e.printStackTrace();
			return null;
		}
	}
// Using Hibernate properties 
      Properties oracleProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", Oracle10gDialect.class.getName());
		properties.setProperty("hibernate.hbm2ddl.auto", "create");
		properties.setProperty("hibernate.show_sql", "false");
		properties.setProperty("spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation", "true");
		return properties;
	}





	@Bean
	public PlatformTransactionManager transactionManager(
			EntityManagerFactory emf) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}










}

















