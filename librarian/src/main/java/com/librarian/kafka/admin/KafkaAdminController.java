package com.librarian.kafka.admin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.admin.Admin;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.TopicListing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaAdminController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public static final AdminClient getAdmin() {
		Properties config = new Properties();
		config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		AdminClient admin = AdminClient.create(config);
		return admin;
	}

	@GetMapping("/kafka/admin/topics")
	public ResponseEntity<?> adminTest() {

		try {
			Admin admin = getAdmin();
		
			Collection<TopicListing> topics = admin.listTopics().listings().get();
			ArrayList<String> topicNames = new ArrayList();
			
			for (TopicListing topicList: topics) {
				System.out.println("admin : " + topicList.name());
				topicNames.add(topicList.name());
			}
			return ResponseEntity.ok(topicNames);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return ResponseEntity.badRequest().build();
	}
}
