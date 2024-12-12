package dasturlash.uz.config;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.sql.DataSource;

@Configuration
public class FlywayStarterConfig implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(FlywayStarterConfig.class);

    @Autowired
    private DataSource dataSource;

    @Override
    public void run(String... args) {
        try {
            Flyway.configure().baselineOnMigrate(true).dataSource(dataSource).load().migrate();

            logger.info("Flyway migration completed successfully");
        } catch (Exception e) {
            logger.error("Flyway migration failed", e);
            throw new RuntimeException("Flyway migration failed", e);
        }
    }
}