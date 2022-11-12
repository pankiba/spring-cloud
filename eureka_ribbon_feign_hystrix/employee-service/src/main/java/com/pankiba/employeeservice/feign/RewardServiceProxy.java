package com.pankiba.employeeservice.feign;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.pankiba.employeeservice.config.FeignConfig;
import com.pankiba.employeeservice.service.dto.Reward;

@FeignClient(name = "reward-service", configuration = FeignConfig.class)
@RibbonClient("reward-service")
public interface RewardServiceProxy {

	@GetMapping("/reward-service/api/rewards")
	public List<Reward> getEmployeesWithRewards();

}
