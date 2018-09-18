package com.geo.sm.client;

import java.util.Collection;

import com.geo.sm.repository.CheckIn;
import com.geo.sm.repository.Qa;
import com.geo.sm.repository.QaType;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

public interface CheckInSvcApi {

	public static final String TITLE_PARAMETER = "title";

	public static final String CHECKIN_SVC_PATH = "/checkin";
	public static final String QA_SVC_PATH = "/qa";
	public static final String QATYPE_SVC_PATH = "/qatype";

	@POST(CHECKIN_SVC_PATH)
	public CheckIn addCheckIn(@Body CheckIn c);

	@POST(QA_SVC_PATH)
	public Qa addQa(@Body Qa c);

	@GET(CHECKIN_SVC_PATH)
	public Collection<CheckIn> getAllCheckIns();

	@GET(QATYPE_SVC_PATH + "/{id}")
	public QaType getQaTypeById(@Path("id") Long id);

	@GET(CHECKIN_SVC_PATH + "/{id}/qas")
	public Collection<Qa> getQasByCheckInId(@Path("id") Long id);
	
	@GET(CHECKIN_SVC_PATH + "/{id}")
	public CheckIn getCheckInById(@Path("id") Long id);

}
