package com.springbaseproject.sharedstarter.mappers;

public interface Mapper<T, D> {
    D toDto(T entity);
    T toEntity(D dto);
}

