package com.social.application.model;

import com.social.domain.model.Coordinates;
import com.social.domain.model.ImageFile;
import com.social.domain.model.Post;
import com.social.domain.model.User;
import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.Serializable;

@Builder
public record PostDto(Long id, String description,
                      String lat,
                      String log,
                      MultipartFile image,
                      Long userId) implements Serializable {
    public Post toModel() throws IOException {
        return Post.builder()
                .id(id)
                .author(User.builder().id(userId).build())
                .description(description)
                .coordinates(Coordinates.builder().lat(lat).log(log).build())
                .image(ImageFile.builder().name(image.getName()).content(image.getInputStream()).build())
                .build();
    }
}
