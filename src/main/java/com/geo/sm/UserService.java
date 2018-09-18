package com.geo.sm;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import retrofit.http.Query;

import com.geo.sm.client.UserSvcApi;
import com.geo.sm.repository.Doctor;
import com.geo.sm.repository.DoctorRepository;
import com.geo.sm.repository.Patient;
import com.geo.sm.repository.PatientRepository;

@Controller
public class UserService implements UserSvcApi {

	@Autowired
	private DoctorRepository doctorRepository;
	@Autowired
	private PatientRepository patientRepository;

	@RequestMapping(value = UserSvcApi.DOCTOR_SVC_PATH, method = RequestMethod.POST)
	public @ResponseBody Doctor addDoctor(@RequestBody Doctor v) {
		return doctorRepository.save(v);
	}

	@RequestMapping(value = UserSvcApi.DOCTOR_SVC_PATH + "/{id}/patients", method = RequestMethod.GET)
	public @ResponseBody Collection<Patient> getPatientListByDoctorId(
			@PathVariable("id") Long id) {
		Doctor doctor = doctorRepository.findOne(id);
		return patientRepository.findByAdministeredBy(doctor);
	}

	@RequestMapping(value = UserSvcApi.DOCTOR_SVC_PATH + "/{id}", method = RequestMethod.GET)
	public @ResponseBody Doctor getDoctorById(@PathVariable("id") Long id) {
		return doctorRepository.findOne(id);
	}

	// patients

	@RequestMapping(value = UserSvcApi.PATIENT_SVC_PATH, method = RequestMethod.POST)
	public @ResponseBody Patient addPatient(@RequestBody Patient v) {
		return patientRepository.save(v);
	}

	@RequestMapping(value = UserSvcApi.PATIENT_SVC_PATH + "/{id}", method = RequestMethod.GET)
	public @ResponseBody Patient getPatientById(@PathVariable("id") Long id) {
		return patientRepository.findOne(id);
	}

	@RequestMapping(value = UserSvcApi.PATIENT_NAME_SEARCH_PATH, method = RequestMethod.GET)
	public @ResponseBody Collection<Patient> getPatientListByFirstOrLastName(
			@Query(PATIENT_NAME_PARAMETER) String name) {
		return patientRepository.findByFirstNameOrLastName(name, name);
	}

	@RequestMapping(value = UserSvcApi.PATIENT_SVC_PATH + "/findbyusername", method = RequestMethod.GET)
	public @ResponseBody Patient getPatientByUserName(
			@Query(PATIENT_NAME_PARAMETER) String name) {
		return patientRepository.findByUserName(name);
	}

	@RequestMapping(value = UserSvcApi.DOCTOR_SVC_PATH + "/findbyusername", method = RequestMethod.GET)
	public @ResponseBody Doctor getDoctorByUserName(
			@Query(PATIENT_NAME_PARAMETER) String name) {
		return doctorRepository.findByUserName(name);
	}
}
