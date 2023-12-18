package com.pankiba.employeeservice;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.SpringVersion;
import org.springframework.core.env.Environment;

import com.pankiba.employeeservice.domain.Employee;
import com.pankiba.employeeservice.domain.Grade;
import com.pankiba.employeeservice.repository.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
public class EmployeeServiceApplication {

	private static final String SPRING_PROFILE_DEFAULT = "spring.profiles.default";
	public static final String DEVELOPMENT_PROFILE = "dev";

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(EmployeeServiceApplication.class);

		/*
		 * setting default profile as DEV if no other profile is configured. This needs to be done before calling run
		 * method on SpringApplication
		 */
		Map<String, Object> defaultProperties = new HashMap<>();
		defaultProperties.put(SPRING_PROFILE_DEFAULT, DEVELOPMENT_PROFILE);
		springApplication.setDefaultProperties(defaultProperties);

		Environment environment = springApplication.run(args).getEnvironment();
		logApplicationStartup(environment);

	}

	@Bean
	public CommandLineRunner populateTestData(EmployeeRepository employeeRepository) {
		return (String... args) -> {

			List<Employee> employeeList = new ArrayList<>();

			employeeList.add(new Employee("John", "McLane", "M", "john.mclane@users.noreply.github.com",
					DateUtils.parseDate("7/31/1970", "MM/dd/yyyy"), DateUtils.parseDate("7/27/2008", "MM/dd/yyyy"),
					Grade.Developer, 51063L, 0L));

			employeeList.add(new Employee("Ethan", "Hunt", "M", "ethan.hunt@yahoo.com",
					DateUtils.parseDate("9/27/1982", "MM/dd/yyyy"), DateUtils.parseDate("7/22/2005", "MM/dd/yyyy"),
					Grade.Lead, 72305L, 0L));

			employeeList.add(new Employee("Jery", "Maguire", "M", "jery.maguire@yahoo.com",
					DateUtils.parseDate("6/9/1959", "MM/dd/yyyy"), DateUtils.parseDate("4/27/1994", "MM/dd/yyyy"),
					Grade.Architect, 164143L, 0L));

			employeeRepository.saveAll(employeeList);
		};
	}

	public static void logApplicationStartup(Environment environment) {

		String protocol = "http";
		if (environment.getProperty("server.ssl.key-store") != null) {
			protocol = "https";
		}

		String serverPort = environment.getProperty("server.port");
		String contextPath = environment.getProperty("server.servlet.context-path");

		if (StringUtils.isBlank(contextPath)) {
			contextPath = "/";
		}

		String hostAddress = "localhost";
		try {
			hostAddress = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException unknownHostException) {
			log.warn("The host name could not be determined, using 'localhost' as fallback");
		}

		log.info("Spring Version : {}, Spring-Boot Ver : {}", SpringVersion.getVersion(),
				SpringBootVersion.getVersion());

		String[] profiles = environment.getActiveProfiles();
		if (profiles.length == 0) {
			log.info("No active profile set, falling back to default profiles: {} ",
					Arrays.toString(environment.getDefaultProfiles()));
			profiles = environment.getDefaultProfiles();
		}

		log.info(
				"\n--------------------------------------------------------------------\n\t"
						+ "Application '{}' is running! Access URLs:\n\t" + "Local: \t\t{}://localhost:{}{}\n\t"
						+ "External: \t{}://{}:{}{}\n\t"
						+ "Profile(s): \t{}\n--------------------------------------------------------------------",
				environment.getProperty("spring.application.name"), protocol, serverPort, contextPath, protocol,
				hostAddress, serverPort, contextPath, profiles);

	}

}
