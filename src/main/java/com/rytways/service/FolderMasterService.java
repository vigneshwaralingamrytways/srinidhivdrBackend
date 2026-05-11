package com.rytways.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rytways.dto.LoadOptionsDto;
import com.rytways.model.DocumentTypeMaster;
import com.rytways.model.DocumentUserMaster;
import com.rytways.model.FolderMaster;
import com.rytways.repository.FolderRepository;

@Component
@Service
@Transactional
public class FolderMasterService {
	
	
	@Autowired
	private FolderRepository folderRepository;

	public FolderMaster createFolder(FolderMaster doc) {
		
		
		doc=folderRepository.save(doc);
		return doc;
	}
	
	public FolderMaster editFolder(FolderMaster doc) {
		
		doc.setDocumentTypeMaster(null);
		
		doc=folderRepository.save(doc);
		return doc;
	}

	public List<LoadOptionsDto> loadFolder() {
		
		List<FolderMaster> folderList = folderRepository.findAll();
		List<LoadOptionsDto> dtoList = new ArrayList<>();
		 if (!folderList.isEmpty()) {
			   for (FolderMaster folderMaster : folderList) {
				   LoadOptionsDto loadDto = new LoadOptionsDto(); // Move inside the loop

		            loadDto.setLabel(folderMaster.getFolderCategoryName());
		            loadDto.setValue(folderMaster.getFolderId());

		            dtoList.add(loadDto); // Add each instance to the list
		        }
		 }
		return dtoList;
	}

	public List<LoadOptionsDto> loadOptions(FolderMaster doc) {
		  System.out.println("Inside loadOptions method");
		List<FolderMaster> folderList = folderRepository.findByDocumentTypeIdOrderByUpdatedOn(doc.getDocumentTypeId());
		List<LoadOptionsDto> dtoList = new ArrayList<>();
		 if (!folderList.isEmpty()) {
			   for (FolderMaster folderMaster : folderList) {
				   LoadOptionsDto loadDto = new LoadOptionsDto(); // Move inside the loop

		            loadDto.setLabel(folderMaster.getFolderCategoryName());
		            loadDto.setValue(folderMaster.getFolderId());

		            dtoList.add(loadDto); // Add each instance to the list
		        }
		 }
		return dtoList;
		
	}

	public void deleteFolder(Long folderId) throws Exception {
		
		System.out.println("print =================>" + folderId);
		
		
	    if (folderId != null) {
	        // Check if the record exists
	    	FolderMaster folderMaster = folderRepository.findById(folderId)
	                .orElseThrow(() -> new Exception("Folder with ID " + folderId + " not found."));
	        
	        // Ensure no dependent records exist (if necessary)
	       

	        // Delete the DocumentUserMaster record
	        folderRepository.deleteById(folderId);
	    } else {
	        throw new Exception("FolderId cannot be null.");
	    }
	}


	
	

	
}
