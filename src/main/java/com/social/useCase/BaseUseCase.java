package com.social.useCase;

import java.util.List;

public interface BaseUseCase<T> {

    T findById(Long id);

    List<T> findAll();

    T save(T obj);
}
