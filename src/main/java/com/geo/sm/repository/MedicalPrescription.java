package com.geo.sm.repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.common.base.Objects;

@Entity
@Table(name = "medical_prescription")
public class MedicalPrescription {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "medicine_name")
	private String medicineName;
	
	@ManyToOne
	@JoinColumn(name = "prescribed_for")
	private Patient prescribedFor;
	
	@ManyToOne
	@JoinColumn(name = "prescribed_by")
	private Doctor prescribedBy;
	
	public Doctor getPrescribedBy() {
		return prescribedBy;
	}

	public void setPrescribedBy(Doctor prescribedBy) {
		this.prescribedBy = prescribedBy;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMedicineName() {
		return medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	public Patient getPrescribedFor() {
		return prescribedFor;
	}

	public void setPrescribedFor(Patient prescribedFor) {
		this.prescribedFor = prescribedFor;
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(medicineName, prescribedFor.getFirstName());
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof MedicalPrescription) {
			MedicalPrescription other = (MedicalPrescription) obj;
			return Objects.equal(medicineName, other.getMedicineName())
					&& Objects.equal(prescribedFor.getFirstName(), other.prescribedFor.getFirstName());
		} else {
			return false;
		}
	}
}
