package com.egorl.battle.generator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class BattleGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(BattleGeneratorApplication.class, args);
	}

}
