package com.pankiba.employeeservice.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Reward {

	private Long rewardId;
	private String grade;
	private Long rewardAmmount;
	private String messageFrom;

}
