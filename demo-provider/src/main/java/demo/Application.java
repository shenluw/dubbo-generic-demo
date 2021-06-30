package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author shenluw
 * @date 2021/6/30 20:03
 */
@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    new SpringApplication(Application.class).run(args);
  }
}
