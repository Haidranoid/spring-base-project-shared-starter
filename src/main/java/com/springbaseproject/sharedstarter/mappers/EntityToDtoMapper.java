package com.springbaseproject.sharedstarter.mappers;

public interface EntityToDtoMapper<E, D> {
    D toDto(E entity);
}
