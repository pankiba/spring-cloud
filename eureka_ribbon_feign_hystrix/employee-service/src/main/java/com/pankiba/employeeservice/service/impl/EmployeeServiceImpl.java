package com.pankiba.employeeservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pankiba.employeeservice.domain.Employee;
import com.pankiba.employeeservice.feign.RewardServiceProxy;
import com.pankiba.employeeservice.repository.EmployeeRepository;
import com.pankiba.employeeservice.service.EmployeeService;
import com.pankiba.employeeservice.service.dto.Reward;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private RewardServiceProxy rewardServiceProxy;

	@Override
	public List<Employee> getEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public List<Employee> getEmployeesWithRewards() {

		List<Employee> employeeList = getEmployees();

		log.info("retreving rewards details");
		List<Reward> rewardList = rewardServiceProxy.getEmployeesWithRewards();

		employeeList.forEach(employee -> {
			rewardList.forEach(reward -> {
				if (employee.getGrade().getLevel().equals(reward.getGrade())) {
					employee.setReward(reward.getRewardAmmount());
					employee.setMessageFrom("rewards data received from - " + reward.getMessageFrom());
				}
			});
		});

		return employeeList;
	}

}
