package com.iot.pi.five.controller;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iot.pi.five.entity.NodeMCUEntity;
import com.iot.pi.five.repository.NodeMCURepository;

@RestController
@RequestMapping(path = "/node")
public class NodeMCUController {
	
	@Autowired
	private NodeMCURepository repository;
	
	@GetMapping(path = "/findAll")
	public Iterable<NodeMCUEntity> findAll() {
		return repository.findAll();
	}
	
	@GetMapping(path = "/findById")
	public ResponseEntity<NodeMCUEntity> findById(@RequestParam int id) {
		return repository.findById(id)
						 .map(ResponseEntity::ok)
						 .orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@GetMapping(path = "/findByDeviceId")
	public List<NodeMCUEntity> findByDeviceId(@RequestParam String deviceId) {
		return repository.findByDeviceId(deviceId);
	}
	
	@PostMapping(path = "/save")
	@Async
	public ResponseEntity<NodeMCUEntity> save(@RequestBody NodeMCUEntity nodeEntity) {
		nodeEntity.setRecordSaveTime(ZonedDateTime.now());
		repository.save(nodeEntity);
		return ResponseEntity.ok(nodeEntity);
	}
}
