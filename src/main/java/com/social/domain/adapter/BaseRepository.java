package com.social.domain.adapter;

import java.util.List;

public interface BaseRepository<T> {

    T findById(Long id);

    List<T> findAll();

    T save(T object);
}
