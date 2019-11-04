package com.example.dbms.config;

import javax.sql.DataSource;

import com.example.dbms.dao.adminDAO;
import com.example.dbms.dao.adminDAOimp;
import com.example.dbms.dao.employeeDAO;
import com.example.dbms.dao.employeeDAOimp;
import com.example.dbms.dao.productDAO;
import com.example.dbms.dao.productDAOimp;
import com.example.dbms.dao.retailerDAO;
import com.example.dbms.dao.retailerDAOimp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages = "com.example.dbms")
@EnableWebMvc
public class MvcConfiguration implements WebMvcConfigurer{
 
    @Bean
    public ViewResolver getViewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
     
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }
 
    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        // dataSource.setUrl("jdbc:mysql://bbde1024486b5d:69789c1c@us-cdbr-iron-east-05.cleardb.net/heroku_7fd874d880baac3?reconnect=true");
        // dataSource.setUsername("bbde1024486b5d");
        // dataSource.setPassword("69789c1c");
        dataSource.setUrl("jdbc:mysql://localhost:3306/project");
        dataSource.setUsername("root");
        dataSource.setPassword("bhagya");
        return dataSource;
    }

    @Bean
    public retailerDAO getRetailerDAO(){
        return new retailerDAOimp(getDataSource());
    }

    @Bean
    public employeeDAO getEmployeeDAO(){
        return new employeeDAOimp(getDataSource());
    }

    @Bean 
    public adminDAO getAdminDAO(){
        return new adminDAOimp(getDataSource());
    }

    @Bean 
    public productDAO getProductDAO(){
        return new productDAOimp(getDataSource());
    }
}