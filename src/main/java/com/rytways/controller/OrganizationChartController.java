package com.rytways.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rytways.model.EmployeeDetailsForOrgChart;
import com.rytways.model.OrganizationChart;
import com.rytways.service.OrganizationChartService;

@RequestMapping("/orgChart")
@RestController
public class OrganizationChartController {
	
	@Autowired
	private OrganizationChartService organizationChartService;
	
	@PostMapping("/getAllDesignation")
	public ResponseEntity<List<Map<String,Object>>> getAllDesignation(){
		return new ResponseEntity<List<Map<String,Object>>>(organizationChartService.getAllDesignation(),HttpStatus.OK);
	}
	
	@PostMapping("/getAllDesignations")
	public ResponseEntity<List<OrganizationChart>> getAllDesignations(){
		return new ResponseEntity<List<OrganizationChart>>(organizationChartService.getAllDesignations(),HttpStatus.OK);
	}
	
	@PostMapping("/getAllEmployees")
	public ResponseEntity<List<EmployeeDetailsForOrgChart>> getAllEmployees(){
		return new ResponseEntity<List<EmployeeDetailsForOrgChart>>(organizationChartService.getAllEmployees(),HttpStatus.OK);
	}
	

}
