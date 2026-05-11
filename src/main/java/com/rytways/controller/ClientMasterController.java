package com.rytways.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rytways.model.ClientMaster;
import com.rytways.service.ClientMasterService;

@RestController
@RequestMapping("/clientMaster")
public class ClientMasterController {

	@Autowired
	private ClientMasterService clientMasterService;
	
	@PostMapping("/getAllClients")
	public ResponseEntity<List<ClientMaster>> getAllClients(){
		return new ResponseEntity<List<ClientMaster>>(clientMasterService.getAllClients(),HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<ClientMaster> createNewClient(@RequestBody ClientMaster client){
		return new ResponseEntity<ClientMaster>(clientMasterService.createNewClient(client),HttpStatus.OK);
	}
	
	@PostMapping("/search")
	public ResponseEntity<List<ClientMaster>> searchClients(@RequestBody ClientMaster client){
		return new ResponseEntity<List<ClientMaster>>(clientMasterService.searchClients(client),HttpStatus.OK);
	}
	
	@PostMapping("/readExcel")
	public ResponseEntity<List<ClientMaster>> readExcel(@RequestParam("file") MultipartFile file){
		return new ResponseEntity<List<ClientMaster>>(clientMasterService.readExcel(file),HttpStatus.OK);
	}
}
