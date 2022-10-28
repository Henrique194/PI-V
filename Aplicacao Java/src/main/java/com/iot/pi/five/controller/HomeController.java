package com.iot.pi.five.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iot.pi.five.entity.NodeMCUEntity;

@Controller
public class HomeController {
	@Autowired
	private NodeMCUController nodeMCUController;
	
	@RequestMapping("/home")
	public String home(Model model) {
		List<NodeMCUEntity> pointList = nodeMCUController.findByDeviceId("nodeMCUIn");
		Map<Integer, Integer> surveyMap = new LinkedHashMap<>();
		for (NodeMCUEntity entity : pointList) {
			surveyMap.put(entity.getRowIndex(), entity.getRecordSaveTime().getHour());
		}
		model.addAttribute("surveyMap", surveyMap);
		return "home";
	}
}