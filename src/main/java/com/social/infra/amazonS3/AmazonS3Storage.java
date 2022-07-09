package com.social.infra.amazonS3;

import com.social.domain.adapter.ObjectStorage;
import com.social.domain.model.ImageFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class AmazonS3Storage implements ObjectStorage {

    private final S3Client s3Client;

    @Autowired
    public AmazonS3Storage(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    @Override
    public void putObject(ImageFile object) throws IOException {
        object.setUniqueName(getUniqueKey());
        s3Client.putObject(builder -> builder.bucket("photos-insta").key(object.getUniqueName()),
                RequestBody.fromBytes(object.getContent().readAllBytes()));
        s3Client.close();
    }

    @Override
    public InputStream getObject(String key) {
        return s3Client.getObjectAsBytes(builder -> builder.bucket("photos-insta")
                .key(key)).asInputStream();
    }

    private String getUniqueKey() {
        return LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
}
