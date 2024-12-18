package com.springapitemplate.support.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import feign.Logger;
import feign.Retryer;
import feign.codec.ErrorDecoder;

import com.springapitemplate.support.error.FeignClientExceptionErrorDecoder;

@Configuration
@EnableFeignClients(basePackages = "com.springapitemplate") // TODO: basePackages 수정
@Import(FeignClientProperties.FeignClientConfiguration.class)
public class OpenFeignConfig {

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new FeignClientExceptionErrorDecoder();
    }

    @Bean
    public Retryer retryer() {
        return new Retryer.Default(1000, 2000, 3);
    }

}
