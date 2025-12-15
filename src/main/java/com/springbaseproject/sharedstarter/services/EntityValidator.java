package com.springbaseproject.sharedstarter.services;

public interface EntityValidator<T> {
    boolean validateEntity(T entity);
}

