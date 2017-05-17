package com.goldcn;

import com.goldcn.config.RabbitMqConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({RabbitMqConfig.class})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
