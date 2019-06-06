package kr.ac.jejunu.polls;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PollsApplication {

    public static void main(String[] args) {
        SpringApplication.run(PollsApplication.class, args);
    }

}
