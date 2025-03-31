package com.rightings.sdk;

import com.rightings.sdk.client.ImagsClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("imags.api")
@ComponentScan
@Data
public class ImagsApiClientAutoConfiguration {

    private String accessKey;
    private String secretKey;


    @Bean
    public ImagsClient ImagsApiClientAutoConfiguration() {

        return new ImagsClient(accessKey, secretKey);
    }

}
