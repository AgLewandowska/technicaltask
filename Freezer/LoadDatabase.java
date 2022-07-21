package com.example.Freezer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase
{
    @Bean
    CommandLineRunner initDatabase(FreezerRepository repository) {
        return args -> {};
    }
}

