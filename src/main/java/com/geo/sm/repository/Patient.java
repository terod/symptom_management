package com.geo.sm.repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.google.common.base.Objects;

@Entity
public class Patient extends User {

	@Column(name = "mrn", unique = true)
	private String medicalRecordNumber;

	private long dob;

	public long getDob() {
		return dob;
	}

	public void setDob(long dob) {
		this.dob = dob;
	}

	@ManyToOne
	@JoinColumn(name = "administered_by")
	private Doctor administeredBy;

	/*
	 * @OneToMany(mappedBy = "prescribedFor")
	 * 
	 * @NotFound(action = NotFoundAction.IGNORE) private
	 * Collection<MedicalPrescription> medicalList = new
	 * ArrayList<MedicalPrescription>();
	 * 
	 * @OneToMany(mappedBy = "checkInBy")
	 * 
	 * @NotFound(action = NotFoundAction.IGNORE) private Collection<CheckIn>
	 * checkInList = new ArrayList<CheckIn>();
	 * 
	 * public Collection<CheckIn> getCheckInList() { return checkInList; }
	 * 
	 * public void setCheckInList(Collection<CheckIn> checkInList) {
	 * this.checkInList = checkInList; }
	 */

	public Doctor getAdministeredBy() {
		return administeredBy;
	}

	public void setAdministeredBy(Doctor administeredBy) {
		this.administeredBy = administeredBy;
	}

	public String getMedicalRecordNumber() {
		return medicalRecordNumber;
	}

	public void setMedicalRecordNumber(String medicalRecordNumber) {
		this.medicalRecordNumber = medicalRecordNumber;
	}

	/*
	 * public Collection<MedicalPrescription> getMedicalList() { return
	 * medicalList; }
	 * 
	 * public void setMedicalList(Collection<MedicalPrescription> medicalList) {
	 * this.medicalList = medicalList; }
	 */

	@Override
	public int hashCode() {
		return Objects.hashCode(medicalRecordNumber, getFirstName(),
				getLastName());
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Patient) {
			Patient other = (Patient) obj;
			return Objects
					.equal(medicalRecordNumber, other.medicalRecordNumber)
					&& Objects.equal(getFirstName(), other.getFirstName())
					&& Objects.equal(getLastName(), other.getLastName());
		} else {
			return false;
		}
	}

}
