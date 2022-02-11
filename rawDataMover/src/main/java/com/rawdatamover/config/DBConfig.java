package com.rawdatamover.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
@Configuration
@PropertySource("classpath:/application.yml")
public class DBConfig {

    private final ApplicationContext applicationContext;

    @Autowired
    public DBConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari.primary")
    public HikariConfig primaryHikariConfig() {
        return new HikariConfig();
    }


    @Bean
    public DataSource primaryDataSource() {
        return new HikariDataSource(primaryHikariConfig());
    }

    @Bean
    public SqlSessionFactory primarySqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(primaryDataSource());
        sqlSessionFactoryBean.setTypeAliasesPackage("com.rawdatamover.model");
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mybatis/**/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "primarySqlSessionTemplate")
    public SqlSessionTemplate primarySqlSessionTemplate()throws Exception {
        return new SqlSessionTemplate(primarySqlSessionFactory());
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari.secondary")
    public HikariConfig secondaryHikariConfig() {
        return new HikariConfig();
    }

    @Bean
    public DataSource secondaryDataSource() {
        return new HikariDataSource(secondaryHikariConfig());
    }

    @Bean
    public SqlSessionFactory secondarySqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(secondaryDataSource());
        sqlSessionFactoryBean.setTypeAliasesPackage("com.rawdatamover.model");
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mybatis/**/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "secondarySqlSessionTemplate")
    public SqlSessionTemplate secondarySqlSessionTemplate() throws Exception{
        return new SqlSessionTemplate(secondarySqlSessionFactory());
    }




    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari.third")
    public HikariConfig thirdHikariConfig() {
        return new HikariConfig();
    }

    @Bean
    public DataSource thirdDataSource() {
        return new HikariDataSource(thirdHikariConfig());
    }

    @Bean
    public SqlSessionFactory thirdSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(thirdDataSource());
        sqlSessionFactoryBean.setTypeAliasesPackage("com.rawdatamover.model");
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mybatis/**/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "thirdSqlSessionTemplate")
    public SqlSessionTemplate thirdSqlSessionTemplate() throws Exception{
        return new SqlSessionTemplate(thirdSqlSessionFactory());
    }
}