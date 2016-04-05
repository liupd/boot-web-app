package com.wa.net;

import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.MyBatisExceptionTranslator;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.util.ClassUtils;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@MapperScan(basePackages = "com.wa.net.dao")
public class SpringConfigMyBatis {


    @Resource
    private DataSource dataSource;

    @Bean
    @ConfigurationProperties(prefix = "spring.mybatis")
    public SqlSessionFactory sqlSessionFactory() throws Throwable {
        com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean factory=new MybatisSqlSessionFactoryBean();
        factory.setConfigLocation(new ClassPathResource("/mybatis-mapper/mybatis.xml"));
        factory.setFailFast(true);
        factory.setDataSource(dataSource);
        System.out.println(factory);
        return factory.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate() throws Throwable {
        SqlSessionTemplate template =
                new SqlSessionTemplate(sqlSessionFactory(), ExecutorType.SIMPLE, new MyBatisExceptionTranslator(dataSource, false));
        return template;
    }


    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }

}
