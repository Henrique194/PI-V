package com.iot.pi.five.client.mqtt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.microsoft.azure.sdk.iot.service.IotHubServiceClientProtocol;
import com.microsoft.azure.sdk.iot.service.ServiceClient;

@Configuration
public class MQTTClientFactory {
	
	private static final String SERVICE_CONNECTION_STRING =
			"HostName=pi-v-iot-hub.azure-devices.net;SharedAccessKeyName=service;SharedAccessKey=ZdhmZSC9SCGSOds1eOijqGJGBRfAjy66X8B2cjB7Jxo=";
	@Bean
	public ServiceClient createServiceClient() {
		return new ServiceClient(SERVICE_CONNECTION_STRING, IotHubServiceClientProtocol.AMQPS);
	}
}
