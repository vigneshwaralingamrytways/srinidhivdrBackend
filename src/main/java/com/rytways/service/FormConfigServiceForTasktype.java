package com.rytways.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rytways.model.FormConfigMasterForTaskType;
import com.rytways.repository.FormConfigRepoForTaskType;

@Service
public class FormConfigServiceForTasktype {

	@Autowired
	private FormConfigRepoForTaskType formConfigRepoForTaskType;

	public FormConfigMasterForTaskType createNewForm(FormConfigMasterForTaskType form) {
		return formConfigRepoForTaskType.save(form);
	}

	public String deleteFormByFormConfigId(FormConfigMasterForTaskType form) {
		formConfigRepoForTaskType.deleteById(form.getFormConfigId());
		return "Successfully Deleted";
	}

	public List<FormConfigMasterForTaskType> getAllForms() {
		return formConfigRepoForTaskType.findAll();
	}
}
