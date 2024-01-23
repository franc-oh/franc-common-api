package com.franc.config.datasource;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@MapperScan(value="com.franc.account.dao", sqlSessionFactoryRef="accountSessionFactory")
@EnableTransactionManagement
public class AccountDatabaseConfig {

    @Primary
    @Bean(name="accountDataSource")
    @ConfigurationProperties(prefix="spring.datasource.account")
    public DataSource accountDataSource() {
        //application.properties에서 정의한 DB 연결 정보를 빌드
        return DataSourceBuilder
                .create()
                .type(HikariDataSource.class)
                .build();
    }

    @Primary
    @Bean(name="accountSessionFactory")
    public SqlSessionFactory accountSessionFactory(@Qualifier("accountDataSource") DataSource accountDataSource, ApplicationContext applicationContext) throws Exception{
        //세션 생성 시, 빌드된 DataSource를 세팅하고 SQL문을 관리할 mapper.xml의 경로를 알려준다.
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(accountDataSource);
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mapper/account/*.xml"));
        sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:mybatis-config.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Primary
    @Bean(name="accountSessionTemplate")
    public SqlSessionTemplate accountSessionTemplate(SqlSessionFactory accountSessionFactory) throws Exception{
        return new SqlSessionTemplate(accountSessionFactory);
    }

}
