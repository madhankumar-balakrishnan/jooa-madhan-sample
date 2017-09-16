package com.jooq.madhan.sample.config;


import java.sql.SQLException;

import javax.sql.DataSource;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {
	
	@Value("${username}")
	private String username;
	
	@Value("${password}")
	private String password;
	
	@Bean
	public DSLContext dslContext(DataSource dataSource) throws SQLException {
		System.out.println("######################################");
		System.out.println("###                                ###");
		System.out.println("### Username: "+username+"         ###");
		System.out.println("### Password: "+password+"         ###");
		System.out.println("###                                ###");
		System.out.println("######################################");

		return DSL.using(dataSource.getConnection(),SQLDialect.MYSQL,new Settings().withRenderFormatted(true));
	}
}
