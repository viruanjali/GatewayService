package com.amsidh.mvc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.core.env.Environment;

import static java.lang.String.format;

@Slf4j
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class GatewayServiceApplication implements CommandLineRunner {

	@Autowired(required = true)
	private Environment environment;

	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info(format("Read Property 'api.users-ws.actuator.url.path': %s", this.environment.getProperty("api.users-ws.actuator.url.path")));
	}
}