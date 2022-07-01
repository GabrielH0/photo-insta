package com.social.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Post {

    private Long id;
    private User author;
    private String description;
    private Coordinates coordinates;
    private ImageFile image;

}
