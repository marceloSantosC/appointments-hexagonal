package com.example.appointments;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

@SpringBootApplication
public class AppointmentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppointmentsApplication.class, args);
	}

	@Bean
	public BeanFactoryPostProcessor beanFactoryPostProcessor(ApplicationContext applicationContext) {
		return beanFactory -> registerDomainBeans(
				(BeanDefinitionRegistry) ((AnnotationConfigServletWebServerApplicationContext) applicationContext)
						.getBeanFactory());
	}

	private void registerDomainBeans(BeanDefinitionRegistry registry) {
		ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(registry);
		scanner.addIncludeFilter(serviceClassesFilter());
		scanner.scan("com.example.appointments.application.domain.service");
	}

	private static TypeFilter serviceClassesFilter() {
		return (MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) -> metadataReader
				.getClassMetadata().getClassName().endsWith("Service");
	}
}
