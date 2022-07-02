package com.social.infra.database.amazonS3;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import software.amazon.awssdk.regions.Region;

@Configuration
public class S3ClientConfiguration {

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public software.amazon.awssdk.services.s3.S3Client s3Client() {
        return software.amazon.awssdk.services.s3.S3Client.builder().region(Region.SA_EAST_1).build();
    }
}
