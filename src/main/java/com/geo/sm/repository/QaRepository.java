package com.geo.sm.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QaRepository extends CrudRepository<Qa, Long> {
	public Collection<Qa> findByBelongsTo(CheckIn checkin);
}
