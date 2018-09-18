package com.geo.sm;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.geo.sm.client.SMFuncSvcApi;
import com.geo.sm.client.UserSvcApi;
import com.geo.sm.repository.CheckIn;
import com.geo.sm.repository.CheckInRepository;
import com.geo.sm.repository.Doctor;
import com.geo.sm.repository.DoctorRepository;
import com.geo.sm.repository.MedicalPrescription;
import com.geo.sm.repository.MedicalPrescriptionRepository;
import com.geo.sm.repository.Patient;
import com.geo.sm.repository.PatientRepository;
import com.geo.sm.repository.QaRepository;
import com.geo.sm.repository.QaTypeRepository;

@Controller
public class SMFuncService implements SMFuncSvcApi {

	@Autowired
	private DoctorRepository doctorRepository;
	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	private CheckInRepository checkInRespository;
	@Autowired
	private QaRepository qaRepository;
	@Autowired
	private QaTypeRepository qaTypeRepository;
	@Autowired
	private MedicalPrescriptionRepository medicalPrescriptionRepository;

	@RequestMapping(value = UserSvcApi.PATIENT_SVC_PATH + "/{id}/checkins", method = RequestMethod.GET)
	public @ResponseBody Collection<CheckIn> getCheckInsByPatientId(
			@PathVariable("id") Long id) {
		Patient patient = patientRepository.findOne(id);
		return checkInRespository.findByCheckInBy(patient);
	}

	@RequestMapping(value = UserSvcApi.PATIENT_SVC_PATH + "/{id}/medicines", method = RequestMethod.GET)
	public @ResponseBody Collection<MedicalPrescription> getMedicinesByPatientId(@PathVariable("id") Long id) {
		Patient patient = patientRepository.findOne(id);
		return medicalPrescriptionRepository.findByPrescribedFor(patient);
	}
	
	@RequestMapping(value = SMFuncSvcApi.MEDICINE_SVC_PATH, method = RequestMethod.POST)
	public @ResponseBody MedicalPrescription addMedicine(@RequestBody MedicalPrescription v) {
		return medicalPrescriptionRepository.save(v);
	}
}
