package com.lecoffretderachel.ordersmanager.injectionconfig;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.lecoffretderachel.ordersmanager.dao.ProductDAO;
import com.lecoffretderachel.ordersmanager.dao.ProductDAOImpl;
import com.lecoffretderachel.ordersmanager.dao.TagDAO;
import com.lecoffretderachel.ordersmanager.dao.TagDAOImpl;
import com.lecoffretderachel.ordersmanager.model.Customer;
import com.lecoffretderachel.ordersmanager.model.Inventory;
import com.lecoffretderachel.ordersmanager.model.Order;
import com.lecoffretderachel.ordersmanager.model.Product;
import com.lecoffretderachel.ordersmanager.model.Tag;
import com.lecoffretderachel.ordersmanager.service.ProductService;
import com.lecoffretderachel.ordersmanager.service.ProductServiceImpl;
import com.lecoffretderachel.ordersmanager.service.TagService;
import com.lecoffretderachel.ordersmanager.service.TagServiceImpl;

@Configuration
@ComponentScan("com.lecoffretderachel.ordersmanager")
@EnableTransactionManagement
public class DataBaseConfig {
	
	@Bean
	public ProductService getProductService() {
		return new ProductServiceImpl(getProductDAO());
	}
	
	@Bean
	public ProductDAO getProductDAO() {
		return new ProductDAOImpl(getSessionFactory());
	}
	
	@Bean
	public TagService getTagService() {
		return new TagServiceImpl(getTagDAO());
	}
	
	@Bean
	public TagDAO getTagDAO() {
		return new TagDAOImpl(getSessionFactory());
	}
	
	@Bean
	public DataSource getDataSource() {
	    BasicDataSource dataSource = new BasicDataSource();
	    dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
	    dataSource.setUrl("jdbc:mysql://localhost:3306/order_db?serverTimezone=UTC&amp;useSSL=false");
	    dataSource.setUsername("root");
	    dataSource.setPassword("root");
	 
	    return dataSource;
	}
	
	@Bean
	public SessionFactory getSessionFactory() {
	 
	    LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(getDataSource());

	    sessionBuilder.addAnnotatedClasses(Product.class);
	    sessionBuilder.addAnnotatedClasses(Tag.class);
	    sessionBuilder.addAnnotatedClasses(Inventory.class);
	    sessionBuilder.addAnnotatedClasses(Order.class);
	    sessionBuilder.addAnnotatedClasses(Customer.class);
	    
	    Properties prop = new Properties();
	    prop.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
	    prop.setProperty("hibernate.show_sql", "true");
	    sessionBuilder.addProperties(prop);
	 
	    return sessionBuilder.buildSessionFactory();
	}
	
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager() {
	    HibernateTransactionManager transactionManager = new HibernateTransactionManager(
	            getSessionFactory());
	 
	    return transactionManager;
	}
}
