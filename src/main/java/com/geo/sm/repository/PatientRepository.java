package com.geo.sm.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Long> {

	public List<Patient> findByAdministeredBy(Doctor doctor);
	
	public List<Patient> findByFirstNameOrLastName(String name1, String name2);

	public Patient findByUserName(String name);

}
