package demo;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.service.GenericService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author shenluw
 * @date 2021/6/30 20:06
 */
@SpringBootApplication
@EnableScheduling
public class Application {

  public static void main(String[] args) {
    new SpringApplication(Application.class)
        .run(args);
  }

  @Service
  public static class Worker {

    ApplicationConfig applicationConfig;
    RegistryConfig registryConfig;

    public Worker(ApplicationConfig applicationConfig,
        RegistryConfig registryConfig) {
      this.applicationConfig = applicationConfig;
      this.registryConfig = registryConfig;
    }

    @Scheduled(fixedDelay = 4_000L)
    void invoke() {
      System.out.println("start invoke");

      ReferenceConfig<GenericService> config = build();
      GenericService genericService = config.get();
      Object ret = genericService.$invoke("work", new String[]{
          String.class.getName(),
          List.class.getName()

      }, new Object[]{
          "time " + System.currentTimeMillis(), Arrays.asList(1, 2, 3)
      });
      System.out.println(ret);
    }

    public ReferenceConfig<GenericService> build() {
      ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
      reference.setGeneric(true);
      reference.setApplication(applicationConfig);
      reference.setRegistry(registryConfig);
      reference.setInterface("demo.BiService");
      reference.setProtocol("dubbo");
      try {
        Object obj = reference.get();
      } catch (Exception e) {
        System.out.println("init alibaba dubbo refernce ex");
        e.printStackTrace();
      }

      return reference;
    }

  }

}
