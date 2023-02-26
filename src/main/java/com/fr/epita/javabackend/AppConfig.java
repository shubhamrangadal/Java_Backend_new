package com.fr.epita.javabackend;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;



@Configuration
public class AppConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedHeaders("*")
                        .allowCredentials(true)
                        .maxAge(3600);
            }
        };
    }


    public static Properties loadProperties() {
        Properties configuration = new Properties();
        try {

            InputStream inputStream = AppConfig.class
                    .getClassLoader()
                    .getResourceAsStream("application.properties");
            configuration.load(inputStream);
            inputStream.close();


        }catch (Exception e) {
            e.printStackTrace();
        }
        return configuration;
    }

    private DataSource getDataSource(String url) {
        Properties properties = loadProperties();
        DataSourceBuilder<?> dataSource = DataSourceBuilder.create();
        dataSource.url(properties.getProperty(url));
        dataSource.username(properties.getProperty("spring.datasource.username"));
        dataSource.password(properties.getProperty("spring.datasource.password"));
        dataSource.driverClassName(properties.getProperty("spring.datasource.driver-class-name"));
        return dataSource.build();
    }

    String movieUrl = "spring.datasource.url.movie";


    public DataSource movieDataSource() {
        return getDataSource(movieUrl);
    }



    @Bean(name = "movieJdbcTemplate")
    public JdbcTemplate movieJdbcTemplate() {
        return new JdbcTemplate(movieDataSource());
    }


}
