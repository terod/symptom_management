package com.geo.sm;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.geo.sm.repository.CheckIn;

/**
 * This is a utility class to aid in the construction of Video objects with
 * random names, urls, and durations. The class also provides a facility to
 * convert objects into JSON using Jackson, which is the format that the
 * VideoSvc controller is going to expect data in for integration testing.
 * 
 * @author jules
 *
 */
public class TestData {

	private static final ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * Construct and return a Video object with a rnadom name, url, and
	 * duration.
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static CheckIn randomCheckIn() {
		// Information about the video
		// Construct a random identifier using Java's UUID class
		/*
		 * Doctor doctor1 = new Doctor(); doctor1.setDoctorId("DOC111");
		 * doctor1.setUserName("doc1"); doctor1.setFirstName("Paul");
		 * doctor1.setLastName("John"); Collection<Patient> userListDoctor1 =
		 * new ArrayList<Patient>();
		 */

		CheckIn checkIn1 = new CheckIn();
		checkIn1.setDateTime(new Date().getTime());
		checkIn1.setTimeOfConsume("21/Dec /2014");
		checkIn1.setImagePath("1.png");
		// File file = new File("images\\extjsfirstlook.jpg"); //windows
		File file = new File("1.png");
		byte[] bFile = new byte[(int) file.length()];

		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			fileInputStream.read(bFile);
			fileInputStream.close();
			checkIn1.setImage(new Base64().encodeToString(bFile));
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		/*
		 * Qa qa1 = new Qa(); qa1.setQuestion("Did you take your lortab");
		 * qa1.setAnswer("Yes"); Qa qa2 = new Qa();
		 * qa2.setQuestion("Did you take your Crocin"); qa2.setAnswer("No"); Qa
		 * qa3 = new Qa(); qa3.setQuestion("Did you take your OxyCortin");
		 * qa3.setAnswer("Yes"); Qa qa4 = new Qa();
		 * qa4.setQuestion("How bad is your mouth pain/sore throat");
		 * qa4.setAnswer("Moderate"); Qa qa5 = new Qa();
		 * qa5.setQuestion("Does your pain stop you from eating/driking");
		 * qa5.setAnswer("I can't eat");
		 * 
		 * checkIn1.getQaList().add(qa1); checkIn1.getQaList().add(qa2);
		 * checkIn1.getQaList().add(qa3); checkIn1.getQaList().add(qa4);
		 * checkIn1.getQaList().add(qa5);
		 */

		return checkIn1;
	}

	/**
	 * Convert an object to JSON using Jackson's ObjectMapper
	 * 
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public static String toJson(Object o) throws Exception {
		return objectMapper.writeValueAsString(o);
	}
}
