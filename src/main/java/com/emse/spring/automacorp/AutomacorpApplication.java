package com.emse.spring.automacorp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.h2.tools.Server;
import java.sql.SQLException;

@SpringBootApplication
public class AutomacorpApplication {

	public static void main(String[] args) throws SQLException {
		Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092").start();

		// Start the Spring Boot application
		SpringApplication.run(AutomacorpApplication.class, args);
	}
}