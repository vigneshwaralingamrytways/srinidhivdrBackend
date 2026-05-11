package com.rytways.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rytways.dto.LoadOptionsDto;
import com.rytways.model.DocumentTypeMaster;
import com.rytways.model.FolderMaster;
import com.rytways.model.FormConfigMaster;
import com.rytways.repository.FormConfigMasterRepo;

@Component
@Service
@Transactional
public class FormConfigService {
	
	@Autowired
	private FormConfigMasterRepo formConfigMasterRepo;

	public FormConfigMaster createForm(FormConfigMaster doc) {
		
		doc=formConfigMasterRepo.save(doc);
		return doc;
	}

	public void deleteFormConfig(Long formConfigId) throws Exception {
	    if (formConfigId != null) {
	        // Check if the record exists
	    	FormConfigMaster formConfigMaster = formConfigMasterRepo.findById(formConfigId)
	                .orElseThrow(() -> new Exception("formConfig with ID " + formConfigId + " not found."));
	        
	        // Ensure no dependent records exist (if necessary)
	       

	        // Delete the DocumentUserMaster record
	    	formConfigMasterRepo.deleteById(formConfigId);
	    } else {
	        throw new Exception("formConfigId cannot be null.");
	    }
	}





}
