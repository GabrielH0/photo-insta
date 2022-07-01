package com.social.infra.database.model;

import com.social.domain.model.Coordinates;
import com.social.domain.model.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Table(name = "Post")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostJpaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UserJpaModel author;

    private String description;

    @Embedded
    private Coordinates coordinates;

    @OneToOne(cascade = CascadeType.ALL)
    private S3File image;

    public Post toModel() {
        return Post.builder()
                .id(id)
                .author(author.toModel())
                .description(description)
                .coordinates(coordinates)
                .image(image.toModel())
                .build();
    }

    public static PostJpaModel fromModel(Post post) {
        return PostJpaModel.builder()
                .id(post.getId())
                .author(UserJpaModel.fromModel(post.getAuthor()))
                .description(post.getDescription())
                .coordinates(post.getCoordinates())
                .image(S3File.fromModel(post.getImage()))
                .build();
    }

}
