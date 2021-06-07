package com.wcp.common.base;

import com.wcp.coding.inputFile.CodeInputFile;

import java.util.List;
import java.util.Optional;

public interface CRUDService<T> {

    T save(T entity);

    Optional<T> fetchById(String id);
    Optional<T> fetchById(Long id);
    List<T> fetchAll();

    T update(T entity);

    T delete(T entity);

    void deleteById(String id);
    void deleteById(Long id);

    Long count();

}
