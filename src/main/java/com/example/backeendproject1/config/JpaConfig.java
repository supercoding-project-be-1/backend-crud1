//package com.example.backeendproject1.config;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.JpaVendorAdapter;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//@EnableJpaRepositories(
//        basePackages = {"com.example.backeendproject1.repository.comment",
//                        "com.example.backeendproject1.repository.post",
//                        "com.example.backeendproject1.repository.member"},
//        entityManagerFactoryRef = "entityManagerFactory",
//        transactionManagerRef =  "tmJpa"
//)
//public class JpaConfig {
//    @Bean
//    public DataSource dataSource(){
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setUsername("ajy8u9cn1h4pohh9e4pi");
//        dataSource.setPassword("pscale_pw_mMH67dgtpoKJXw6aom9s7ZadYESfbPVXbpzzTbAX3OV");
//        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://gcp.connect.psdb.cloud:3306/hunsd");
//        return dataSource;
//    }
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
//        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(dataSource);
//        em.setPackagesToScan("com.example.backeendproject1.repository.comment", "com.example.backeendproject1.repository.post", "com.example.backeendproject1.repository.member");
//
//        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        em.setJpaVendorAdapter(vendorAdapter);
//
//        Map<String, Object> properties = new HashMap<>();
//        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
//        properties.put("hibernate.format_sql", "true");
//        properties.put("hibernate.use_sql_comment", "true");
//
//        em.setJpaPropertyMap(properties);
//
//        return em;
//    }
//
//
//    @Bean(name = "tmJpa")
//    public JpaTransactionManager transactionManger(DataSource dataSource) {
//
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(entityManagerFactory(dataSource).getObject());
//        return transactionManager;
//    }
//}
