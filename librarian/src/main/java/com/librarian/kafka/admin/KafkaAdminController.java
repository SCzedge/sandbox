package com.librarian.kafka.admin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.admin.Admin;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.DeleteTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.admin.TopicListing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaAdminController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public static final AdminClient ADMIN = getAdmin();
	
	
	public static final AdminClient getAdmin() {
		Properties config = new Properties();
		config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		AdminClient admin = AdminClient.create(config);
		return admin;
	}
	
	@GetMapping("/kafka/admin/topics")
	public ResponseEntity<?> getTopics() {

		try {

			Collection<TopicListing> topics = ADMIN.listTopics().listings().get();
			ArrayList<String> topicNames = new ArrayList<>();

			for (TopicListing topicList : topics) {
				System.out.println("admin : " + topicList.name());
				topicNames.add(topicList.name());
			}
			return ResponseEntity.ok(topicNames);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.badRequest().build();
	}

	@PostMapping("/kafka/admin/topic")
	public ResponseEntity<?> createTopic(@RequestBody String topic) {
		try {

			System.out.println("createTopic : "+topic);
			String topicNm = topic.replaceAll("\"","");
			
			NewTopic newTopic = new NewTopic(topicNm, 1, (short) 1);
			CreateTopicsResult result = ADMIN.createTopics(Collections.singleton(newTopic));
			
			System.out.println("value : "+result.values());
			System.out.println("all : "+result.all().toString());
			
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.badRequest().build();
	}

	@DeleteMapping("/kafka/admin/topic")
	public ResponseEntity<?> deleteTopic(@RequestBody String topic) {
		try {
			System.out.println("delete topic : "+topic);
			String topicNm = topic.replaceAll("\"", "");
			DeleteTopicsResult result = ADMIN.deleteTopics(Collections.singleton(topicNm));
			
			
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.badRequest().build();

	}
}
