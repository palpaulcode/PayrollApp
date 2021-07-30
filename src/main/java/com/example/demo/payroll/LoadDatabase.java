package com.example.demo.payroll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository employeeRepository,
                                   OrderRepository orderRepository){
        return args -> {
            log.info("Preloading " + employeeRepository.save(new Employee(
                    "Bilbo", "Baggins", "Burglar")));
            log.info("Preloading " + employeeRepository.save(new Employee(
                    "Frodo", "Baggins", "Thief")));

            employeeRepository.findAll().forEach(employee -> log.info("preloaded " + employee));

            orderRepository.save(new Order("MacBook Pro", Status.COMPLETED));
            orderRepository.save(new Order("iPhone", Status.IN_PROGRESS));
            orderRepository.findAll().forEach(order -> {
                log.info("Preloaded " +  order);
            });
        };
    }
}
