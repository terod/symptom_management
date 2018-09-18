package com.geo.sm.repository;

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
@Table(name = "qa")
public class Qa {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Lob
	private String question;
	@Lob
	private String answer;

	@ManyToOne
	@JoinColumn(name = "belongs_to", nullable = false)
	private CheckIn belongsTo;

	@ManyToOne
	@JoinColumn(name = "question_type", nullable = false)
	private QaType questionType;

	public CheckIn getBelongsTo() {
		return belongsTo;
	}

	public void setBelongsTo(CheckIn belongsTo) {
		this.belongsTo = belongsTo;
	}

	public QaType getQuestionType() {
		return questionType;
	}

	public void setQuestionType(QaType questionType) {
		this.questionType = questionType;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(question, answer, belongsTo.getDateTime());
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Qa) {
			Qa other = (Qa) obj;
			return Objects.equal(question, other.question)
					&& Objects.equal(answer, other.answer)
					&& Objects.equal(belongsTo.getDateTime(),
							other.belongsTo.getDateTime());
		} else {
			return false;
		}
	}
}
