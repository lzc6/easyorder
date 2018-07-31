package zqeasyorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class ZqeasyorderApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZqeasyorderApplication.class, args);
	}
}
