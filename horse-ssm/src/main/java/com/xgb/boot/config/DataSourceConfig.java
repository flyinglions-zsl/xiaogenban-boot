package com.xgb.boot.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;

@Configuration
public class DataSourceConfig {

    private static Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);
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

    public DataSourceConfig() throws Exception {
        logger.info("Datasource init");
        initSqlSessionFactory(url,username,password,drivername);
    }


    public SqlSessionFactory getSqlSessionFactory(){
        return sqlSessionFactory;
    }

    public void initSqlSessionFactory(String url,String username,String password,String drivername) throws Exception {
        //build hikara datasource
        DataSourceBuilder<HikariDataSource> hikariDataSourceDataSourceBuilder = DataSourceBuilder.create().type(HikariDataSource.class);
        HikariDataSource hikariDataSource = hikariDataSourceDataSourceBuilder.driverClassName(drivername).url(url)
                                                    .username(username).password(password).build();

        //配置Hikari连接池
        /*hikariDataSource.setAutoCommit(true);//update自动提交设置
        hikariDataSource.setConnectionTestQuery("select 1");//连接查询语句设置
        hikariDataSource.setConnectionTimeout(3000);//连接超时时间设置
        hikariDataSource.setIdleTimeout(3000);//连接空闲生命周期设置
        hikariDataSource.setIsolateInternalQueries(false);//执行查询启动设置
        hikariDataSource.setMaximumPoolSize(3000);//连接池允许的最大连接数量
        hikariDataSource.setMaxLifetime(1800000);//检查空余连接优化连接池设置时间,单位毫秒
        hikariDataSource.setMinimumIdle(10);//连接池保持最小空余连接数量
        hikariDataSource.setPoolName("hikariPool");//连接池名称*/
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(hikariDataSource);
        //Resource []resources = new PathMatchingResourcePatternResolver().getResources("classpath:mappers/*.xml");
        //factoryBean.setMapperLocations(resources);
        factoryBean.setConfigLocation(new DefaultResourceLoader().getResource("classpath:mybatisplus-config.xml"));
        sqlSessionFactory = factoryBean.getObject();
        logger.info("Datasource init finish");
    }

    public static DataSourceConfig getInstance() throws Exception {
        if (dataSourceConfig == null){
            synchronized (DataSourceConfig.class){
                if (dataSourceConfig == null){
                    dataSourceConfig = new DataSourceConfig();
                }
            }
        }
        return dataSourceConfig;
    }
}
