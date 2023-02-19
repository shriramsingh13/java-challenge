package jp.co.axa.apidemo;

import jp.co.axa.apidemo.configuration.SpringConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * ApiDemoApplication : main method to startup this spring boot application
 *
 * @author shriram.singh
 */
@EnableSwagger2
@EnableCaching
@SpringBootApplication
@EnableConfigurationProperties(SpringConfigProperties.class)
public class ApiDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiDemoApplication.class, args);
    }

}
