package com.springbaseproject.sharedstarter.services;

import java.util.Collection;
import java.util.List;

public interface ReadService<R, ID> {
    //Collection<R> findAll();
    //Page<R> findAll(Pageable pageable);
    List<R> findAll();
    R findById(ID id);
}
