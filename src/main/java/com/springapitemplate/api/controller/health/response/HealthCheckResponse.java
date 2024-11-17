package com.springapitemplate.api.controller.health.response;

import java.util.List;

import lombok.Getter;

@Getter
public class HealthCheckResponse {

    private final String health;
    private final List<String> activeProfiles;

    private HealthCheckResponse(String health, List<String> activeProfiles) {
        this.health = health;
        this.activeProfiles = activeProfiles;
    }

    public static HealthCheckResponse of(String health, List<String> activeProfiles) {
        return new HealthCheckResponse(health, activeProfiles);
    }

}
