package com.geo.sm.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DBPopulation {
	@Autowired
	private PatientRepository patientRespository;
	@Autowired
	private DoctorRepository doctorRespository;
	@Autowired
	private QaRepository qaRepository;
	@Autowired
	private QaTypeRepository qaTypeRepository;
	@Autowired
	private CheckInRepository checkInRepository;
	@Autowired
	private MedicalPrescriptionRepository medicalPrescriptionRepository;

	public PatientRepository getPatientRespository() {
		return patientRespository;
	}

	public void setPatientRespository(PatientRepository patientRespository) {
		this.patientRespository = patientRespository;
	}

	public DoctorRepository getDoctorRespository() {
		return doctorRespository;
	}

	public void setDoctorRespository(DoctorRepository doctorRespository) {
		this.doctorRespository = doctorRespository;
	}

	@PostConstruct
	public void populateDB() throws IOException {
		
		//doctor and patient
		Doctor doctor1 = new Doctor();
		doctor1.setDoctorId("DOC111");
		doctor1.setUserName("doc1");
		doctor1.setFirstName("Paul");
		doctor1.setLastName("John");
		doctor1.setPassword("somepassword");
		doctorRespository.save(doctor1);
		Patient patient = loadPatientDetails(doctor1);
		
		//checkin and qa
		loadqaDetails(patient);
		
		//loading medicines for patient
		loadMedineDetails(patient, doctor1);
		
		
		
	}
	

	private void loadMedineDetails(Patient patient, Doctor doctor) {
		MedicalPrescription medicine1 = new MedicalPrescription();
		medicine1.setMedicineName("Paracetamol");
		medicine1.setPrescribedFor(patient);
		medicine1.setPrescribedBy(doctor);
		medicalPrescriptionRepository.save(medicine1);
		MedicalPrescription medicine2 = new MedicalPrescription();
		medicine2.setMedicineName("Oxymorin");
		medicine2.setPrescribedFor(patient);
		medicine2.setPrescribedBy(doctor);
		medicalPrescriptionRepository.save(medicine2);
	}

	private void loadqaDetails(Patient patient) {
		QaType qaType1 = new QaType();
		qaType1.setId(1l);
		qaType1.setType("type1");
		qaType1.setDescription("Pain status");
		qaTypeRepository.save(qaType1);
		QaType qaType2 = new QaType();
		qaType2.setId(2l);
		qaType2.setType("type2");
		qaType2.setDescription("Drinking\\Easting status");
		qaTypeRepository.save(qaType2);
		QaType qaType3 = new QaType();
		qaType3.setId(3l);
		qaType3.setType("type3");
		qaType3.setDescription("Other questions");
		qaTypeRepository.save(qaType3);
		
		CheckIn checkIn1 = new CheckIn();
		checkIn1.setDateTime(new Date().getTime());
		checkIn1.setImagePath("1.png");
		checkIn1.setCheckInBy(patient);
		checkIn1.setImagePath("1.png");
		checkIn1.setTimeOfConsume("21 Jan 2014");
		// File file = new File("images\\extjsfirstlook.jpg"); //windows
		File file = new File("1.png");
		byte[] bFile = new byte[(int) file.length()];

		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			fileInputStream.read(bFile);
			fileInputStream.close();
			//checkIn1.setImage(new Base64().encodeToString(bFile));
		} catch (Exception e) {
			e.printStackTrace();
		}
		checkInRepository.save(checkIn1);
		
		Qa qa1 = new Qa();
		qa1.setQuestion("Did you take your lortab");
		qa1.setAnswer("Yes");
		qa1.setBelongsTo(checkIn1);
		qa1.setQuestionType(qaType3);
		Qa qa2 = new Qa();
		qa2.setQuestion("Did you take your Crocin");
		qa2.setAnswer("No");
		qa2.setBelongsTo(checkIn1);
		qa2.setQuestionType(qaType3);
		Qa qa3 = new Qa();
		qa3.setQuestion("Did you take your OxyCortin");
		qa3.setAnswer("Yes");
		qa3.setBelongsTo(checkIn1);
		qa3.setQuestionType(qaType3);
		Qa qa4 = new Qa();
		qa4.setQuestion("How bad is your mouth pain/sore throat");
		qa4.setAnswer("Moderate");
		qa4.setBelongsTo(checkIn1);
		qa4.setQuestionType(qaType1);
		Qa qa5 = new Qa();
		qa5.setQuestion("Does your pain stop you from eating/driking");
		qa5.setAnswer("I cannot eat");
		qa5.setBelongsTo(checkIn1);
		qa5.setQuestionType(qaType2);
		
		ArrayList<Qa> listOfQuestions = new ArrayList<Qa>();
		listOfQuestions.add(qa1);
		listOfQuestions.add(qa2);
		listOfQuestions.add(qa3);
		listOfQuestions.add(qa4);
		listOfQuestions.add(qa5);
		
		qaRepository.save(listOfQuestions);
	}

	private Patient loadPatientDetails(Doctor doctor) {
		
		
		Patient user = null;
		for(int i = 1 ; i <= 10 ; i++){
			user = new Patient();
			user.setFirstName("FirstName" + i);
			user.setLastName("SecondName" + i);
			user.setMedicalRecordNumber("M00" + i);
			user.setUserName(UUID.randomUUID().toString());
			user.setPassword(UUID.randomUUID().toString());
			user.setDob(new Date().getTime());
			user.setAdministeredBy(doctor);
			patientRespository.save(user);
		}
		user = new Patient();
		user.setFirstName("Jack");
		user.setLastName("Sparrow");
		user.setUserName("jack");
		user.setPassword("somepassword");
		user.setDob(new Date().getTime());
		user.setMedicalRecordNumber("P101");
		user.setAdministeredBy(doctor);
		patientRespository.save(user);
		return user;
	}
}
