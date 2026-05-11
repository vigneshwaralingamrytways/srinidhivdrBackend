package com.rytways.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rytways.dto.LoadOptionsDto;
import com.rytways.model.DocumentUserMaster;
import com.rytways.model.FolderMaster;
import com.rytways.repository.FolderRepository;
import com.rytways.service.FolderMasterService;

@RestController
@RequestMapping("/folderMaster")
public class FolderMasterController {
	
	@Autowired
	private FolderMasterService folderMasterService;
	
	@Autowired
	private FolderRepository folderRepository;
	
	
	@PostMapping("/create")
	public ResponseEntity <FolderMaster> createFolder (@RequestBody FolderMaster doc){
		
		doc=folderMasterService.createFolder(doc);
		
		return new ResponseEntity <>(doc,HttpStatus.OK);
	}
	
	@PostMapping("/edit")
	public ResponseEntity <FolderMaster> editFolder (@RequestBody FolderMaster doc){
		
		doc=folderMasterService.editFolder(doc);
		
		return new ResponseEntity <>(doc,HttpStatus.OK);
	}
	
	@GetMapping("/folderMaster")
	public ResponseEntity <List<FolderMaster>> listFolder(){
		List<FolderMaster> doc=folderRepository.findAll();
		return new ResponseEntity <>(doc,HttpStatus.OK);
	}
	
	@GetMapping("/loadOptions")
    public ResponseEntity <List<LoadOptionsDto>> loadType(){
	 		 	
	 
	 	List<LoadOptionsDto> loadFolderList=folderMasterService.loadFolder();
       
        return new ResponseEntity<>(loadFolderList,HttpStatus.OK);
    } 
	
	@PostMapping("/loadOptionsById")
	public ResponseEntity<List<LoadOptionsDto>> loadOptions(@RequestBody FolderMaster doc) {
	    System.out.println("Inside loadOptions method");
	    List<LoadOptionsDto> loadFolderList = folderMasterService.loadOptions(doc);
	    return new ResponseEntity<>(loadFolderList, HttpStatus.OK);
	}

	 
	@PostMapping("/getListByDocumentTypeId")
	public ResponseEntity<List<FolderMaster>> getListByTransacId(@RequestBody FolderMaster idDto) {
	    List<FolderMaster> folderList = folderRepository.findByDocumentTypeIdOrderByUpdatedOn(idDto.getDocumentTypeId());
	    
	    if (!folderList.isEmpty()) {
	        return new ResponseEntity<>(folderList, HttpStatus.OK);
	    } else {
	    	return new ResponseEntity<>(folderList, HttpStatus.OK);
	    }
	}

	
	@PostMapping("/delete")
	public ResponseEntity<String> deleteFolder(@RequestBody FolderMaster request) {
	    try {
	        Long folderId = request.getFolderId();
	        folderMasterService.deleteFolder(folderId);
	        return new ResponseEntity<>("Folder deleted successfully.", HttpStatus.OK);
	    } catch (Exception e) {
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	    }
	}
	  
}
