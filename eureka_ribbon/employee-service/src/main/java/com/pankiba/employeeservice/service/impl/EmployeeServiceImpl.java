package com.pankiba.employeeservice.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pankiba.employeeservice.domain.Employee;
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
	private LoadBalancerClient loadBalancerClient;

	@Override
	public List<Employee> getEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public List<Employee> getEmployeesWithRewards() {

		ServiceInstance serviceInstance = loadBalancerClient.choose("reward-service");

		List<Employee> employeeList = getEmployees();

		log.info("instance selected by loadbalncer is {} ", serviceInstance.getUri());

		log.info(" ** Instance Details **");
		log.info(" host - {} ", serviceInstance.getHost());
		log.info(" instanceId - {} ", serviceInstance.getInstanceId());
		log.info(" metadata - {} ", serviceInstance.getMetadata());
		log.info(" port - {} ", serviceInstance.getPort());
		log.info(" scheme - {} ", serviceInstance.getScheme());
		log.info(" serviceId - {} ", serviceInstance.getServiceId());
		log.info(" uri - {} ", serviceInstance.getUri());

		String baseUrl = serviceInstance.getUri().toString();
		baseUrl = baseUrl + "/reward-service/api/rewards";

		ResponseEntity<Reward[]> response = new RestTemplate().getForEntity(baseUrl, Reward[].class);
		List<Reward> rewardList = Arrays.asList(response.getBody());

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
