package org.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.test.service.CallRaterService;

@Configuration
public class AppConfig {

    @Bean
    public CallRaterService callRaterService() {
        return new CallRaterService();
    }
}
