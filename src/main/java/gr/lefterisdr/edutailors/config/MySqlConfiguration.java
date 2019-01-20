package gr.lefterisdr.edutailors.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class MySqlConfiguration
{
    @Bean
    @ConfigurationProperties(prefix="edutailors.datasource")
    public DataSource mySqlDataSource()
    {
        return DataSourceBuilder.create().build();
    }
}
