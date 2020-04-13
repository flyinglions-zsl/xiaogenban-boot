package com.xgb.boot.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String drivername;

    private SqlSessionFactory sqlSessionFactory;

    /**
     * double check singel schme
     * */
    private static volatile DataSourceConfig dataSourceConfig;

    public DataSourceConfig(){
        System.out.println("Datasource init");
        initSqlSessionFactory(url,username,password,drivername);
    }


    public SqlSessionFactory getSqlSessionFactory(){
        return sqlSessionFactory;
    }

    public void initSqlSessionFactory(String url,String username,String password,String drivername){
        //build hikara datasource
        DataSourceBuilder<HikariDataSource> hikariDataSourceDataSourceBuilder = DataSourceBuilder.create().type(HikariDataSource.class);
        HikariDataSource hikariDataSource = hikariDataSourceDataSourceBuilder.driverClassName(drivername).url(url)
                                                    .username(username).password(password).build();
    }
}
