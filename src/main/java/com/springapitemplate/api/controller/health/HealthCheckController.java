package com.springapitemplate.api.controller.health;

import java.util.Arrays;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.springapitemplate.api.ApiResponse;
import com.springapitemplate.api.controller.health.response.HealthCheckResponse;

@RequiredArgsConstructor
@RestController
public class HealthCheckController {

    private final Environment environment;

    @GetMapping("/api/v1/health")
    public ApiResponse<HealthCheckResponse> healthCheck() {
        return ApiResponse.ok(
                "API Health Check",
                HealthCheckResponse.of("OK", Arrays.asList(environment.getActiveProfiles()))
        );
    }

}
