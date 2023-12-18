package com.pankiba.employeeservice.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
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
	private DiscoveryClient discoveryClient;

	@Override
	public List<Employee> getEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public List<Employee> getEmployeesWithRewards() {

		List<ServiceInstance> instances = discoveryClient.getInstances("reward-service");

		List<Employee> employeeList = getEmployees();

		if (instances.isEmpty()) {

			log.info("returning defaul employee data withour reward information ");
			return employeeList;

		} else {

			log.info("{} instances found for reward service  ", instances.size());
			instances.forEach(serviceInstance -> {
				log.info(" ** Instance Details **");
				log.info(" host - {} ", serviceInstance.getHost());
				log.info(" instanceId - {} ", serviceInstance.getInstanceId());
				log.info(" metadata - {} ", serviceInstance.getMetadata());
				log.info(" port - {} ", serviceInstance.getPort());
				log.info(" scheme - {} ", serviceInstance.getScheme());
				log.info(" serviceId - {} ", serviceInstance.getServiceId());
				log.info(" uri - {} ", serviceInstance.getUri());
			});

			// selecting instance randomly between list
			Random randomSelector = new Random();
			String baseUrl = instances.get(randomSelector.nextInt(instances.size())).getUri().toString();

			baseUrl = baseUrl + "/reward-service/api/rewards";

			log.info("instance selected by discovery client is {} ", baseUrl);

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
}
