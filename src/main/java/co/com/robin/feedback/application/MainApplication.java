package co.com.robin.feedback.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@ComponentScan({ "co.com.robin.feedback "})
@EntityScan(basePackages = "co.com.robin.feedback.domain")
@EnableMongoRepositories({ "co.com.robin.feedback.infrastructure" })
@SpringBootApplication(scanBasePackages = "co.com.robin.feedback.infrastructure", exclude = { DataSourceAutoConfiguration.class })
public class MainApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

}
