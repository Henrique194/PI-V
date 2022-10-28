package com.iot.pi.five.controller.mqtt;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.iot.pi.five.client.mqtt.MQTTClientFactory;
import com.microsoft.azure.sdk.iot.service.DeliveryAcknowledgement;
import com.microsoft.azure.sdk.iot.service.Message;
import com.microsoft.azure.sdk.iot.service.ServiceClient;
import com.microsoft.azure.sdk.iot.service.exceptions.IotHubException;

@Configuration
public class MQTTController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MQTTController.class);
	
	@Autowired
	private MQTTClientFactory mqttClientFactory;
	
	public void sendMessageToDevice(String deviceId, String message) {
		ServiceClient serviceClient = mqttClientFactory.createServiceClient();
		try {
			Message messageToSend = new Message(message);			
			messageToSend.setDeliveryAcknowledgementFinal(DeliveryAcknowledgement.Full);
			serviceClient.send(deviceId, messageToSend);
			serviceClient.close();
		}
		catch (UnsupportedEncodingException uee) {
			LOGGER.error("Enconding Utilizado Para Criar A Mensagem Não É Suportado!");
		}
		catch (IotHubException ihe) {
			LOGGER.error("IotHub Rejeitou A Mensagem!");
		}
		catch (IOException ioe) {
			LOGGER.error("O Objeto AmqpSender Não Foi Inicializado!");
		}
	}
}
