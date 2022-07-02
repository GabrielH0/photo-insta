package com.social.infra.database.amazonS3;

import com.social.domain.adapter.ObjectStorage;
import com.social.domain.model.ImageFile;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class AmazonS3Storage implements ObjectStorage {

    @Override
    public void putObject(ImageFile object) throws IOException {
        object.setUniqueName(getUniqueKey());
        S3Client s3 = S3Client.builder().region(Region.SA_EAST_1).build();
        s3.putObject(builder -> builder.bucket("photos-insta").key(object.getUniqueName()),
                RequestBody.fromBytes(object.getContent().readAllBytes()));
        s3.close();
    }

    @Override
    public InputStream getObject(String key) {
        S3Client s3Client = S3Client.builder().region(Region.SA_EAST_1).build();
        return s3Client.getObjectAsBytes(builder -> builder.bucket("photos-insta")
                .key(key)).asInputStream();
    }

    private String getUniqueKey() {
        return LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
}
