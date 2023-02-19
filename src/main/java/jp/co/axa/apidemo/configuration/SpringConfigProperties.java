package jp.co.axa.apidemo.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * This file is to get the h2 database details from application.properties
 *
 * @author shriram.singh
 */
@ConfigurationProperties("spring.datasource")
public class SpringConfigProperties {

    /**
     * H2 database connection url
     */
    @Getter
    @Setter
    private String url;

    /**
     * H2 database driver className
     */
    @Getter
    @Setter
    private String driverClassName;

    /**
     * H2 database username
     */
    @Getter
    @Setter
    private String user;

    /**
     * H2 database password
     */
    @Getter
    @Setter
    private String password;
}
