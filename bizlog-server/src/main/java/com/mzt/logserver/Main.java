package com.mzt.logserver;

import cn.hutool.extra.spring.EnableSpringUtil;
import com.mzt.logapi.starter.annotation.EnableLogRecord;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

@SpringBootApplication(exclude = {DataSourceTransactionManagerAutoConfiguration.class})
@MapperScan(basePackages = "com.mzt.logserver.repository.mapper", annotationClass = Mapper.class)
@EnableLogRecord(tenant = "", joinTransaction = true)
@EnableTransactionManagement(order = 0)
@EnableSpringUtil
public class Main {
    @Autowired
    private DataSource dataSource;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @PostConstruct
    public void showConnectionInfo() throws SQLException {
        Connection conn = dataSource.getConnection();
        DatabaseMetaData metaData = conn.getMetaData();
        System.out.println("Connected to: " + metaData.getURL());
        System.out.println("Driver: " + metaData.getDriverName());
        conn.close();
    }
}
