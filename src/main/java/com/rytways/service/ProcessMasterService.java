package com.rytways.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rytways.dto.LoadOptionsDto;
import com.rytways.model.ProcessMasters;
import com.rytways.repository.ActivityMasterRepo;
import com.rytways.repository.ProcessMasterRepo;

@Component
@Service
@Transactional
public class ProcessMasterService {

	
	@Autowired
	private ProcessMasterRepo processRepo;
	@Autowired
	private ActivityMasterRepo activityMasterRepo;
//	
//	@Autowired
//	private FunctionMasterRepo functionRepo;
	

	public ProcessMasters createProcess(ProcessMasters doc) {
		
		return processRepo.save(doc);
	}

	public List<ProcessMasters> getProcessMaster() {
		// TODO Auto-generated method stub
		return processRepo.findAll();
	}
	
	public void deleteProcess(long processId) throws Exception {
		
		boolean isUsed = activityMasterRepo.existsByProcessId(processId);
		if (isUsed) {
		    throw new Exception("Cannot delete process: it is in use by FunctionMaster.");
		}
        // Check if the record exists
	 ProcessMasters process = processRepo.findById(processId)
                .orElseThrow(() -> new Exception("Process with ID " + processId + " not found."));
        
        
        // Delete the DocumentUserMaster record
	 processRepo.deleteById(processId);
    } 
	
	
	public List<LoadOptionsDto> loadType() {
		 
		List<ProcessMasters> typeList = processRepo.findAll();
		List<LoadOptionsDto> dtoList = new ArrayList<>();
		 if (!typeList.isEmpty()) {
			   for (ProcessMasters typeMaster : typeList) {
				   LoadOptionsDto loadDto = new LoadOptionsDto(); // Move inside the loop

		            loadDto.setLabel(typeMaster.getProcessName());
		            loadDto.setValue(typeMaster.getProcessId());

		            dtoList.add(loadDto); // Add each instance to the list
		        }
		 }
		return dtoList;
	}

	public ProcessMasters findProcessById(long id) {
    return processRepo.findById(id).orElse(null);
}

public List<ProcessMasters> getAllProcess() {
	return processRepo.findAll();
}
	
}
