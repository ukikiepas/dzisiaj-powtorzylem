package ukikiepas.dzisiajpowtorzylem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DzisiajPowtorzylemApplication {

    public static void main(String[] args) {
        SpringApplication.run(DzisiajPowtorzylemApplication.class, args);
    }

}
