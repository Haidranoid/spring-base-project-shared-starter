package com.springbaseproject.sharedstarter.mappers;

public interface ResponseMapper<E, R> {
    R toResponse(E entity);
}

