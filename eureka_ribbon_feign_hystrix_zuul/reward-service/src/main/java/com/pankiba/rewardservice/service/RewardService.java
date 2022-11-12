package com.pankiba.rewardservice.service;

import java.util.List;

import com.pankiba.rewardservice.domain.Reward;

public interface RewardService {

	public List<Reward> getRewards();

	public Reward getReward(String grade);
}
