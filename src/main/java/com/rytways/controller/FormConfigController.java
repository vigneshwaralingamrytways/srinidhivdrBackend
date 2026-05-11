package com.rytways.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rytways.dto.LoadOptionsDto;
import com.rytways.model.FolderMaster;
import com.rytways.model.FormConfigMaster;
import com.rytways.repository.FormConfigMasterRepo;
import com.rytways.service.FormConfigService;

@RestController
@RequestMapping("/formConfigMaster")
public class FormConfigController {
	
	@Autowired
	private FormConfigMasterRepo formConfigMasterRepo;
	
	@Autowired
	private FormConfigService formConfigService;
	
	
	@PostMapping("/create")
	public ResponseEntity <FormConfigMaster> createForm (@RequestBody FormConfigMaster doc){
		
		doc=formConfigService.createForm(doc);
		
		return new ResponseEntity <>(doc,HttpStatus.OK);
	}
	
	
	
	@PostMapping("/formConfigMaster")
	public ResponseEntity <List<FormConfigMaster>> listFormConfig(@RequestBody FormConfigMaster document){
		List<FormConfigMaster> doc=formConfigMasterRepo.findAll();
		return new ResponseEntity <>(doc,HttpStatus.OK);
	}
	
	@PostMapping("/getListByDocumentTypeId")
	public ResponseEntity<List<FormConfigMaster>> getListById(@RequestBody FormConfigMaster doc) {
	    List<FormConfigMaster> folderList = formConfigMasterRepo.findByDocumentTypeIdOrderByUpdatedOn(doc.getDocumentTypeId());
	    
	    if (!folderList.isEmpty()) {
	        return new ResponseEntity<>(folderList, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	
	@PostMapping("/delete")
	public ResponseEntity<String> deleteFormConfig(@RequestBody FormConfigMaster request) {
	    try {
	        Long formConfigId = request.getFormConfigId();
	        formConfigService.deleteFormConfig(formConfigId);
	        return new ResponseEntity<>("FormConfig deleted successfully.", HttpStatus.OK);
	    } catch (Exception e) {
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	    }
	}

}
