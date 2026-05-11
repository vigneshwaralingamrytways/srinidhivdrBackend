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
import com.rytways.model.SubFolderMaster;
import com.rytways.repository.SubFolderMasterRepo;
import com.rytways.service.SubfolderService;

@RestController
@RequestMapping("/subFolderMaster")
public class SubFolderMasterController {

	
	@Autowired
	private SubFolderMasterRepo subFolderMasterRepo;
	
	@Autowired
	private SubfolderService subfolderService;
	
	
	@PostMapping("/create")
	public ResponseEntity <SubFolderMaster> createSub (@RequestBody SubFolderMaster doc){
		
		doc=subfolderService.createSub(doc);
		
		return new ResponseEntity <>(doc,HttpStatus.OK);
	}
	
	@PostMapping("/edit")
	public ResponseEntity <SubFolderMaster> editSub (@RequestBody SubFolderMaster doc){
		
		doc=subfolderService.editSub(doc);
		
		return new ResponseEntity <>(doc,HttpStatus.OK);
	}
	
	
	
	@GetMapping("/subFolderMaster")
	public ResponseEntity <List<SubFolderMaster>> listDoc(){
		List<SubFolderMaster> doc=subFolderMasterRepo.findAll();
		return new ResponseEntity <>(doc,HttpStatus.OK);
	}
	
	@GetMapping("/loadOptions")
    public ResponseEntity <List<LoadOptionsDto>> loadSub(){
	 		 	
	 
	 	List<LoadOptionsDto> loadTypeList=subfolderService.loadSub();
       
        return new ResponseEntity<>(loadTypeList,HttpStatus.OK);
    } 
	
	@PostMapping("/loadOptionsById")
    public ResponseEntity <List<LoadOptionsDto>> loadSubs(@RequestBody SubFolderMaster doc){
	 		 	
	 
	 	List<LoadOptionsDto> loadTypeList=subfolderService.loadOptions(doc);
       
        return new ResponseEntity<>(loadTypeList,HttpStatus.OK);
    } 
	
	@PostMapping("/getListByDocypeAndFolder")
	public ResponseEntity<List<SubFolderMaster>> getListByDocumentTypeId(@RequestBody SubFolderMaster doc) {
	    List<SubFolderMaster> folderList = subFolderMasterRepo.findByDocumentTypeIdAndFolderIdOrderByUpdatedOn(
	        doc.getDocumentTypeId(), 
	        doc.getFolderId()
	    );
	    
	    if (!folderList.isEmpty()) {
	        return new ResponseEntity<>(folderList, HttpStatus.OK);
	    } else {
	    	return new ResponseEntity<>(folderList, HttpStatus.OK);
	    }
	}

	@PostMapping("/delete")
	public ResponseEntity<String> deleteSubFolder(@RequestBody SubFolderMaster request) {
	    try {
	        Long subFolderId = request.getSubFolderId();
	        subfolderService.deleteSubFolder(subFolderId);
	        return new ResponseEntity<>("Sub Folder deleted successfully.", HttpStatus.OK);
	    } catch (Exception e) {
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	    }
	}
	  
}
