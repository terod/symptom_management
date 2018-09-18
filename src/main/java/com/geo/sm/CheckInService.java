package com.geo.sm;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.geo.sm.client.CheckInSvcApi;
import com.geo.sm.repository.CheckIn;
import com.geo.sm.repository.CheckInRepository;
import com.geo.sm.repository.Qa;
import com.geo.sm.repository.QaRepository;
import com.geo.sm.repository.QaType;
import com.geo.sm.repository.QaTypeRepository;

@Controller
public class CheckInService implements CheckInSvcApi {

	@Autowired
	private CheckInRepository checkInRespository;
	@Autowired
	private QaRepository qaRepository;
	@Autowired
	private QaTypeRepository qaTypeRepository;

	// check ins
	@RequestMapping(value = CheckInSvcApi.CHECKIN_SVC_PATH, method = RequestMethod.POST)
	public @ResponseBody CheckIn addCheckIn(@RequestBody CheckIn v) {
		return checkInRespository.save(v);
	}

	@RequestMapping(value = CheckInSvcApi.CHECKIN_SVC_PATH, method = RequestMethod.GET)
	public @ResponseBody Collection<CheckIn> getAllCheckIns() {
		return (Collection<CheckIn>) checkInRespository.findAll();
	}

	@RequestMapping(value = CheckInSvcApi.CHECKIN_SVC_PATH + "/{id}", method = RequestMethod.GET)
	public @ResponseBody CheckIn getCheckInById(@PathVariable("id") Long id) {

		return checkInRespository.findOne(id);
	}

	// qas
	@RequestMapping(value = CheckInSvcApi.QA_SVC_PATH, method = RequestMethod.POST)
	public @ResponseBody Qa addQa(@RequestBody Qa v) {
		return qaRepository.save(v);
	}

	@RequestMapping(value = CheckInSvcApi.QATYPE_SVC_PATH + "/{id}", method = RequestMethod.GET)
	public @ResponseBody QaType getQaTypeById(@PathVariable("id") Long id) {
		return qaTypeRepository.findOne(id);
	}

	@RequestMapping(value = CheckInSvcApi.CHECKIN_SVC_PATH + "/{id}/qas", method = RequestMethod.GET)
	public @ResponseBody Collection<Qa> getQasByCheckInId(
			@PathVariable("id") Long id) {
		CheckIn checkin = checkInRespository.findOne(id);
		return qaRepository.findByBelongsTo(checkin);
	}

}
