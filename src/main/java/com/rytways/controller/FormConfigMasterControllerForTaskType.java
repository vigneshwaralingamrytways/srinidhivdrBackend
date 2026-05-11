package com.rytways.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rytways.model.FormConfigMasterForTaskType;
import com.rytways.repository.FormConfigRepoForTaskType;
import com.rytways.service.FormConfigServiceForTasktype;

@RestController
@RequestMapping("/form-config/taskType")
public class FormConfigMasterControllerForTaskType {
	
	@Autowired
	private FormConfigRepoForTaskType formConfigRepoForTaskType;

	@Autowired
	private FormConfigServiceForTasktype formConfigServiceForTasktype;
	
	@PostMapping("/create")
	public ResponseEntity<FormConfigMasterForTaskType> createNewForm(@RequestBody FormConfigMasterForTaskType form){
		return new ResponseEntity<FormConfigMasterForTaskType>(formConfigServiceForTasktype.createNewForm(form),HttpStatus.OK);
	}
	
	@PostMapping("/delete")
	public ResponseEntity<String> deleteFormByFormConfigId(@RequestBody FormConfigMasterForTaskType form){
		return new ResponseEntity<String>(formConfigServiceForTasktype.deleteFormByFormConfigId(form),HttpStatus.OK);
	}
	
	@PostMapping("/getAllForms")
	public ResponseEntity<List<FormConfigMasterForTaskType>> getAllForms(){
		return new ResponseEntity<List<FormConfigMasterForTaskType>>(formConfigServiceForTasktype.getAllForms(),HttpStatus.OK);
	}
	
	@PostMapping("/getAllFormByTaskTypeId/{taskTypeId}")
	public ResponseEntity<List<FormConfigMasterForTaskType>> getListByTaskTypeId(@PathVariable("taskTypeId") int taskTypeId) {
	    List<FormConfigMasterForTaskType> folderList = formConfigRepoForTaskType.findByTaskTypeId(taskTypeId);
	    
	    if (!folderList.isEmpty()) {
	        return new ResponseEntity<>(folderList, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
}
