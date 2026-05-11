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
import com.rytways.model.SubFolderMaster;
import com.rytways.repository.SubFolderMasterRepo;

@Component
@Service
@Transactional
public class SubfolderService {

	
	@Autowired
	private SubFolderMasterRepo subFolderMasterRepo;

	public SubFolderMaster createSub(SubFolderMaster doc) {
		
		doc=subFolderMasterRepo.save(doc);
		return doc;
	}
	
	public SubFolderMaster editSub(SubFolderMaster doc) {
		
		doc.setDocumentTypeMaster(null);
		doc.setFolderMaster(null);
		doc=subFolderMasterRepo.save(doc);
		return doc;
	}

	public List<LoadOptionsDto> loadSub() {
		
		List<SubFolderMaster> subList = subFolderMasterRepo.findAll();
		List<LoadOptionsDto> dtoList = new ArrayList<>();
		 if (!subList.isEmpty()) {
			   for (SubFolderMaster subMaster : subList) {
				   LoadOptionsDto loadDto = new LoadOptionsDto(); // Move inside the loop

		            loadDto.setLabel(subMaster.getSubFolderCategoryName());
		            loadDto.setValue(subMaster.getSubFolderId());

		            dtoList.add(loadDto); // Add each instance to the list
		        }
		 }
		return dtoList;
	}

	public List<LoadOptionsDto> loadOptions(SubFolderMaster doc) {
		
		List<SubFolderMaster> subList = subFolderMasterRepo.findByFolderId(doc.getFolderId());
		List<LoadOptionsDto> dtoList = new ArrayList<>();
		 if (!subList.isEmpty()) {
			   for (SubFolderMaster subMaster : subList) {
				   LoadOptionsDto loadDto = new LoadOptionsDto(); // Move inside the loop

		            loadDto.setLabel(subMaster.getSubFolderCategoryName());
		            loadDto.setValue(subMaster.getSubFolderId());

		            dtoList.add(loadDto); // Add each instance to the list
		        }
		 }
		return dtoList;
	}

	public void deleteSubFolder(Long subFolderId) throws Exception {
	    if (subFolderId != null) {
	        // Check if the record exists
	    	SubFolderMaster subFolderMaster = subFolderMasterRepo.findById(subFolderId)
	                .orElseThrow(() -> new Exception("Sub Folder with ID " + subFolderId + " not found."));
	        
	        // Ensure no dependent records exist (if necessary)
	       

	        // Delete the DocumentUserMaster record
	    	subFolderMasterRepo.deleteById(subFolderId);
	    } else {
	        throw new Exception("Sub FolderId cannot be null.");
	    }
	}
	
	
	
	
	
}
