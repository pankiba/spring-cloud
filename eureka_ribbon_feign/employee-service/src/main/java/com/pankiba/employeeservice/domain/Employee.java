package com.pankiba.employeeservice.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Employee implements Serializable {

	private static final long serialVersionUID = 6798516679232905689L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long employeeId;

	private String firstName;

	private String lastName;

	private String gender;

	@Column(unique = true)
	private String email;

	@Temporal(TemporalType.DATE)
	private Date birthDate;

	@Temporal(TemporalType.DATE)
	private Date joiningDate;

	@Enumerated(EnumType.STRING)
	private Grade grade;

	private Long salary;

	private Long reward;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Transient
	private String messageFrom;

	public Employee(String firstName, String lastName, String gender, String email, Date birthDate, Date joiningDate,
			Grade grade, Long salary, Long reward) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.email = email;
		this.birthDate = birthDate;
		this.joiningDate = joiningDate;
		this.grade = grade;
		this.salary = salary;
		this.reward = reward;
	}

}
