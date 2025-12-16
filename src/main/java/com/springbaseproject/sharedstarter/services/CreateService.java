package com.springbaseproject.sharedstarter.services;

public interface CreateService<R, C> {
    R create(C dto);
}
