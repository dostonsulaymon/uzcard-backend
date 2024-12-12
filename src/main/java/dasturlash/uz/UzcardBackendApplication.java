package dasturlash.uz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "dasturlash.uz")
public class UzcardBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(UzcardBackendApplication.class, args);
    }

}
