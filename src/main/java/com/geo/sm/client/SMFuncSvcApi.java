package com.geo.sm.client;

import java.util.Collection;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

import com.geo.sm.repository.CheckIn;
import com.geo.sm.repository.MedicalPrescription;
import com.geo.sm.repository.Patient;

public interface SMFuncSvcApi {
	
	public static final String TOKEN_PATH = "/oauth/token";
	
	public static final String MEDICINE_SVC_PATH = "/medicine";

	@GET(UserSvcApi.PATIENT_SVC_PATH + "/{id}/checkins")
	public Collection<CheckIn> getCheckInsByPatientId(@Path("id") Long id);

	@GET(UserSvcApi.PATIENT_SVC_PATH + "/{id}/medicines")
	public Collection<MedicalPrescription> getMedicinesByPatientId(@Path("id") Long id);
	
	@POST(MEDICINE_SVC_PATH)
	public MedicalPrescription addMedicine(@Body MedicalPrescription c);
}
