package school.newton.sysjs2.grupp3.UAR;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

@SpringBootApplication(exclude = ErrorMvcAutoConfiguration.class)
public class UarApplication {

	//Start
	public static void main(String[] args) {
		SpringApplication.run(UarApplication.class, args);
	}

}
