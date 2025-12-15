package com.springbaseproject.sharedstarter.services;

import java.util.Collection;
import java.util.Optional;

public interface CrudService<D, ID> {
    Collection<D> findAll();

    Optional<D> findById(ID id);

    D create(D dto);

    D update(ID id, D dto);

    void delete(ID id);

    //Page<D> findAll(Pageable pageable);
}
