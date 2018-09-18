package com.geo.sm.client;

import java.util.Collection;
import com.geo.sm.repository.Doctor;
import com.geo.sm.repository.Patient;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

public interface UserSvcApi {

	public static final String PATIENT_NAME_PARAMETER = "name";

	public static final String DOCTOR_SVC_PATH = "/doctor";
	public static final String PATIENT_SVC_PATH = "/patient";

	public static final String PATIENT_NAME_SEARCH_PATH = PATIENT_SVC_PATH
			+ "/find";

	@POST(PATIENT_SVC_PATH)
	public Patient addPatient(@Body Patient c);

	@POST(DOCTOR_SVC_PATH)
	public Doctor addDoctor(@Body Doctor c);

	@GET(DOCTOR_SVC_PATH + "/{id}/patients")
	public Collection<Patient> getPatientListByDoctorId(@Path("id") Long id);

	@GET(DOCTOR_SVC_PATH + "/{id}")
	public Doctor getDoctorById(@Path("id") Long id);

	@GET(PATIENT_SVC_PATH + "/{id}")
	public Patient getPatientById(@Path("id") Long id);

	@GET(PATIENT_NAME_SEARCH_PATH)
	public Collection<Patient> getPatientListByFirstOrLastName(
			@Query(PATIENT_NAME_PARAMETER) String name);
	
	@GET(PATIENT_SVC_PATH + "/findbyusername")
	public Patient getPatientByUserName(@Query(PATIENT_NAME_PARAMETER) String name);
	
	@GET(DOCTOR_SVC_PATH + "/findbyusername")
	public Doctor getDoctorByUserName(@Query(PATIENT_NAME_PARAMETER) String name);
}
