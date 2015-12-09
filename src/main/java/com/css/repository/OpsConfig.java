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
@ConfigurationProperties(prefix="cassandra") 
public class OpsConfig extends DataSourceAutoConfiguration { 
 
	@Bean
	public DataSource opsDataSource() {
	    return DataSourceBuilder.create().build();
	}
	

} 