package com.social.infra.database.postgres.model;

import com.social.domain.model.User;
import lombok.*;

import javax.persistence.*;

@Table(name = "user_")
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserJpaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email;

    public static UserJpaModel fromModel(User author) {
        return  UserJpaModel.builder()
                .id(author.getId())
                .username(author.getUsername())
                .email(author.getEmail())
                .build();
    }

    public User toModel() {
        return User.builder()
                .id(id)
                .username(username)
                .email(email)
                .build();
    }
}
