package com.github.dhslrl321.pagination;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@SpringBootApplication
public class DataSourceBean4Test {
    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/my-test-db");
        config.setUsername("my-test-db");
        config.setPassword("my-test-db");
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setMaximumPoolSize(5); // 최대 커넥션 풀 크기
        config.setMinimumIdle(2); // 최소 커넥션 풀 크기
        config.setIdleTimeout(30000); // 유휴 커넥션 대기 시간 (30초)
        config.setConnectionTimeout(20000); // 커넥션 타임아웃 (20초)

        return new HikariDataSource(config);
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}
