package com.pankiba.rewardservice.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Reward implements Serializable {

	private static final long serialVersionUID = -7013574090505467746L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long rewardId;

	private String grade;

	private Long rewardAmmount;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Transient
	private String messageFrom;

	public Reward(String grade, Long rewardAmmount) {
		this.grade = grade;
		this.rewardAmmount = rewardAmmount;
	}

}
