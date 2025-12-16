package com.springbaseproject.sharedstarter.services;

public interface UpdateService<R, U, ID> {
    R update(ID id, U dto);
}
