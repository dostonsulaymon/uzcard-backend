package dasturlash.uz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication(scanBasePackages = "dasturlash.uz")
@Slf4j
public class UzcardBackendApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(UzcardBackendApplication.class, args);
        Environment env = context.getEnvironment();

        log.info("✨ Uzcard Backend Application has successfully started!");
        log.info("Server is running on port: {}",
                env.getProperty("server.port"));
    }



}