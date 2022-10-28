package com.iot.pi.five.listener.mqtt;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import com.azure.spring.messaging.AzureHeaders;
import com.azure.spring.messaging.checkpoint.Checkpointer;
import com.azure.spring.messaging.eventhubs.support.EventHubsHeaders;
import com.iot.pi.five.controller.NodeMCUController;
import com.iot.pi.five.controller.mqtt.MQTTController;
import com.iot.pi.five.entity.NodeMCUEntity;

@Configuration
public class MQTTListener {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MQTTListener.class);
	
	@Bean
	public Consumer<Message<String>> consume(MQTTController mqttController, NodeMCUController nodeMCUController) {
		return message -> {
			MessageHeaders msgHeaders = message.getHeaders();
			
			Checkpointer checkpointer = (Checkpointer) msgHeaders.get(AzureHeaders.CHECKPOINTER);
			Date iotHubDate = (Date) msgHeaders.get("iothub-enqueuedtime");
			ZonedDateTime iothubEnqueuedTime = ZonedDateTime.ofInstant(iotHubDate.toInstant(), ZoneId.of("UTC-03"));
			String deviceId = (String) msgHeaders.get("iothub-connection-device-id");
			String msgPayload = message.getPayload();
			
			nodeMCUController.save(new NodeMCUEntity(deviceId, iothubEnqueuedTime, msgPayload));
			
			if (deviceId.equals("nodeMCUIn")) {
				mqttController.sendMessageToDevice("wemos", "LED ON");
			}
			if (deviceId.equals("nodeMCUOut")) {
				mqttController.sendMessageToDevice("wemos", "LED OFF");
			}
			
			LOGGER.info(
					"New message received: '{}', partition key: {}, sequence number: {}, offset: {}, enqueued time: {}",
					message.getPayload(), message.getHeaders().get(AzureHeaders.PARTITION_KEY),
					message.getHeaders().get(EventHubsHeaders.SEQUENCE_NUMBER),
					message.getHeaders().get(EventHubsHeaders.OFFSET),
					message.getHeaders().get(EventHubsHeaders.ENQUEUED_TIME));
			
			checkpointer.success()
					.doOnSuccess(success -> LOGGER.info("Message '{}' successfully checkpointed", message.getPayload()))
					.doOnError(error -> LOGGER.error("Exception found", error)).block();
		};
	}
}
