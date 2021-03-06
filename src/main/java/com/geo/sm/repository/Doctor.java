package com.geo.sm.repository;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.google.common.base.Objects;

@Entity
public class Doctor extends User {
	@Column(unique = true)
	private String doctorId;

	/*
	 * @OneToMany
	 * 
	 * @NotFound(action = NotFoundAction.IGNORE) private Collection<Patient>
	 * patientsUnderDoctor = new ArrayList<Patient>();
	 */

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	/*
	 * public Collection<Patient> getPatientsUnderDoctor() { return
	 * patientsUnderDoctor; }
	 * 
	 * public void setPatientsUnderDoctor(Collection<Patient>
	 * patientsUnderDoctor) { this.patientsUnderDoctor = patientsUnderDoctor; }
	 */

	@Override
	public int hashCode() {
		return Objects.hashCode(doctorId);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Doctor) {
			Doctor other = (Doctor) obj;
			return Objects.equal(doctorId, other.doctorId);
		} else {
			return false;
		}
	}
}
