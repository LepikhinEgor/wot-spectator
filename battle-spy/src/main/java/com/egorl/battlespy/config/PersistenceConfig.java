package com.egorl.battlespy.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EntityScan(basePackages = {"com.egorl.spectator.domain.entities"})
@EnableJpaRepositories(basePackages={"com.egorl.battlespy.repository"})
@EnableTransactionManagement
public class PersistenceConfig {
}
