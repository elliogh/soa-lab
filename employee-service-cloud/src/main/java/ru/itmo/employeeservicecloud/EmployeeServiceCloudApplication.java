package ru.itmo.employeeservicecloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class EmployeeServiceCloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeServiceCloudApplication.class, args);
	}

}
