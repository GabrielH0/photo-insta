package com.social.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.InputStream;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class ImageFile {

    private String uniqueName;
    private String name;
    private InputStream content;
    private LocalDateTime includedAt;
}
