package com.iot.pi.five.entity;

import java.time.ZonedDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "dbo", name = "NodeMCU")
public class NodeMCUEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int rowId;
	private String deviceId;
	private ZonedDateTime iotHubEnqueuedTime;
	private String deviceMessagePayload;
	private ZonedDateTime recordSaveTime;

	public NodeMCUEntity() {
	}
	
	public NodeMCUEntity(String deviceId, ZonedDateTime iotHubEnqueuedTime, String deviceMessagePayload) {
		this.deviceId = deviceId;
		this.iotHubEnqueuedTime = iotHubEnqueuedTime;
		this.deviceMessagePayload = deviceMessagePayload;
		this.recordSaveTime = null;
	}
	
	public NodeMCUEntity(String deviceId, ZonedDateTime iotHubEnqueuedTime, String deviceMessagePayload,
			ZonedDateTime recordSaveTime) {
		this.deviceId = deviceId;
		this.iotHubEnqueuedTime = iotHubEnqueuedTime;
		this.deviceMessagePayload = deviceMessagePayload;
		this.recordSaveTime = recordSaveTime;
	}
	
	public int getRowIndex() {
		return rowId;
	}
	
	public void setRowIndex(int rowId) {
		this.rowId = rowId;
	}
	
	public String getDeviceId() {
		return deviceId;
	}
	
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	public ZonedDateTime getIotHubEnqueuedTime() {
		return iotHubEnqueuedTime;
	}
	
	public void setIotHubEnqueuedTime(ZonedDateTime iotHubEnqueuedTime) {
		this.iotHubEnqueuedTime = iotHubEnqueuedTime;
	}
	
	public String getDeviceMessagePayload() {
		return deviceMessagePayload;
	}
	
	public void setDeviceMessagePayload(String deviceMessagePayload) {
		this.deviceMessagePayload = deviceMessagePayload;
	}
	
	public ZonedDateTime getRecordSaveTime() {
		return recordSaveTime;
	}
	
	public void setRecordSaveTime(ZonedDateTime recordSaveTime) {
		this.recordSaveTime = recordSaveTime;
	}
}
