package com.social.domain.model;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Coordinates {

    private String lat;
    private String log;
}
