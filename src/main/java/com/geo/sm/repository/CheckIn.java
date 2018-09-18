package com.geo.sm.repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.common.base.Objects;

@Entity
@Table(name = "check_in")
public class CheckIn {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "date_time")
	private long dateTime;

	@ManyToOne
	@JoinColumn(name = "checkin_by")
	private Patient checkInBy;

	@Column(name = "image_path")
	private String imagePath;

	@Lob
	private String image;

	@Column(name = "time_of_medicine")
	private String timeOfConsume;

	/*
	 * @OneToMany(mappedBy = "associatedCheckIn")
	 * 
	 * @NotFound(action = NotFoundAction.IGNORE) private Collection<Qa> qaList =
	 * new ArrayList<Qa>();
	 * 
	 * public Collection<Qa> getQaList() { return qaList; }
	 * 
	 * public void setQaList(Collection<Qa> qaList) { this.qaList = qaList; }
	 */

	public long getId() {
		return id;
	}

	public String getTimeOfConsume() {
		return timeOfConsume;
	}

	public void setTimeOfConsume(String timeOfConsume) {
		this.timeOfConsume = timeOfConsume;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getDateTime() {
		return dateTime;
	}

	public void setDateTime(long dateTime) {
		this.dateTime = dateTime;
	}

	public Patient getCheckInBy() {
		return checkInBy;
	}

	public void setCheckInBy(Patient checkInBy) {
		this.checkInBy = checkInBy;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(dateTime, imagePath);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof CheckIn) {
			CheckIn other = (CheckIn) obj;
			return Objects.equal(dateTime, other.dateTime)
					&& Objects.equal(imagePath, other.imagePath);
		} else {
			return false;
		}
	}
}
