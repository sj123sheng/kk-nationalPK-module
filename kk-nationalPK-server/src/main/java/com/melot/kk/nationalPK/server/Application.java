package com.melot.kk.nationalPK.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

@MapperScan(basePackages = {"com.melot.kk.nationalPK.server.dao"},
        sqlSessionFactoryRef = "sqlSessionFactory_masterPg")
@ImportResource(locations={"classpath*:conf/disconf.xml"})
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

