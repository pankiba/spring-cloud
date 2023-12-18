package com.pankiba.rewardservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pankiba.rewardservice.domain.Reward;

public interface RewardRepository extends JpaRepository<Reward, Long> {

	public Reward findByGrade(String grade);

}
