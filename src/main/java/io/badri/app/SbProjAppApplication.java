package io.badri.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.social.config.annotation.EnableSocial;

@SpringBootApplication
@EnableSocial // for social integrations
@EnableAsync(proxyTargetClass = true) // making asynchronous
public class SbProjAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbProjAppApplication.class, args);
	}

}
