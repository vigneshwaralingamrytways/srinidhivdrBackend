package com.rytways.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rytways.model.EmployeeDetailsForOrgChart;
import com.rytways.model.OrganizationChart;
import com.rytways.repository.EmployeeDetailsRepo;
import com.rytways.repository.OrganizationChartRepo;

@Service
public class OrganizationChartService {
	
	@Autowired
	private OrganizationChartRepo organizationChartRepo;
	
	@Autowired
	private EmployeeDetailsRepo employeeDetailsRepo;

	public List<Map<String, Object>> getAllDesignation() {
		return buildHierachy(null);
	}

	private List<Map<String, Object>> buildHierachy(Long parentId) {
		List<OrganizationChart> designations;
		if(parentId==null)
			designations=organizationChartRepo.findByRootDesignation();
		else
			designations=organizationChartRepo.findByParentId(parentId);
		
		List<Map<String, Object>> hierachy=new ArrayList<>();
		for(OrganizationChart chart:designations) {
			Map<String, Object> node=new HashMap<String, Object>();
			node.put("chartId", chart.getChartId());
			node.put("designation", chart.getDesignation());
			node.put("noOfMaxPosition", chart.getNoOfMaxPosition());
			node.put("filledUp", chart.getFilledUp());
			node.put("toolTip", chart.getToolTip());
			node.put("children", buildHierachy(chart.getChartId()));
			hierachy.add(node);
		}
		return hierachy;
	}

	public List<OrganizationChart> getAllDesignations() {
		return organizationChartRepo.findAll();
	}

	public List<EmployeeDetailsForOrgChart> getAllEmployees() {
		return employeeDetailsRepo.findAll();
	}
	
	

}
