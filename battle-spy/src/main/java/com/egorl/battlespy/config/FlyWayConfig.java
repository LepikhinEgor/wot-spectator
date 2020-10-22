package com.egorl.battlespy.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

public class FlyWayConfig {

//    private Environment environment;
//
//    @Autowired
//    public FlyWayConfig(Environment environment) {
//        this.environment = environment;
//    }
//
//    @EventListener
//    public void migrateDatabase(ApplicationReadyEvent e) {
//        System.out.println("------------------------------------------------------------");
//        System.out.println(environment.getProperty("spring.flyway.url"));
//        System.out.println("------------------------------------------------------------");
//        String url = environment.getProperty("spring.flyway.url");
//        String user = environment.getProperty("spring.flyway.user");
//        String password = environment.getProperty("spring.flyway.password");
//        Flyway flyway = Flyway.configure().dataSource(url, user, password).load();
//        flyway.migrate();
//    }
}
