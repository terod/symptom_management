package com.geo.sm.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QaTypeRepository extends CrudRepository<QaType, Long> {

}
