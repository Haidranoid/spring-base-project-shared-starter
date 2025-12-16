package com.springbaseproject.sharedstarter.services;

public interface BaseService<R, ID> extends ReadService<R, ID>, DeleteService<ID> {
}
