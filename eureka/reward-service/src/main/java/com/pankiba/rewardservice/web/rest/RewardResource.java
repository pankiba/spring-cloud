package com.pankiba.rewardservice.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pankiba.rewardservice.domain.Reward;
import com.pankiba.rewardservice.service.RewardService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class RewardResource {

	@Autowired
	private RewardService rewardService;

	/*
	 * Retrieve all rewards
	 */
	@GetMapping("/rewards")
	public ResponseEntity<List<Reward>> getRewards() {

		log.info("Get rewards");
		List<Reward> rewardList = rewardService.getRewards();

		return new ResponseEntity<List<Reward>>(rewardList, HttpStatus.OK);
	}

	/*
	 * Retrieve rewards for specific grade
	 */
	@GetMapping("/rewards/{grade}")
	public ResponseEntity<Reward> getRewardForGrade(@PathVariable String grade) {

		log.info("Get rewards for " + grade);
		Reward reward = rewardService.getReward(grade);

		return new ResponseEntity<Reward>(reward, HttpStatus.OK);
	}
}
