package com.example.demo;

import com.example.demo.model.RoleFormatter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SpringBootApplication
public class DemoStaticApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoStaticApplication.class, args);
	}

	@Configuration
	static class WebConfig implements WebMvcConfigurer {

		@PersistenceContext
		private EntityManager entityManager;

		@Override
		public void addFormatters(FormatterRegistry formatterRegistry) {
			formatterRegistry.addFormatter(new RoleFormatter(entityManager));
		}

	}

	@Bean
	HiddenHttpMethodFilter hiddenHttpMethodFilter() {
		return new HiddenHttpMethodFilter();
	}
}
