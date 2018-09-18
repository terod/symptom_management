package com.geo.sm.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalPrescriptionRepository extends
		CrudRepository<MedicalPrescription, Long> {

	public Collection<MedicalPrescription> findByPrescribedFor(Patient patient);

}
