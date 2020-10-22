package com.egorl.battle.generator.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MicroservicesProperties {

    @Value("${spectator.spy.url}")
    private String spyUrl;

    public String getSpyUrl() {
        return spyUrl;
    }

    public void setSpyUrl(String spyUrl) {
        this.spyUrl = spyUrl;
    }
}
