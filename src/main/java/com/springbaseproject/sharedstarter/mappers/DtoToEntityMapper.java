package com.springbaseproject.sharedstarter.mappers;

public interface DtoToEntityMapper<D, E> {
    E toEntity(D dto);
}
