package com.emse.spring.automacorp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.emse.spring.automacorp.hello.GreetingService;

@Configuration // (1)
public class AutomacorpApplicationConfig {

    // (2)
    @Bean
    public CommandLineRunner greetingCommandLine(GreetingService greetingService) { // (3)
        return args -> {
            // (4)
            greetingService.greet("Spring");
        };
    }
}
