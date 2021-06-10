package com.wcp.common.base;

import com.wcp.coding.inputFile.CodeInputFile;

import java.util.List;
import java.util.Optional;

public interface CRUDService<T, D> {

    D save(D dto);

    D fetchById(String id);
    List<D> fetchAll();

    D update(D dto);

    D delete(D dto);

    void deleteById(String id);

    Long count();

}
