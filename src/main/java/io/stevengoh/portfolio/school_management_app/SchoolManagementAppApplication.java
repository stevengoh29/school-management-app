package io.stevengoh.portfolio.school_management_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EnableAspectJAutoProxy
public class SchoolManagementAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolManagementAppApplication.class, args);
	}

}
