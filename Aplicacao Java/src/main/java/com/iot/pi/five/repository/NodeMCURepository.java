package com.iot.pi.five.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.iot.pi.five.entity.NodeMCUEntity;

public interface NodeMCURepository extends CrudRepository<NodeMCUEntity, Integer> {
	List<NodeMCUEntity> findByDeviceId(String deviceId);
}
