package com.css.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties; 
import org.springframework.context.annotation.Bean; 
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate; 
 
import javax.sql.DataSource; 
 
@Configuration 
@ConfigurationProperties(prefix = "spring.datasource.test") 
public class TestConfig extends DataSourceAutoConfiguration { 
 
	@Bean(name="dsTest")
	@Primary
	@ConfigurationProperties(prefix="spring.datasource.test")
	public DataSource dataSource() {
	    return DataSourceBuilder.create().build();
	}

	@Bean(name = "jdbcTest") 
    public JdbcTemplate jdbcTemplate(@Qualifier("dsTest") DataSource dsTest) { 	
        return new JdbcTemplate((javax.sql.DataSource) dsTest); 
    }
 
} 