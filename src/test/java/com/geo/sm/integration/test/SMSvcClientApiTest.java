package com.geo.sm.integration.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.geo.sm.TestData;
import com.geo.sm.client.CheckInSvcApi;
import com.geo.sm.client.SMFuncSvcApi;
import com.geo.sm.client.UserSvcApi;
import com.geo.sm.https.UnsafeHttpsClient;
import com.geo.sm.oauth.SecuredRestBuilder;
import com.geo.sm.repository.CheckIn;
import com.geo.sm.repository.Doctor;
import com.geo.sm.repository.MedicalPrescription;
import com.geo.sm.repository.Patient;
import com.geo.sm.repository.Qa;

import retrofit.RestAdapter.LogLevel;
import retrofit.client.ApacheClient;

public class SMSvcClientApiTest {

	private final String TEST_URL = "https://localhost:8443";

	private CheckInSvcApi checkInService = new SecuredRestBuilder()
			.setLoginEndpoint(TEST_URL + SMFuncSvcApi.TOKEN_PATH)
			.setClient(new ApacheClient(UnsafeHttpsClient.createUnsafeClient()))
			.setUsername("admin")
			.setPassword("pass")
			.setClientId("mobile")
			.setClient(new ApacheClient(UnsafeHttpsClient.createUnsafeClient()))
			.setEndpoint(TEST_URL).setLogLevel(LogLevel.FULL).build()
			.create(CheckInSvcApi.class);

	private UserSvcApi userService = new SecuredRestBuilder()
			.setLoginEndpoint(TEST_URL + SMFuncSvcApi.TOKEN_PATH)
			.setClient(new ApacheClient(UnsafeHttpsClient.createUnsafeClient()))
			.setUsername("admin")
			.setPassword("pass")
			.setClientId("mobile")
			.setClient(new ApacheClient(UnsafeHttpsClient.createUnsafeClient()))
			.setEndpoint(TEST_URL).setLogLevel(LogLevel.FULL).build()
			.create(UserSvcApi.class);

	private SMFuncSvcApi smFuncService = new SecuredRestBuilder()
			.setLoginEndpoint(TEST_URL + SMFuncSvcApi.TOKEN_PATH)
			.setClient(new ApacheClient(UnsafeHttpsClient.createUnsafeClient()))
			.setUsername("admin")
			.setPassword("pass")
			.setClientId("mobile")
			.setClient(new ApacheClient(UnsafeHttpsClient.createUnsafeClient()))
			.setEndpoint(TEST_URL).setLogLevel(LogLevel.FULL).build()
			.create(SMFuncSvcApi.class);

	private CheckIn checkIn = TestData.randomCheckIn();

	/**
	 * This test creates a Video, adds the Video to the VideoSvc, and then
	 * checks that the Video is included in the list when getVideoList() is
	 * called.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testPatientDoctorAdd() throws Exception {
		// create a Doctor
		Doctor doctor = new Doctor();
		doctor.setDoctorId("DOC102");
		doctor.setFirstName("Ruth");
		doctor.setLastName("Abraham");
		doctor.setPassword("somepassword");
		doctor.setUserName("doc2");
		doctor = userService.addDoctor(doctor);
		// creating a patient
		Patient user = new Patient();
		user.setFirstName("Will");
		user.setLastName("Smith");
		user.setUserName("will");
		user.setPassword("somepassword");
		user.setDob(new Date().getTime());
		user.setMedicalRecordNumber("P100");
		user.setAdministeredBy(doctor);
		Patient user1 = userService.addPatient(user);

		// We should get back the video that we added above
		// Collection<CheckIn> checkIns = checkInService.getAllCheckIns();
		assertTrue(user.equals(user1));
	}

	@Test
	public void testAddDoctor() throws Exception {
		Doctor doctor = userService.getDoctorById(1l);
		assertTrue(doctor.getDoctorId().equals("DOC111"));
	}

	@Test
	public void testGetPatientListUnderDoctor() throws Exception {
		Collection<Patient> patientsUnderDoctor = userService
				.getPatientListByDoctorId(1l);

		assertFalse(patientsUnderDoctor.isEmpty());
	}

	@Test
	public void testAddCheckIn() throws Exception {
		CheckIn checkIn1 = new CheckIn();
		checkIn1.setDateTime(new Date().getTime());
		checkIn1.setImagePath("\\2.png");
		checkIn1.setCheckInBy(userService.getPatientById(2l));
		checkIn1 = checkInService.addCheckIn(checkIn1);

		Qa qa1 = new Qa();
		qa1.setQuestion("Did you take your lortab");
		qa1.setAnswer("Yes");
		qa1.setBelongsTo(checkIn1);
		qa1.setQuestionType(checkInService.getQaTypeById(3l));
		assertTrue(checkInService.addQa(qa1).equals(qa1));
	}

	@Test
	public void testPatientListDownload() throws Exception {
		List<Patient> patients = (List<Patient>) userService
				.getPatientListByDoctorId(1l);
		assertTrue(patients.size() != 0);
	}

	@Test
	public void testSearchByName() throws Exception {
		List<Patient> patients = (List<Patient>) userService
				.getPatientListByFirstOrLastName("FirstName5");
		assertFalse(patients.isEmpty());
	}

	@Test
	public void testGetCheckInsByPatient() throws Exception {
		Patient patient = userService.getPatientByUserName("jack");
		List<CheckIn> checkins = (List<CheckIn>) smFuncService
				.getCheckInsByPatientId(patient.getId());
		System.out.println(checkins.size());
		assertTrue(checkins.size() != 0);
	}

	@Test
	public void testGetCheckInsByPatientAndGetQuestion() throws Exception {
		Patient patient = userService.getPatientByUserName("jack");
		List<CheckIn> checkins = (List<CheckIn>) smFuncService
				.getCheckInsByPatientId(patient.getId());
		System.out.println(checkins.size());
		assertTrue(checkins.size() != 0);

		List<Qa> qas = (List<Qa>) checkInService.getQasByCheckInId(checkins
				.get(0).getId());
		System.out.println(checkins.size());
		assertTrue(qas.size() != 0);
	}

	@Test
	public void testFetchMedicalListforPatient() {
		Patient patient = userService.getPatientByUserName("jack");
		List<MedicalPrescription> medicines = (List<MedicalPrescription>) smFuncService
				.getMedicinesByPatientId(patient.getId());
		assertTrue(medicines.size() != 0);
	}

	@Test
	public void testGetPatientDoctorByUserName() {
		Patient patient = userService.getPatientByUserName("jack");
		assertTrue(patient.getUserName().endsWith("jack"));

		Doctor doctor = userService.getDoctorByUserName("doc1");
		assertTrue(doctor.getUserName().equals("doc1"));
	}
	
	@Test
	public void testAddMedicine() {
		Patient patient = userService.getPatientByUserName("jack");
		assertTrue(patient.getUserName().endsWith("jack"));

		Doctor doctor = userService.getDoctorByUserName("doc1");
		assertTrue(doctor.getUserName().equals("doc1"));
		
		MedicalPrescription medicalPrescription = new MedicalPrescription();
		medicalPrescription.setMedicineName("RtroCure");
		medicalPrescription.setPrescribedBy(doctor);
		medicalPrescription.setPrescribedFor(patient);
		MedicalPrescription medicalPrescriptionReturn = smFuncService.addMedicine(medicalPrescription);
		
		assertTrue(medicalPrescription.equals(medicalPrescriptionReturn));
	}

}
