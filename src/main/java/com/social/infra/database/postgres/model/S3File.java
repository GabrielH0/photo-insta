package com.social.infra.database.postgres.model;

import com.social.domain.model.ImageFile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Table(name = "s3File")
@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class S3File {

    @Id
    private String uniqueName;
    private String fileName;
    private LocalDateTime includedAt;

    public static S3File fromModel(ImageFile image) {
        return S3File.builder()
                .uniqueName(image.getUniqueName())
                .fileName(image.getName())
                .includedAt(LocalDateTime.now())
                .build();
    }

    public ImageFile toModel() {
        return ImageFile.builder()
                .uniqueName(uniqueName)
                .name(fileName)
                .includedAt(includedAt)
                .build();
    }
}
