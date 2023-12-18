package com.pankiba.rewardservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.pankiba.rewardservice.domain.Reward;
import com.pankiba.rewardservice.repository.RewardRepository;
import com.pankiba.rewardservice.service.RewardService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RewardServiceImpl implements RewardService {

	@Autowired
	private RewardRepository rewardRepository;

	@Autowired
	private Environment environment;

	@Override
	@HystrixCommand(fallbackMethod = "getRewardsFallback")
	public List<Reward> getRewards() {

		log.info("getting rewards from db");

		List<Reward> rewards = rewardRepository.findAll();
		String messageFrom = environment.getProperty("spring.application.name") + ":"
				+ environment.getProperty("server.port");

		rewards.forEach(reward -> {
			if (reward.getGrade().equals("Lead")) {
				throw new RuntimeException("Simulating Error");
			}
			reward.setMessageFrom(messageFrom);
		});

		return rewards;
	}

	@Override
	public Reward getReward(String grade) {

		log.info("getting rewards from db");

		Reward reward = rewardRepository.findByGrade(grade);
		String messageFrom = environment.getProperty("spring.application.name") + ":"
				+ environment.getProperty("server.port");
		reward.setMessageFrom(messageFrom);

		return reward;
	}

	public List<Reward> getRewardsFallback() {

		log.info("CIRCUIT BREAKER ENABLED !! No Response From Reward service at this moment. Fallback route enabled");

		List<Reward> rewards = new ArrayList<Reward>();

		rewards.add(new Reward("Developer", 10L));
		rewards.add(new Reward("Lead", 10L));
		rewards.add(new Reward("Architect", 10L));

		String messageFrom = environment.getProperty("spring.application.name") + ":"
				+ environment.getProperty("server.port");

		rewards.forEach(reward -> {
			reward.setMessageFrom(messageFrom);
		});

		return rewards;
	}

}
