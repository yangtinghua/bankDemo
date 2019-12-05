package com.yth;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.zookeeper.discovery.ZookeeperServiceInstance;
import org.springframework.stereotype.Component;

/**
 * 
 * @author yth
 *
 */
@Component
public class InitProject implements ApplicationRunner {
	private static final Logger logger = LoggerFactory.getLogger(InitProject.class);

	@Autowired
	private DiscoveryClient zkClient;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		logger.info("==========init project===========");

		System.out.println("==========init project===========");
		List<String> services = zkClient.getServices();
		services.forEach(serviceId -> {
			List<ServiceInstance> serviceInstances = zkClient.getInstances(serviceId);

			serviceInstances.forEach(serviceInstance -> {

				ZookeeperServiceInstance zkInstance = (ZookeeperServiceInstance) serviceInstance;
				System.out.println("name：" + zkInstance.getServiceId());
				System.out.println("id：" + zkInstance.getServiceInstance().getId());
				System.out.println("ip：" + zkInstance.getHost());
				System.out.println("port：" + zkInstance.getPort());

			});

		});

		System.out.println("==========init success===========");
	}
}
