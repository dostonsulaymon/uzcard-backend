package dasturlash.uz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "dasturlash.uz")
public class UzcardBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(UzcardBackendApplication.class, args);

        System.out.println(100 + 100 +"dasturlsh.uz");
        System.out.println("dasturlash.uz" + 100 + 100);
        System.out.println("dasturlash.uz" + 10*5);
        System.out.println(2*5+"dasturlash.uz");
    }

}
